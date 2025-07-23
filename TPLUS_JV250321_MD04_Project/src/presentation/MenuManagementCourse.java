package presentation;

import business.imp.CourseBusinessImp;
import entity.Course;
import org.w3c.dom.ls.LSOutput;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class MenuManagementCourse {
    private final CourseBusinessImp courseBusinessImp;

    public MenuManagementCourse() {
        courseBusinessImp = new CourseBusinessImp();
    }

    void displayMenuManagementCourse(Scanner scanner) {
        boolean exits = false;
        do {

            try {
                System.out.println("1. Hiển thị danh sách khóa học");
                System.out.println("2. Thêm mới khóa học");
                System.out.println("3. Chỉnh sửa thông tin khóa học(hiển thị menu chỉnh sửa)");
                System.out.println("4. Xóa khóa học(sác nhận trước khi xóa)");
                System.out.println("5. Tìm kiến theo tên tương đối");
                System.out.println("6. Sắp xếp theo tên hoặc ID(tăng / Giảm)");
                System.out.println("7. Quay lại menu chính");
                System.out.print("mời bạn nhập lựa chọn : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayCourse(scanner);
                        break;
                    case 2:saveCourse(scanner);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        exits = true;
                        break;
                    default: {
                        System.err.println("mời bạn nhập số từ 1 đến 7");
                    }
                }
            } catch (NullPointerException npe) {
                System.err.println("mời bạn nhập số thích hợp!");
            }

        } while (!exits);

    }

    void displayCourse(Scanner scanner) {
        List<Course> courses = courseBusinessImp.getCourses();
        if (courses.isEmpty()) {
            System.err.println("hiện tại danh sách đang trống");
        } else {
            courses.forEach(System.out::println);
        }
    }

    void saveCourse(Scanner scanner) {
        Course course = new Course();
        course.setName(inputCourseName(scanner));
        course.setDuration(inputDuration(scanner));
        course.setInstructor(inputIntructor(scanner));
        boolean result = courseBusinessImp.addCourse(course);
        if (result) {
            System.out.println("thêm mới thành công");
        }else {
            System.err.println("thêm mới thất bại!");
        }
    }

    String inputCourseName(Scanner scanner) {
        System.out.println("nhập tên khóa học : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("khóa học không được để trống");
            } else {
                if (courseBusinessImp.checkCourseName(input)) {
                    System.err.println("Tên khóa học đã bị trùng lặp xin mời nhập tên khác");
                } else return input;

            }

        } while (true);
    }

    int inputDuration(Scanner scanner) {
        System.out.println("mời bạn nhập thời lương khóa học (tính theo tiếng)");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("Thời gian không được để trống");
            } else {
                if (Validator.isInt(input)) {
                    return Integer.parseInt(input);
                } else {
                    System.err.println("nhập thời gian là số nguyên dương");
                }
            }

        } while (true);
    }

    String inputIntructor(Scanner scanner) {
        System.out.println("nhập tên người chịu trách nhiệm môn : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("tên người phụ trách không được để trống");
            } else return input;
        } while (true);
    }
    void menuUpdateCourse(Scanner scanner) {
        System.out.println("Bạn hãy nhập Id course cần cập nhật sửa đổi :");
        do {String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("ID không được để trống ");
            }else {
                if (Validator.isInt(input)) {
                    int id = Integer.parseInt(input);
                    if (courseBusinessImp.checkCourseId(id)) {}else {
                        System.err.println();
                    }
                }else {
                    System.err.println("ID phải là số nguyen dương!");
                }
            }

        }while (true);
    }
}
