package dao.imp;

import dao.StudentDao;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class StudentImp implements StudentDao {
    @Override
    public String getPasswordByEmailStudent(String email) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingmanagement.is_email_student(?)}");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return "";
    }

    @Override
    public boolean isEmailStudent(String email) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingmanagement.get_pass_by_email(?,?)}");
            stmt.setString(1, email);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();
            int pass = stmt.getInt(2);
            if (pass == 1) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return false;
    }
}
