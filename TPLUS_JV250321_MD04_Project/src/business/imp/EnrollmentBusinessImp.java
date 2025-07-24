package business.imp;

import business.EnrollmentBusinessDao;
import dao.imp.EnrollmentImp;
import entity.Enrollment;

public class EnrollmentBusinessImp implements EnrollmentBusinessDao {
    private final EnrollmentImp enrollmentImp;

    public EnrollmentBusinessImp() {
        enrollmentImp = new EnrollmentImp();
    }
    @Override
    public boolean createEnrollment(Enrollment enrollment) {
        return enrollmentImp.addEnrollment(enrollment);
    }


}
