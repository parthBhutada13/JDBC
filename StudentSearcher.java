//StudentSearcher
package student_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSearcher {
    private final Connection connection;

    public StudentSearcher(Connection connection) {
        this.connection = connection;
    }

    public Student searchByPRN(String prn) throws StudentNotFoundException {
        String sql = "SELECT * FROM students WHERE prn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createStudentFromResultSet(rs);
                } else {
                    throw new StudentNotFoundException(prn);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while searching student", e);
        }
    }

    public Student[] searchByName(String name) {
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                List<Student> students = new ArrayList<>();
                while (rs.next()) {
                    students.add(createStudentFromResultSet(rs));
                }
                return students.toArray(new Student[0]);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while searching students by name", e);
        }
    }

    private Student createStudentFromResultSet(ResultSet rs) throws SQLException {
        String prn = rs.getString("prn");
        String name = rs.getString("name");
        String dob = rs.getString("dob");
        double marks = rs.getDouble("marks");
        return new Student(prn, name, dob, marks);
    }
}
