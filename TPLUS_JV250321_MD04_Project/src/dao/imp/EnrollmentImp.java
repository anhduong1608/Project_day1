package dao.imp;

import dao.EnrollmentDao;
import entity.Enrollment;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;

public class EnrollmentImp implements EnrollmentDao {
    @Override
    public boolean addEnrollment(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.save_enrollment(?,?)}");
            stmt.setInt(1, enrollment.getStudent_id());
            stmt.setInt(2, enrollment.getCourseId());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return false;
    }
}
