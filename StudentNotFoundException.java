//StudentNotFoundException
package student_database;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
        super("Student not found in database");
    }

    public StudentNotFoundException(String prn) {
        super("Student with PRN " + prn + " not found");
    }

    public StudentNotFoundException(int position) {
        super("No student found at position " + position);
    }
}
