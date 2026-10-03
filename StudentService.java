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
        if (firstName == null || lastName == null || email == null || major == null
                || firstName.isBlank() || lastName.isBlank() || major.isBlank()
                || !isValidGpa(gpa)) {
            return false;
        }

        String trimmedEmail = email.trim();
        if (!isValidEmail(trimmedEmail)) {
            return false;
        }

        return studentDao.addStudent(new Student(firstName.trim(), lastName.trim(), trimmedEmail, major.trim(), gpa));
    }

    public boolean updateStudentDetails(int id, String firstName, String lastName, String email, String major, String gpaStr) {
        if (firstName == null || lastName == null || email == null || major == null || gpaStr == null) {
            return false;
        }

        String trimmedEmail = email.trim();
        if (!trimmedEmail.isEmpty() && !isValidEmail(trimmedEmail)) {
            return false;
        }

        Double parsedGpa = null;
        String trimmedGpa = gpaStr.trim();
        if (!trimmedGpa.isEmpty()) {
            try {
                parsedGpa = Double.parseDouble(trimmedGpa);
            } catch (NumberFormatException e) {
                return false;
            }
            if (!isValidGpa(parsedGpa)) {
                return false;
            }
        }

        Optional<Student> existingOpt = studentDao.getStudentById(id);
        if (existingOpt.isEmpty()) return false;

        Student s = existingOpt.get();

        if (!firstName.isBlank()) s.setFirstName(firstName.trim());
        if (!lastName.isBlank()) s.setLastName(lastName.trim());
        if (!trimmedEmail.isEmpty()) s.setEmail(trimmedEmail);
        if (!major.isBlank()) s.setMajor(major.trim());
        if (parsedGpa != null) s.setGpa(parsedGpa);

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
