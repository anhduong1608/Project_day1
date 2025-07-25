package presentation.adminofmenu;

import business.EnrollmentBusinessDao;
import business.imp.CourseBusinessImp;
import business.imp.EnrollmentBusinessImp;
import business.imp.StudentBusinessImp;
import entity.Course;
import entity.EnrollAndSudentAndCourse;
import entity.Enrollment;
import entity.Student;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class MenuEnrollment {
    private final EnrollmentBusinessImp enrollmentBusinessImp;
    private final CourseBusinessImp courseBusinessImp;
    private final StudentBusinessImp studentBusinessImp;
    private final MenuManagementCourse menuManagementCourse;
    private final MenuManagementStudent menuManagementStudent;

    public MenuEnrollment() {
        enrollmentBusinessImp = new EnrollmentBusinessImp();
        courseBusinessImp = new CourseBusinessImp();
        studentBusinessImp = new StudentBusinessImp();
        menuManagementCourse = new MenuManagementCourse();
        menuManagementStudent = new MenuManagementStudent();
    }

    public void displayMenuEnrollment(Scanner sc) {
        boolean exit = false;
        do {
            try {
                System.out.println("=========MANAGEMENT ENROLLMENT===========");
                System.out.println("1. Hiển thị học viên theo từng khóa học");
                System.out.println("2. Duyệt đơn đăng kí của học viên");
                System.out.println("3. Xóa học viên ra khỏi khóa học");
                System.out.println("4. Quay lại menu chính");
                System.out.println("=========================================");
                System.out.print("lựa chọn của bạn : ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        displayStudentByCourse(sc);
                        break;
                    case 2:
                        addEnrollment(sc);
                        break;
                    case 3:
                        deleteEnrollment(sc);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default: {
                        System.err.println("Mời nhập số lựa chọn từ 1 đến 4");
                    }
                }

            } catch (Exception e) {
                System.err.println("nhập lựa chọn là số nguyên dương!");
            }

        } while (!exit);
    }

    void displayStudentByCourse(Scanner sc) {
        System.out.println("Danh sách khóa học hiện có : \n");
        menuManagementCourse.printHeader();
        List<Course> courses = courseBusinessImp.getCourses();
        courses.forEach(System.out::println);
        System.out.println("Mời nhập ID COURSE cần tìm kiếm thông tin");
        do {
            System.out.println("ID : ");
            String id = sc.nextLine();
            if (id.trim().isEmpty()) {
                System.out.println("mời nhập ID cần tìm không được để trống");
            } else {
                if (Validator.isInt(id)) {
                    int courseId = Integer.parseInt(id);
                    if (courseBusinessImp.checkCourseId(courseId)) {
                        List<Student> students = enrollmentBusinessImp.findStudentByCourse(courseId);
                        if (students.isEmpty()) {
                            System.err.println("Danh sách trống!");
                        } else {
                            System.out.println("Danh sách tìm được các học viên theo khóa học với ID " + courseId);
                            menuManagementStudent.headStudent();
                            students.forEach(System.out::println);
                            break;
                        }
                    } else {
                        System.err.println("ID sai bạn có muốn nhập lại Y/N");
                        String choice = sc.nextLine();
                        if (choice.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                } else {
                    System.err.println("mời nhập ID là số nguyên dương");
                }
            }


        } while (true);
    }

    void addEnrollment(Scanner sc) {
        List<EnrollAndSudentAndCourse> enrollAndSudentAndCourses = enrollmentBusinessImp.findAllEnrollments();
        if (enrollAndSudentAndCourses.isEmpty()) {
            System.err.println("Danh sách đơn đăng ký trống!");
        } else {
            System.out.println("Danh sách đơn đăng ký : \n");
            enrollAndSudentAndCourses.forEach(System.out::println);
        }
        do {
            System.out.println("Nhập ID bảng đăng ký cần duyệt !");
            String id = sc.nextLine();
            if (id.trim().isEmpty()) {
                System.err.println("ID không được để trống!");
            } else {
                if (Validator.isInt(id)) {
                    int enrollmentId = Integer.parseInt(id);
                    boolean result = enrollmentBusinessImp.appectEnrollment(enrollmentId);
                    if (result) {
                        System.out.println("Duyệt đơn thành công!");
                        break;
                    } else {
                        System.err.println("ID Enrollement sai hoặc trạng thái không phải WAITING bạn kiểm tra lại!");
                        break;
                    }
                } else {
                    System.err.println("ID đơn đăng ký nhâp  phải là số nguyên dương sai!");
                }
            }

        } while (true);
    }

    void deleteEnrollment(Scanner sc) {
        int idStudent;
        int idCourse;
        List<EnrollAndSudentAndCourse> enrollAndSudentAndCourses = enrollmentBusinessImp.findAllEnrollments();
        if (enrollAndSudentAndCourses.isEmpty()) {
            System.err.println("Danh sách đơn đăng ký trống!");
        } else {
            System.out.println("Danh sách đơn đăng ký : ");
            headEnrollment();
            enrollAndSudentAndCourses.forEach(System.out::println);
        }
        do {
            System.out.println("nhập ID course muốn xóa đăng kí");
            String input = sc.nextLine().trim();
            if (input.trim().isEmpty()) {
                System.err.println("Nhập ID không được để trống");
            } else {
                if (Validator.isInt(input)) {
                    idCourse = Integer.parseInt(input);
                    break;
                } else {
                    System.err.println("Nhập ID là số nguyên dương");
                    sc.nextLine();
                }
            }
        } while (true);
        do {
            System.out.println("nhập ID Sinh Viên muốn xóa đăng kí");
            String input = sc.nextLine().trim();
            if (input.trim().isEmpty()) {
                System.err.println("Nhập ID không được để trống");
            } else {
                if (Validator.isInt(input)) {
                    idStudent = Integer.parseInt(input);
                    break;
                } else {
                    System.err.println("Nhập ID là số nguyên dương");
                    sc.nextLine();
                }
            }
        } while (true);
        boolean result = studentBusinessImp.deleteEnrolment(idCourse, idStudent);
        if (result) {
            System.out.println("bạn đã xóa thành công!");
        } else {
            System.err.println("Xóa không thành công ID sai hoặc trạng thái không phải là WAITING");
        }

    }
    public void headEnrollment() {
        System.out.printf(" %-5s | %-10s | %-10s | %-25s | %-10s | %-25s | %-25s \n",
                "ID", "Student ID", "Course ID", "Enrollment At", "Status","Course Name","Student Name");
        System.out.println("====================================================================================================================");
    }
    public void headEnrollment1(){
        System.out.printf(" %-5s | %-10s | %-10s | %-25s | %-10s  \n",
                "ID", "Student ID", "Course ID", "Enrollment At", "Status");
        System.out.println("====================================================================================================================");
    }
}
