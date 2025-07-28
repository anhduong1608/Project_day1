package presentation;

import business.imp.CourseBusinessImp;
import business.imp.EnrollmentBusinessImp;
import business.imp.StudentBusinessImp;
import dao.imp.LoginDaoImp;
import entity.Course;
import entity.CourseEnrollment;
import entity.Enrollment;
import entity.Student;
import presentation.adminofmenu.MenuManagementCourse;
import validate.PasswordUtil;
import validate.Validator;

import java.util.ArrayList;
import java.util.Comparator;
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
            String inputEmail = scanner.nextLine();
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
            String inputPassword = scanner.nextLine();
            if (inputPassword.trim().isEmpty()) {
                System.err.println("mật khẩu không được để trống!");
            } else {
                String inputHash = PasswordUtil.hashPassword(inputPassword);
                String dbHash = studentBusinessImp.getPasswordByEmailStudent(student.getEmail());

                if (inputHash.equals(dbHash)) {
                    student.setPassword(inputHash);
                    break;
                } else {
                    System.err.println("Mật khẩu hoặc email không đúng. Vui lòng nhập lại.");
                    break;
                }
            }

        } while (true);

        boolean studentLoginResult = studentBusinessImp.loginStudent(student.getEmail(), student.getPassword());
        if (studentLoginResult) {
            System.out.println("Sinh viên đăng nhập thành công!\n");
            student = studentBusinessImp.getStudentFromEmail(student.getEmail());
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
                    System.out.println("==================================");
                    System.out.println("mời bạn chọn :");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuManagementCourse.pagingCourseMenu(scanner);
                            break;
                        case 2:
                            enrollmentCourse(scanner, student);
                            break;
                        case 3:
                            displayCourseByStudentId(scanner, student);
                            break;
                        case 4:
                            deleteEnrollmentByStudentId(scanner, student);
                            break;
                        case 5:
                            updatePassword(scanner, student);
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
            System.err.println("Đăng nhập thất bại!");
        }

    }


    void enrollmentCourse(Scanner scanner, Student student) {
        Enrollment enrollment = new Enrollment();
        List<Course> enrollmentCourseList = studentBusinessImp.getCoursesByStudentId(student.getId());
        System.out.println("Danh sách khóa học : ");
        menuManagementCourse.pagingCourseMenu(scanner);
        List<Course> courses = courseBusinessImp.getCourses();
        courses.forEach(System.out::println);
        do {
            System.out.println("nhập ID khóa học muốn đăng ký :");
            String string = scanner.nextLine();

            if (string.trim().isEmpty()) {
                System.err.println("Nhập ID muốn đăng ký không được để trống!");
                scanner.nextLine();
            } else {
                if (Validator.isInt(string)) {
                    int id = Integer.parseInt(string);
                    if (courseBusinessImp.checkCourseId(id)) {
                        if (enrollmentCourseList.stream().anyMatch(course -> course.getId() == id)) {
                            System.err.println("Khóa học này bạn đã đăng ký rồi xin mời đăng ký khóa học khác không Y/N");
                            if (scanner.nextLine().trim().equalsIgnoreCase("n")) {
                                return;
                            }
                            continue;
                        } else {
                            System.out.println("Khóa học bạn muốn đăng ký : ");
                            Course course = courseBusinessImp.getCourseById(id);
                            System.out.println(course);
                            System.out.println("Bạn có muốn Đăng ký khóa học này không Y/N ");
                            if (scanner.nextLine().equalsIgnoreCase("y")) {
                                enrollment.setCourseId(id);
                                break;
                            } else {
                                System.out.println("hủy đăng ký");
                                return;
                            }
                        }
                    } else {
                        System.err.println("ID khoá học không đúng bạn có muốn nhập lại không Y/N");
                        if (scanner.nextLine().equalsIgnoreCase("n")) return;
                    }
                } else {
                    System.err.println("Nhập ID phải là số nguyên dương");
                    scanner.nextLine();
                }
            }
        } while (true);
//        System.out.println(student.getId());
        enrollment.setStudent_id(student.getId());
        boolean result = enrollmentBusinessImp.createEnrollment(enrollment);
        if (result) {
            System.out.println("Đăng ký thành công chờ xác nhận");
        } else {
            System.err.println("Đăng ký thất bại!");
        }
    }

    void displayCourseByStudentId(Scanner scanner, Student student) {
        List<CourseEnrollment> courseEnrollments = courseBusinessImp.findCourseByStuId(student.getId());
        if (courseEnrollments.isEmpty()) {
            System.out.println("Hiện chưa đăng ký khóa học nào");
        } else {
            System.out.println("Các khóa học đã đăng ký : \n");
            System.out.printf(" %-5s | %-25s | %-15s | %-20s | %-10s|%-20s|%-10s \n",
                    "ID", "Name", "Duration", "Instructor", "Created At","Enrollment At","Status");
            System.out.println("==========================================================================================================================");
            courseEnrollments.forEach(System.out::println);
            boolean exit = false;
            do {
                try {
                    System.out.println("1. Danh sách sắp xếp theo tên khóa học tăng dần");
                    System.out.println("2. Danh sách sắp xếp theo tên khóa học giảm dần");
                    System.out.println("3. Danh sách sắp xếp theo ngày đăng ký khóa học tăng dần");
                    System.out.println("4. Danh sách sắp xếp theo ngày đăng ký khóa học giảm dần");
                    System.out.println("5. thoát");
                    System.out.print("lựa chọn của bạn : ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Danh sách khóa học của bạn : \n");
                            System.out.printf(" %-5s | %-25s | %-15s | %-20s | %-10s|%-20s|%-10s \n",
                                    "ID", "Name", "Duration", "Instructor", "Created At","Enrollment At","Status");
                            System.out.println("==========================================================================================================================");
                            courseEnrollments.stream().sorted(Comparator.comparing(Course::getName)).forEach(System.out::println);
                            break;
                        case 2:
                            System.out.println("Danh sách khóa học của bạn : \n");
                            System.out.printf(" %-5s | %-25s | %-15s | %-20s | %-10s|%-20s|%-10s \n",
                                    "ID", "Name", "Duration", "Instructor", "Created At","Enrollment At","Status");
                            System.out.println("==========================================================================================================================");
                            courseEnrollments.stream().sorted(Comparator.comparing(Course::getName).reversed()).forEach(System.out::println);
                            break;
                        case 3:
                            System.out.println("Danh sách khóa học của bạn : \n");
                            System.out.printf(" %-5s | %-25s | %-15s | %-20s | %-10s|%-20s|%-10s \n",
                                    "ID", "Name", "Duration", "Instructor", "Created At","Enrollment At","Status");
                            System.out.println("==========================================================================================================================");
                            courseEnrollments.stream().sorted(Comparator.comparing(CourseEnrollment::getEnrollmentAt)).forEach(System.out::println);
                            break;
                        case 4:
                            System.out.println("Danh sách khóa học của bạn : \n");
                            System.out.printf(" %-5s | %-25s | %-15s | %-20s | %-10s|%-20s|%-10s \n",
                                    "ID", "Name", "Duration", "Instructor", "Created At","Enrollment At","Status");
                            System.out.println("==========================================================================================================================");
                            courseEnrollments.stream().sorted(Comparator.comparing(CourseEnrollment::getEnrollmentAt).reversed()).forEach(System.out::println);
                            break;
                        case 5:
                            exit = true;
                            break;
                        default: {
                            System.err.println("Nhập lựa chọn từ 1 đến 5 !");
                        }
                    }
                } catch (Exception npe) {
                    System.err.println("Nhập vào số nguyên thích hợp!");
                }


            } while (!exit);

        }
    }

    void deleteEnrollmentByStudentId(Scanner scanner, Student idStudent) {
        int idCourse;
        List<Course> courses = studentBusinessImp.getCoursesByStudentId(idStudent.getId());
        System.out.println("Danh sách khóa học của bạn : ");
        courses.stream().sorted(Comparator.comparing(Course::getName)).forEach(System.out::println);

        do {
            System.out.println("nhập ID course muốn xóa đăng kí");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("Nhập ID không được để trống");
            } else {
                if (Validator.isInt(input)) {
                    idCourse = Integer.parseInt(input);

                    break;
                } else {
                    System.err.println("Nhập ID là số nguyên dương");
                }
            }
        } while (true);
        boolean result = studentBusinessImp.deleteEnrolment(idCourse, idStudent.getId());
        if (result) {
            System.out.println("Xóa đăng ký thành công");
        } else {
            System.err.println("Xóa đăng ký không thành công!");
        }
    }

    void updatePassword(Scanner scanner, Student student) {
        System.out.println("mời nhập pass cũ : ");
        String pass = scanner.nextLine();
        if (PasswordUtil.hashPassword(pass).equals(student.getPassword())) {
            System.out.println("mời nhập emai hoặc SDT đăng ký");
            String check = scanner.nextLine();
            if (check.equals(student.getEmail()) || check.equals(student.getPhone())) {
                System.out.println("mời nhập mật khẩu mới : ");
                String newPass = scanner.nextLine();
                String hashPass = PasswordUtil.hashPassword(newPass);
                student.setPassword(hashPass);
                boolean result = studentBusinessImp.updatePassStudent(student);
                if (result) {
                    System.out.println("Đổi mật khẩu thành công!");
                } else {
                    System.err.println("Đổi mật khẩu thất bại");
                }
            } else {
                System.err.println("Email hoặc SDT không đúng không thể đổi mật khẩu!!");
            }
        } else {
            System.err.println("Pass cũ không đúng không thể thay đổi pass mới!");
        }
    }
}
