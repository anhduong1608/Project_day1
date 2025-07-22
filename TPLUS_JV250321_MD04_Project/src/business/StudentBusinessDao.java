package business;

public interface StudentBusinessDao {
    boolean loginStudent(String email, String password);
    String getPasswordByEmailStudent(String email);
    boolean isEmailStudent(String email);
}
