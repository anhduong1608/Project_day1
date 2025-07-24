package presentation.adminofmenu;

import business.imp.CourseBusinessImp;
import entity.Course;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuManagementCourse {
    private final CourseBusinessImp courseBusinessImp;

    public MenuManagementCourse() {
        courseBusinessImp = new CourseBusinessImp();
    }

    public void displayMenuManagementCourse(Scanner scanner) {
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
                    case 2:
                        saveCourse(scanner);
                        break;
                    case 3:
                        menuUpdateCourse(scanner);
                        break;
                    case 4:
                        deleteCourse(scanner);
                        break;
                    case 5:
                        displayByNameCourse(scanner);
                        break;
                    case 6:
                        sortCourseMenu(scanner);
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

    public void displayCourse(Scanner scanner) {
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
        } else {
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

        do {
            System.out.println("Bạn hãy nhập Id course cần cập nhật sửa đổi :");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("ID không được để trống ");
            } else {
                if (Validator.isInt(input)) {
                    int id = Integer.parseInt(input);
                    if (courseBusinessImp.checkCourseId(id)) {
                        Course course = courseBusinessImp.getCourseById(id);
                        System.out.println("Đối tượng bạn muốn cập nhật : \n");
                        System.out.println(course);
                        boolean exits = false;
                        do {
                            try {
                                System.out.println("1. Cập nhật tên khóa học : ");
                                System.out.println("2. Cập nhật thời gian khóa học : ");
                                System.out.println("3. Cập nhật giáo viên phụ trách : ");
                                System.out.println("4. Quay lại menu chính : ");
                                System.out.print("Lựa chọn của bạn : ");
                                int choice = Integer.parseInt(scanner.nextLine());
                                switch (choice) {
                                    case 1:
                                        course.setName(inputCourseName(scanner));
                                        break;
                                    case 2:
                                        course.setDuration(inputDuration(scanner));
                                        break;
                                    case 3:
                                        course.setInstructor(inputIntructor(scanner));
                                        break;
                                    case 4:
                                        exits = true;
                                        break;
                                    default: {
                                        System.err.println("Mời nhập lựa chọn 1 đến 5");
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("Mời nhập số nguyên dương thích hợp!");
                            }

                        } while (!exits);
                        boolean result = courseBusinessImp.updateCourse(course);
                        if (result) {
                            System.out.println("Cập nhật thành công!");
                            break;
                        } else {
                            System.err.println("Cập nhật không thành công!");
                            break;
                        }
                    } else {
                        System.err.println("ID không có khóa học nào phù hợp bạn có muốn nhập tìm kiềm lại không Y/N");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("n")) {
                            break;
                        }

                    }
                } else {
                    System.err.println("ID phải là số nguyen dương!");
                }
            }

        } while (true);
    }

    void deleteCourse(Scanner scanner) {

        do {
            System.out.println("Nhập ID khóa học muốn xóa : ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("ID muốn xóa không được để trống :");
            } else {
                if (Validator.isInt(input)) {
                    int id = Integer.parseInt(input);
                    if (courseBusinessImp.checkCourseId(id)) {
                        Course course = courseBusinessImp.getCourseById(id);
                        System.out.println("Đối tượng bạn muốn xóa : ");
                        System.out.println(course);
                        System.out.println("Bạn xác nhận muốn xóa hay không Y/N");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            boolean result = courseBusinessImp.deleteCourse(id);
                            if (result) {
                                System.out.println("Xóa khóa học thành công!");
                                break;
                            } else {
                                System.err.println("xóa đối tượng không thành công!");
                                break;
                            }
                        } else break;

                    } else {
                        System.err.println("ID không chính xác hoặc không tồn tai bạn có muốn tiếp tục tìm Y/N : ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                } else {
                    System.err.println("Mời nhập ID là số nguyên dương");
                }
            }

        } while (true);
    }

    void displayByNameCourse(Scanner scanner) {
        System.out.println("Mời bạn nhập tên khóa học tương đối cần tìm : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("Nhập tên cần tìm không được để trống !");

            } else {
                List<Course> courses = courseBusinessImp.getCourseByName(input);
                if (courses.isEmpty()) {
                    System.err.println("Tên hiện không có đối tượng nào phù hợp với tên cần tìm");
                    break;
                } else {
                    System.out.println("Danh sách bạn muốn tìm : ");
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                    break;
                }

            }

        } while (true);
    }

    void sortCourseMenu(Scanner scanner) {
        List<Course> courses = courseBusinessImp.getCourses();
        boolean exits = false;
        do {
            try {
                System.out.println("1. Sắp xêp tăng dần theo ID");
                System.out.println("2. Sắp xếp giảm dần theo ID");
                System.out.println("3. Sắp xếp tăng dần theo tên");
                System.out.println("4. Sắp xếp giảm dần theo tên");
                System.out.println("5. thoát ra menu");
                System.out.print("lựa chọn của bạn : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        courses.stream().sorted(Comparator.comparing(Course::getId)).forEach(System.out::println);
                        break;
                    case 2:
                        courses.stream().sorted(Comparator.comparing(Course::getId).reversed()).forEach(System.out::println);
                        break;
                    case 3:
                        courses.stream().sorted(Comparator.comparing(Course::getName)).forEach(System.out::println);
                        break;
                    case 4:
                        courses.stream().sorted(Comparator.comparing(Course::getName).reversed()).forEach(System.out::println);
                        break;
                    case 5:
                        exits = true;
                        break;
                    default: {
                        System.err.println("nhập số dương từ 1 đên 5");
                    }
                }
            } catch (Exception e) {
                System.err.println("mời bạn nhập số nguyên dương thích hợp!");
            }

        } while (!exits);

    }

}
