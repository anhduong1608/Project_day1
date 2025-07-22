package business.imp;

import business.StudentBusinessDao;
import dao.imp.LoginDaoImp;
import dao.imp.StudentImp;

import java.util.concurrent.Callable;

public class StudentBusinessImp implements StudentBusinessDao {
    private final LoginDaoImp sLoginDaoImp ;
    private StudentImp sStudentImp;

    public StudentBusinessImp() {
        sLoginDaoImp = new LoginDaoImp();
        sStudentImp = new StudentImp();
    }

    @Override
    public boolean loginStudent(String email, String password) {
        return sLoginDaoImp.loginStudent(email, password);
    }

    @Override
    public String getPasswordByEmailStudent(String email) {
        return sStudentImp.getPasswordByEmailStudent(email);
    }

    @Override
    public boolean isEmailStudent(String email) {
        return sStudentImp.isEmailStudent(email);
    }
}
