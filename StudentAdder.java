//StudentAdder
package student_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentAdder {
    private final Connection connection;

    public StudentAdder(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws DatabaseFullException, DuplicateStudentException {
        try {
            // First check if student already exists
            if (studentExists(student.getPrn())) {
                throw new DuplicateStudentException(student.getPrn());
            }

            // Insert new student
            String sql = "INSERT INTO students (prn, name, dob, marks) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, student.getPrn());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getDob());
                stmt.setDouble(4, student.getMarks());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while adding student", e);
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
