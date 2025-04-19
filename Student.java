//Student
package student_database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Student {
    private String prn;
    private String name;
    private String dob; // Stored as "YYYY-MM-DD" string for MySQL compatibility
    private double marks;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Student(String prn, String name, String dob, double marks) {
        setPrn(prn);
        setName(name);
        setDob(dob);
        setMarks(marks);
    }

    // PRN getter/setter with validation
    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        if (prn == null || prn.trim().isEmpty()) {
            throw new IllegalArgumentException("PRN cannot be null or empty");
        }
        if (!prn.matches("\\d{3,}")) {
            throw new IllegalArgumentException("PRN must contain only digits (minimum 3)");
        }
        this.prn = prn.trim();
    }

    // Name getter/setter with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }
        this.name = name.trim();
    }

    // Date of Birth getter/setter with validation
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        try {
            LocalDate.parse(dob, DATE_FORMATTER);
            this.dob = dob;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("DOB must be in YYYY-MM-DD format");
        }
    }

    // Marks getter/setter with validation
    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        this.marks = marks;
    }

    // Additional calculated property
    public String getGrade() {
        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 55) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    // Age calculation (additional method)
    public int getAge() {
        LocalDate birthDate = LocalDate.parse(dob, DATE_FORMATTER);
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public String toString() {
        return String.format("PRN: %s | Name: %-20s | DoB: %s | Marks: %5.1f | Grade: %s",
                prn, name, dob, marks, getGrade());
    }

    // Equality comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return prn.equals(other.prn);
    }

    @Override
    public int hashCode() {
        return prn.hashCode();
    }
}
