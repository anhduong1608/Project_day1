package dao;

import entity.EnrollAndSudentAndCourse;
import entity.Enrollment;
import entity.Student;

import java.util.List;

public interface EnrollmentDao {
    boolean addEnrollment(Enrollment enrollment);
    List<Student> findStudentByCourse(int courseId);
    boolean adminAppectEnrollment(int enrollmentId);
    List<EnrollAndSudentAndCourse> findAllEnrollment();
    List<EnrollAndSudentAndCourse> findEnrollDelete();
}
