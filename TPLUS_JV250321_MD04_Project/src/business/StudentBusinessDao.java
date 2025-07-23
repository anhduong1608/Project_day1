package business;

import entity.Student;

import java.util.List;

public interface StudentBusinessDao {
    boolean loginStudent(String email, String password);
    String getPasswordByEmailStudent(String email);
    boolean isEmailStudent(String email);
    List<Student> getAllStudents();
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean isStudentId(int idStudent);
    Student getStudentById(int idStudent);
    boolean deleteStudent(int idStudent);
}
