//StudentDatabase
package student_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDatabase {
    private Connection connection;

    public StudentDatabase() {
        try {
            // JDBC connection setup
            String url = "jdbc:mysql://localhost:3306/student_database";
            String username = "root";
            String password = "/////////////";

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    // Factory methods for operations
    public StudentAdder getAdder() {
        return new StudentAdder(connection);
    }

    public StudentSearcher getSearcher() {
        return new StudentSearcher(connection);
    }

    public StudentUpdater getUpdater() {
        return new StudentUpdater(connection);
    }

    public StudentDeleter getDeleter() {
        return new StudentDeleter(connection);
    }

    public StudentDisplayer getDisplayer() {
        return new StudentDisplayer(connection);
    }

    // Get total student count from database
    public int getCount() {
        try (var stmt = connection.createStatement();
             var rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get student count", e);
        }
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}
