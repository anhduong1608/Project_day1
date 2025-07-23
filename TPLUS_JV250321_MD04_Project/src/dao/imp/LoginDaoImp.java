package dao.imp;

import dao.LoginDao;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImp implements LoginDao {
    @Override
    public boolean loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.login_admin(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            rs = callSt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public boolean loginStudent(String email, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.login_student(?,?)}");
            callSt.setString(1, email);
            callSt.setString(2, password);
            rs = callSt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }
}
