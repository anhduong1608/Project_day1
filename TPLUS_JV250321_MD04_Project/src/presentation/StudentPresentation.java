package presentation;

import business.imp.StudentBusinessImp;
import dao.imp.LoginDaoImp;
import entity.Student;
import validate.PasswordUtil;
import validate.Validator;

import java.util.Scanner;

public class StudentPresentation {
    private final StudentBusinessImp studentBusinessImp;
    private final LoginDaoImp loginDaoImp;

    public StudentPresentation() {
        studentBusinessImp = new StudentBusinessImp();
        loginDaoImp = new LoginDaoImp();
    }

    public void loginByStudent(Scanner scanner) {
        Student student = new Student();
        System.out.println("mời nhập email : ");
        do {
            String inputEmail = scanner.next();
            if (inputEmail.trim().isEmpty()) {
                System.err.println("email không được để trống mời nhập lại!");
            } else {
                if (Validator.validateEmail(inputEmail)) {
                    student.setEmail(inputEmail);
                    break;
                } else {
                    System.err.println("xin hãy nhập đúng định dạng email!");
                }
            }

        } while (true);
//        System.out.printf("%s",studentBusinessImp.getPasswordByEmailStudent(student.getEmail()));
        System.out.println("mời nhập mật khẩu đăng nhập : ");
        do {
            String inputPassword = scanner.next();
            if (inputPassword.trim().isEmpty()) {
                System.err.println("mật khẩu không được để trống!");
            } else {
                String inputHash = PasswordUtil.hashPassword(inputPassword);
                String dbHash = studentBusinessImp.getPasswordByEmailStudent(student.getEmail());

                if (inputHash.equals(dbHash)) {
                    student.setPassword(inputHash);
                    scanner.nextLine();
                    break;
                } else {
                    System.err.println("Mật khẩu không đúng. Vui lòng nhập lại.");
                }
            }

        } while (true);

        boolean studentLoginResult = studentBusinessImp.loginStudent(student.getEmail(), student.getPassword());
        if (studentLoginResult) {
            System.out.println("Sinh viên đăng nhập thành công!\n");
            boolean exits = false;
            do {
                try {
                    System.out.println("==========MENU HỌC VIÊN==========");
                    System.out.println("1. Xem danh sách khóa học");
                    System.out.println("2. Đăng ký khóa học");
                    System.out.println("3. Xem khóa học đã đăng ký");
                    System.out.println("4. Hủy đăng ky(Nếu chưa bắt đầu)");
                    System.out.println("5. Đổi mật khẩu");
                    System.out.println("6. Đăng xuất");
                    System.out.println("mời bạn chọn :");
                    int choice = Integer.parseInt(scanner.next());
                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            exits = true;
                            break;
                        default: {
                            System.err.println("mời bạn nhập từ 1 đến 6");
                        }
                    }
                } catch (NullPointerException npe) {
                    System.err.println("vui lòng nhập lưa chọn bằng số thích hợp!");
                }


            } while (!exits);

        } else {
            System.err.println("Đăng nhập thất bại! xin hay đăng nhập lại!");
        }
    }

}
