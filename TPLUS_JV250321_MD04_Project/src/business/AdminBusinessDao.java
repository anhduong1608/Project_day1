package business;

import entity.Admin;

public interface AdminBusinessDao {
    boolean loginAdmin(String username, String password);
    String getPassWordByUsername(String username);
    boolean addAdmin(Admin admin);
}
