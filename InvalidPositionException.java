//InvalidPOsitionException
package student_database;

public class InvalidPositionException extends Exception {

    // Default constructor
    public InvalidPositionException() {
        super("Invalid student position");
    }

    // Constructor with max position parameter
    public InvalidPositionException(int maxPosition) {
        super(String.format("Position must be between 1 and %d", maxPosition));
    }

    // Constructor with custom message
    public InvalidPositionException(String message) {
        super(message);
    }

    // Constructor with position and max position
    public InvalidPositionException(int position, int maxPosition) {
        super(String.format("Invalid position %d (must be between 1 and %d)",
                position, maxPosition));
    }

    // Constructor with cause
    public InvalidPositionException(Throwable cause) {
        super("Invalid student position", cause);
    }

    // Constructor with message and cause
    public InvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
