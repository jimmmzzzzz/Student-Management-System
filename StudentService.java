package service;

import dao.StudentDao;
import model.Student;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public boolean registerStudent(String firstName, String lastName, String email, String major, double gpa) {
        if (firstName == null || lastName == null || major == null
                || firstName.isBlank() || lastName.isBlank() || major.isBlank()
                || !isValidEmail(email) || !isValidGpa(gpa)) {
            return false;
        }
        return studentDao.addStudent(new Student(firstName.trim(), lastName.trim(), email.trim(), major.trim(), gpa));
    }

    public boolean updateStudentDetails(int id, String firstName, String lastName, String email, String major, String gpaStr) {
        Optional<Student> existingOpt = studentDao.getStudentById(id);
        if (existingOpt.isEmpty()) return false;

        Student s = existingOpt.get();

        if (!firstName.isBlank()) s.setFirstName(firstName.trim());
        if (!lastName.isBlank()) s.setLastName(lastName.trim());
        if (!email.isBlank() && isValidEmail(email)) s.setEmail(email.trim());
        if (!major.isBlank()) s.setMajor(major.trim());
        if (!gpaStr.isBlank()) {
            try {
                double gpa = Double.parseDouble(gpaStr.trim());
                if (isValidGpa(gpa)) s.setGpa(gpa);
            } catch (NumberFormatException ignored) {}
        }

        return studentDao.updateStudent(s);
    }

    public boolean removeStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    public List<Student> listAll() {
        return studentDao.getAllStudents();
    }

    public List<Student> search(String query) {
        return studentDao.searchByName(query.trim());
    }

    private boolean isValidGpa(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
