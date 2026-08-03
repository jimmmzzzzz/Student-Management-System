package model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private double gpa;
    private LocalDate enrollmentDate;

    public Student(int id, String firstName, String lastName, String email, String major, double gpa, LocalDate enrollmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        this.gpa = gpa;
        this.enrollmentDate = enrollmentDate;
    }

    public Student(String firstName, String lastName, String email, String major, double gpa) {
        this(-1, firstName, lastName, email, major, gpa, LocalDate.now());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
}
