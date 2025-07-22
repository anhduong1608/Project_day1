package dao.imp;

import dao.AdminDao;
import entity.Admin;
import utils.ConnectionDB;
import validate.PasswordUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminImp implements AdminDao {
    @Override
    public boolean checkUsername(String username) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingmanagement.is_admin_username(?,?)}");
            callSt.setString(1, username);
            callSt.registerOutParameter(2, java.sql.Types.INTEGER);
            callSt.execute();
            int isAdmin = callSt.getInt(2);
            if (isAdmin == 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public String getPasswordByUsername(String username) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL trainingmanagement.get_pass_by_username_admin(?)}");
            callSt.setString(1, username);
            rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return "";
    }

    @Override
    public boolean addAdmin(Admin admin) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingmanagement.save_admin(?,?)}");
            callSt.setString(1, admin.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(admin.getPassword());
            callSt.setString(2, hashedPassword);
            int affected = callSt.executeUpdate();
            if (affected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }
}
