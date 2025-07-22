package dao;

import entity.Admin;

public interface AdminDao {
    boolean checkUsername(String username);
    String getPasswordByUsername(String username);
    boolean addAdmin(Admin admin);

}
