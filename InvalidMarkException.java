//InvalidMarkException
package student_database;

public class InvalidMarkException extends Exception {
    public InvalidMarkException() {
        super("Marks must be between 0 and 100");
    }

    public InvalidMarkException(double marks) {
        super("Invalid marks value: " + marks + ". Must be between 0-100");
    }
}
