package business;

import entity.Course;
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
    List<Student> getStudentByName(String name);
    List<Student> getStudentByEmail(String email);
    List<Course> getCoursesByStudentId(int idStudent);
    boolean deleteEnrolment(int idCourse,int idStudent);
    boolean updatePassStudent(Student student);
    Student getStudentFromEmail(String email);
}
