//DuplicateStudentException.java
package student_database;

public class DuplicateStudentException extends Exception {
    public DuplicateStudentException() {
        super("Student with this PRN already exists");
    }

    public DuplicateStudentException(String prn) {
        super("Student with PRN " + prn + " already exists in database");
    }
}
