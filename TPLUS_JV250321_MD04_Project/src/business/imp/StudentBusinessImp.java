package business.imp;

import business.StudentBusinessDao;
import dao.imp.LoginDaoImp;
import dao.imp.StudentImp;
import entity.Student;

import java.util.List;
import java.util.concurrent.Callable;

public class StudentBusinessImp implements StudentBusinessDao {
    private final LoginDaoImp sLoginDaoImp;
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

    @Override
    public List<Student> getAllStudents() {
        return sStudentImp.getAllStudents();
    }

    @Override
    public boolean addStudent(Student student) {
        return sStudentImp.addStudent(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return sStudentImp.updateStudent(student);
    }

    @Override
    public boolean isStudentId(int idStudent) {
        return sStudentImp.isStudentId(idStudent);
    }

    @Override
    public Student getStudentById(int idStudent) {
        return sStudentImp.getStudentById(idStudent);
    }

    @Override
    public boolean deleteStudent(int idStudent) {
        return sStudentImp.deleteStudent(idStudent);
    }
}
