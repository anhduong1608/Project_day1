package presentation;

import business.imp.CourseBusinessImp;
import business.imp.EnrollmentBusinessImp;
import business.imp.StudentBusinessImp;
import dao.imp.LoginDaoImp;
import entity.Course;
import entity.Enrollment;
import entity.Student;
import org.w3c.dom.ls.LSOutput;
import presentation.adminofmenu.MenuManagementCourse;
import validate.PasswordUtil;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentPresentation {
    private final StudentBusinessImp studentBusinessImp;
    private final LoginDaoImp loginDaoImp;
    private final MenuManagementCourse menuManagementCourse;
    private final EnrollmentBusinessImp enrollmentBusinessImp;
    private final MenuManagementCourse menuManagementCourseImp;
    private final CourseBusinessImp courseBusinessImp;

    public StudentPresentation() {
        studentBusinessImp = new StudentBusinessImp();
        loginDaoImp = new LoginDaoImp();
        menuManagementCourse = new MenuManagementCourse();
        enrollmentBusinessImp = new EnrollmentBusinessImp();
        menuManagementCourseImp = new MenuManagementCourse();
        courseBusinessImp = new CourseBusinessImp();
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
                            menuManagementCourse.displayCourse(scanner);
                            break;
                        case 2:
                            enrollmentCourse(scanner, student);
                            break;
                        case 3:
                            displayCourseByStudentId(scanner, student);
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


    void enrollmentCourse(Scanner scanner, Student student) {
        Enrollment enrollment = new Enrollment();
        do {
            System.out.println("nhập ID khóa học muốn đăng ký :");
            String string = scanner.next();
            if (string.trim().isEmpty()) {
                System.err.println("Nhập ID muốn đăng ký không được để trống!");
            } else {
                if (Validator.isInt(string)) {
                    int id = Integer.parseInt(string);
                    if (courseBusinessImp.checkCourseId(id)) {
                        System.out.println("Khóa học bạn muốn đăng ký : \n");
                        Course course = courseBusinessImp.getCourseById(id);
                        System.out.println(course);
                        System.out.println("Bạn có muốn Đăng ký khóa học này không Y/N ");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            enrollment.setCourseId(id);
                            break;
                        } else {
                            return;
                        }
                    } else {
                        System.err.println("ID khoá học không đúng bạn có muốn nhập lại không Y/N");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("n")) return;
                    }
                } else {
                    System.err.println("Nhập ID phải là số nguyên dương");
                }
            }
        } while (true);
        enrollment.setStudent_id(student.getId());
        boolean result = enrollmentBusinessImp.createEnrollment(enrollment);
        if (result) {
            System.out.println("Đăng ký thành công chờ xác nhận");
        } else {
            System.err.println("Đăng ký thất bại!");
        }
    }

    void displayCourseByStudentId(Scanner scanner, Student student) {
        List<Course> courses = studentBusinessImp.getCoursesByStudentId(student.getId());
        if (courses.isEmpty()) {
            System.out.println("Hiện chưa đăng ký khóa học nào");
        } else {
            System.out.println("Các khóa học đã đăng ký : \n");
            courses.forEach(System.out::println);

        }
    }
}
