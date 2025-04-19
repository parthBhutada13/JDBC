//StudentUpdater
package student_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentUpdater {
    private final Connection connection;

    public StudentUpdater(Connection connection) {
        this.connection = connection;
    }

    public void updateStudent(String oldPRN, Student newStudent) throws StudentNotFoundException {
        try {
            // First check if old student exists
            if (!studentExists(oldPRN)) {
                throw new StudentNotFoundException(oldPRN);
            }

            // Check if new PRN already exists (if PRN is being changed)
            if (!oldPRN.equals(newStudent.getPrn()) && studentExists(newStudent.getPrn())) {
                throw new DuplicateStudentException(newStudent.getPrn());
            }

            // Update student
            String sql = "UPDATE students SET prn = ?, name = ?, dob = ?, marks = ? WHERE prn = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, newStudent.getPrn());
                stmt.setString(2, newStudent.getName());
                stmt.setString(3, newStudent.getDob());
                stmt.setDouble(4, newStudent.getMarks());
                stmt.setString(5, oldPRN);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new StudentNotFoundException(oldPRN);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while updating student", e);
        } catch (DuplicateStudentException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean studentExists(String prn) throws SQLException {
        String sql = "SELECT 1 FROM students WHERE prn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prn);
            try (var rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
