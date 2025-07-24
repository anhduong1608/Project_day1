package business.imp;

import business.EnrollmentBusinessDao;
import dao.imp.EnrollmentImp;
import entity.EnrollAndSudentAndCourse;
import entity.Enrollment;
import entity.Student;

import java.util.List;

public class EnrollmentBusinessImp implements EnrollmentBusinessDao {
    private final EnrollmentImp enrollmentImp;

    public EnrollmentBusinessImp() {
        enrollmentImp = new EnrollmentImp();
    }

    @Override
    public boolean createEnrollment(Enrollment enrollment) {
        return enrollmentImp.addEnrollment(enrollment);
    }

    @Override
    public List<Student> findStudentByCourse(int courseId) {
        return enrollmentImp.findStudentByCourse(courseId);
    }

    @Override
    public boolean appectEnrollment(int enrollmentId) {
        return enrollmentImp.adminAppectEnrollment(enrollmentId);
    }

    @Override
    public List<EnrollAndSudentAndCourse> findAllEnrollments() {
        return enrollmentImp.findAllEnrollment();
    }


}
