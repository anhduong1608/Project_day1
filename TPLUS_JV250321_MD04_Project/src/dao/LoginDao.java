package dao;

public interface LoginDao {
    boolean loginAdmin(String username, String password);
    boolean loginStudent(String email, String password);
}
