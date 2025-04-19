//StudentDisplayer.java
package student_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDisplayer {
    private final Connection connection;

    public StudentDisplayer(Connection connection) {
        this.connection = connection;
    }

    public void displayAllStudents() {
        String sql = "SELECT * FROM students";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- ALL STUDENTS ---");
            System.out.println("+--------+----------------------+------------+-------+-------+");
            System.out.println("| PRN    | Name                 | DoB        | Marks | Grade |");
            System.out.println("+--------+----------------------+------------+-------+-------+");

            int count = 0;
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("prn"),
                        rs.getString("name"),
                        rs.getString("dob"),
                        rs.getDouble("marks")
                );

                System.out.printf("| %-6s | %-20s | %-10s | %5.1f | %-5s |\n",
                        student.getPrn(),
                        student.getName(),
                        student.getDob(),
                        student.getMarks(),
                        student.getGrade());

                count++;
            }

            System.out.println("+--------+----------------------+------------+-------+-------+");
            System.out.printf("Total students: %d\n", count);

        } catch (SQLException e) {
            throw new RuntimeException("Database error while displaying students", e);
        }
    }
}
