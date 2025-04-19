//DatabaseFullException
package student_database;

public class DatabaseFullException extends Exception {
    public DatabaseFullException() {
        super("Database has reached maximum capacity");
    }

    public DatabaseFullException(int capacity) {
        super("Database cannot exceed " + capacity + " students");
    }
}
