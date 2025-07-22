package dao;

public interface StudentDao {
    String getPasswordByEmailStudent(String email);
    boolean isEmailStudent(String email);
}
