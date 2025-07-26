package dao;

import entity.Course;
import entity.Enrollment;
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
    List<Student> getStudentByEmail(String email);
    List<Student> getStudentByName(String name);
    List<Course> getCourseByStudentId(int id);
    boolean deleteEnrollmentByStudent(int courseId, int studentId);
    boolean updatePassStudent(Student student);
    Student getStudentfromEmail(String email);
    int studentOfTotalPage(int rowOfPage);
    List<Student> pagingStudent(int page, int rowOfPage);
}
