package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {
    String getPasswordByEmailStudent(String email);
    boolean isEmailStudent(String email);
    List<Student> getAllStudents();
    boolean addStudent(Student student);
    Student getStudentById(int id);
    boolean updateStudent(Student student);
    boolean isStudentId(int id);
    boolean deleteStudent(int id);
}
