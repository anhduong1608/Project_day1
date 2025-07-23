package presentation;

import business.imp.AdminBusinessImp;
import dao.imp.AdminImp;
import entity.Admin;
import validate.PasswordUtil;
import validate.Validator;

import java.util.Scanner;

public class AdminPresentation {
    private final AdminBusinessImp adminBusinessImp;
    private final AdminImp adminImp;


    public AdminPresentation() {
        adminBusinessImp = new AdminBusinessImp();
        adminImp = new AdminImp();
    }

 public    void loginByAdmin(Scanner scanner) {
        Admin admin = new Admin();
        System.out.println("Đăng nhập với tư cách quản trị viên");
        System.out.println("mời nhập username : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("mời nhập tên đăng nhập không được để trống");
            } else {
                if (input.length()>=5) {
                    admin.setUsername(input);
                    break;
                } else {
                    System.err.println("tên đăng nhập phải dài hơn 6 kí tự lòng nhập lại!");
                }
            }
        } while (true);
        System.out.println("nhập password : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("password không được để trống");
            } else {
                if (input.length() <= 6) {
                    System.err.println("password phải dài hơn 6 kí tự");
                } else {
                    admin.setPassword(input);
                    break;
                }
            }

        } while (true);
        boolean adminLoginResult = adminBusinessImp.loginAdmin(admin.getUsername(), PasswordUtil.hashPassword(admin.getPassword()));
        if (adminLoginResult) {
            boolean exits = false;
            do {
                try {
                    System.out.println("đăng nhập với tư cách quản trị viên thành công");
                    System.out.println("===========MENU ADMIN==========");
                    System.out.println("1. Quản lý khóa học");
                    System.out.println("2. Quản lý học viên");
                    System.out.println("3. Quản lý đăng ký học");
                    System.out.println("4. Thống kê học viên theo khóa");
                    System.out.println("5. Thoát");
                    System.out.printf("Lựa chọn của bạn là:");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:exits = true;
                            break;
                        default: {
                            System.err.println("nhập lựa chọn từ 1 đến 5");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("vui lòng lựa chọn la số !");
                }

            } while (!exits);


        } else {
            System.err.println("đăng nhập thất bại vui lòng kiểm tra đường truyền hoặc mật khẩu và username !");
        }
    }
}
