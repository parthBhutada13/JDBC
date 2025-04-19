//StudentDeleter
package student_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleter {
    private final Connection connection;

    public StudentDeleter(Connection connection) {
        this.connection = connection;
    }

    public void deleteStudent(String prn) throws StudentNotFoundException {
        String sql = "DELETE FROM students WHERE prn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prn);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new StudentNotFoundException(prn);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting student", e);
        }
    }
}
