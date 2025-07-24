import presentation.AdminPresentation;
import presentation.StudentPresentation;
import validate.PasswordUtil;

import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final AdminPresentation adminPresentation;
    private final StudentPresentation studentPresentation;

    public Main() {
        adminPresentation = new AdminPresentation();
        studentPresentation = new StudentPresentation();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
//        String haspassword = PasswordUtil.hashPassword("admin123");
//        System.out.println(haspassword);
        do {
            try {
                System.out.println("1. Đăng nhập với tư cách quản trị viên.");
                System.out.println("2. Đăng nhập với tư cách quản học viên.");
                System.out.println("3. Thoát.");
                System.out.printf("xin moi lua chon : ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        main.adminPresentation.loginByAdmin(sc);
                        break;
                    case 2:
                        main.studentPresentation.loginByStudent(sc);
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (Exception ex) {
                System.err.println("xin lựa chọn bằng số thích hợp!");
            }

        } while (true);

    }
}