package business.imp;

import business.AdminBusinessDao;
import dao.imp.AdminImp;
import dao.imp.LoginDaoImp;
import entity.Admin;

import java.util.Scanner;

public class AdminBusinessImp implements AdminBusinessDao {
    private final LoginDaoImp loginDaoImp ;
    private final AdminImp adminImp;

    public AdminBusinessImp() {
        loginDaoImp = new LoginDaoImp();
        adminImp = new AdminImp();
    }

    @Override
    public boolean loginAdmin(String username, String password) {
        return loginDaoImp.loginAdmin(username, password);
    }

    @Override
    public String getPassWordByUsername(String username) {
        return adminImp.getPasswordByUsername(username);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return adminImp.addAdmin(admin);
    }

}
