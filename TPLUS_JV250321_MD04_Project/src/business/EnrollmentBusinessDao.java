package business;

import entity.EnrollAndSudentAndCourse;
import entity.Enrollment;
import entity.Student;

import java.util.List;

public interface EnrollmentBusinessDao {
    boolean createEnrollment(Enrollment enrollment);
    List<Student> findStudentByCourse(int courseId);
    boolean appectEnrollment(int enrollmentId);
    List<EnrollAndSudentAndCourse> findAllEnrollments();
}
