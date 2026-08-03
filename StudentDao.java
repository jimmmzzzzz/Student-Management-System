package dao;

import model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentDao {
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
    Optional<Student> getStudentById(int id);
    List<Student> getAllStudents();
    List<Student> searchByName(String query);
}
