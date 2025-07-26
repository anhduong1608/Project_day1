package presentation.adminofmenu;

import business.imp.StudentBusinessImp;
import dao.imp.StudentImp;
import entity.Student;
import validate.PasswordUtil;
import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuManagementStudent {
    private final StudentBusinessImp studentBusinessImp;
    private final StudentImp studentImp;
    private int page = 1;
    private final int ROW_OF_PAGE = 5;


    public MenuManagementStudent() {
        studentBusinessImp = new StudentBusinessImp();
        studentImp = new StudentImp();


    }

    public void displayMenuManagementStudent(Scanner scanner) {
        boolean exits = false;
        do {
            try {
                System.out.println("=======================STUDENT MANAGEMENT MENU==========================");
                System.out.println("1. Hiển thị danh sách học viên");
                System.out.println("2. Thêm mới học viên");
                System.out.println("3. Chỉnh sửa thông tin học viên(hiển thị menu con cho phép chỉnh sửa)");
                System.out.println("4. Xóa học viên theo ID(có xác nhận trước khi xóa)");
                System.out.println("5. Tìm kiếm học viên theo tên, email hoặc mã id(tìm kiếm tương đối)");
                System.out.println("6. Sắp xếp học viên (theo tên|ID - tăng dần|giảm dần) ");
                System.out.println("7. Thoát menu");
                System.out.println("========================================================================");
                System.out.print("Lựa chọn của bạn là : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
//                        displayStudent(scanner);
                        pagingStudentMenu(scanner);
                        break;
                    case 2:
                        creatStudent(scanner);
                        break;
                    case 3:
                        updateStudentMenu(scanner);
                        break;
                    case 4:
                        deleteStudent(scanner);
                        break;
                    case 5:
                        searchStudentMenu(scanner);
                        break;
                    case 6:
                        sortStudentMenu(scanner);
                        break;
                    case 7:
                        exits = true;
                        break;
                    default: {
                        System.err.println("mời nhập lựa chọn từ 1 đến 7");
                    }
                }

            } catch (Exception e) {
                System.err.println("mời nhập vào số dương thích hợp!");
            }

        } while (!exits);
    }

    void displayStudent(Scanner scanner) {
        List<Student> students = studentBusinessImp.getAllStudents();
        if (students.isEmpty()) {
            System.err.println("danh sách trống");
        } else {
            System.out.println("danh sách học sinh : \n");
            headStudent();
            students.forEach(System.out::println);
            System.out.println("======================================================================================================");
        }
    }

    String inputNameStudent(Scanner scanner) {
        System.out.println("Nhập tên sinh viên : ");
        do {
            String inputName = scanner.nextLine();
            if (inputName.trim().isEmpty()) {
                System.err.println("Tên không được để trống");
            } else {
                return inputName;
            }
        } while (true);
    }

    LocalDate inputDob(Scanner scanner) {
        System.out.println("Nhập ngày tháng năm sinh (dd/MM/yyyy) : ");
        do {
            String inputDob = scanner.nextLine();
            if (inputDob.trim().isEmpty()) {
                System.err.println("ngày tháng không được để trống");
            } else {
                if (Validator.isLocaldate(inputDob)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate inputDate = LocalDate.parse(inputDob, formatter);
                    return inputDate;
                } else {
                    System.err.println("Mời nhập ngày tháng sinh nhật đúng định dạng dd/MM/yyyy");
                }
            }
        } while (true);
    }

    String inputEmail(Scanner scanner) {
        System.out.println("Mời nhập email");
        do {
            String inputEmail = scanner.nextLine();
            if (inputEmail.trim().isEmpty()) {
                System.err.println("mời nhập email không được để trống!");
            } else {
                if (Validator.validateEmail(inputEmail)) {
                    if (studentBusinessImp.isEmailStudent(inputEmail)) {
                        System.err.println("Email đã được sử dụng vui lòng đăng ký bằng email khác");
                    } else {
                        return inputEmail;
                    }
                } else {
                    System.err.println("Mời nhập email đúng định dạng!");
                }
            }

        } while (true);
    }

    boolean inputSex(Scanner scanner) {
        System.out.println("Mời bạn nhập giới tính Nam(true)|Nữ(false)");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("giới tính không được để trống");
            } else {
                if (Validator.isBoolean(input)) {
                    return Boolean.parseBoolean(input);
                } else {
                    System.err.println("Mời nhập true|false");
                }
            }

        } while (true);
    }

    String inputPhone(Scanner scanner) {
        System.out.println("Nhập số điện thoại của bạn : ");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("Số điện thoại không được để trống");
            } else {
                if (Validator.isPhoneNumber(input)) {
                    return input;
                } else {
                    System.err.println("mời nhập đúng định dạng SDT");
                }
            }
        } while (true);
    }

    String inputPass(Scanner scanner) {
        System.out.println("mời nhập pass đăng ký :");
        do {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.err.println("Pass không được để trống");
            } else {
                return PasswordUtil.hashPassword(input);
            }

        } while (true);
    }

    void creatStudent(Scanner scanner) {
        Student student = new Student();
        student.setName(inputNameStudent(scanner));
        student.setDob(inputDob(scanner));
        student.setEmail(inputEmail(scanner));
        student.setSex(inputSex(scanner));
        student.setPhone(inputPhone(scanner));
        student.setPassword(inputPass(scanner));
        boolean result = studentBusinessImp.addStudent(student);
        if (result) {
            System.out.println("Thêm mới thành công!");
        } else {
            System.err.println("Thêm mới thất bại!");
        }
    }

    void updateStudentMenu(Scanner scanner) {

        do {
            System.out.println("Nhập ID sinh viên muốn cập nhật chỉnh sửa thông tin :");
            String id = scanner.nextLine();
            if (id.trim().isEmpty()) {
                System.err.println("nhập ID không được để trống");
            } else {
                if (Validator.isInt(id)) {
                    int inputID = Integer.parseInt(id);
                    if (studentBusinessImp.isStudentId(inputID)) {
                        Student student = studentBusinessImp.getStudentById(inputID);
                        System.out.println("Đối tượng bạn muốn sửa thông tin :\n");
                        headStudent();
                        System.out.println(student);
                        boolean exits = false;
                        do {
                            try {
                                System.out.println("1. Cập nhật tên");
                                System.out.println("2. Cập nhật ngày tháng năm sinh");
                                System.out.println("3. Cập nhật email");
                                System.out.println("4. Cập nhật giới tính");
                                System.out.println("5. Cập nhật số điện thoại");
                                System.out.println("6. Cập nhật mật khẩu");
                                System.out.println("7. thoát");
                                System.out.print("Mời bạn lựa chọn : ");
                                int choice = Integer.parseInt(scanner.nextLine());
                                switch (choice) {
                                    case 1:
                                        student.setName(inputNameStudent(scanner));
                                        break;
                                    case 2:
                                        student.setDob(inputDob(scanner));
                                        break;
                                    case 3:
                                        student.setEmail(inputEmail(scanner));
                                        break;
                                    case 4:
                                        student.setSex(inputSex(scanner));
                                        break;
                                    case 5:
                                        student.setPhone(inputPhone(scanner));
                                        break;
                                    case 6:
                                        student.setPassword(inputPass(scanner));
                                        break;
                                    case 7:
                                        exits = true;
                                        break;
                                    default: {
                                        System.err.println("Mời bạn nhập số nguyên từ 1 đến 7");
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("Mời bạn nhập vào số nguyên dương phù hợp");
                            }
                        } while (!exits);
                        boolean result = studentBusinessImp.updateStudent(student);
                        if (result) {
                            System.out.println("Cập nhật thành công!");
                            break;
                        } else {
                            System.err.println("Cập nhật thất bại!");
                            break;
                        }
                    } else {
                        System.err.println("ID không có học sinh phù hợp bạn có muốn tìm tiếp Y/N");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                } else {
                    System.err.println("Nhập ID là số nguyên dương");
                }
            }

        } while (true);
    }

    void deleteStudent(Scanner scanner) {

        do {
            System.out.println("Nhập ID sinh viên muốn xóa :");
            String inputID = scanner.nextLine();
            if (inputID.trim().isEmpty()) {
                System.err.println("ID không được để trống !");
            } else {
                if (Validator.isInt(inputID)) {
                    int idStudent = Integer.parseInt(inputID);
                    if (studentBusinessImp.isStudentId(idStudent)) {
                        Student student = studentBusinessImp.getStudentById(idStudent);
                        System.out.println("đối tượng bạn muốn xóa");
                        headStudent();
                        System.out.println(student);
                        System.out.println("Bạn có muốn xóa không Y/N ");
                        String deleteChoice = scanner.nextLine();
                        if (deleteChoice.equalsIgnoreCase("y")) {
                            boolean result = studentBusinessImp.deleteStudent(idStudent);
                            if (result) {
                                System.out.println("Xóa thành công!");
                                break;
                            } else {
                                System.err.println("xóa không thành công!");
                            }
                        } else break;
                    } else {
                        System.out.println("ID không có đối tượng sinh viên nào phù hơp bạn có muốn tìm tiếp không Y/N ");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                } else {
                    System.err.println("Mời nhập ID là số nguyên dương!");
                }
            }

        } while (true);
    }

    void searchStudentMenu(Scanner scanner) {
        boolean exits = false;
        do {
            try {
                System.out.println("====== SEARCH BY ======");
                System.out.println("1. Tìm kiếm theo tên");
                System.out.println("2. Tìm kiếm theo email");
                System.out.println("3. Tìm kiếm theo ID");
                System.out.println("4. thoát");
                System.out.println("=======================");
                System.out.print("lựa chọn của bạn :  ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayStudentByName(scanner);
                        break;
                    case 2:
                        displayStudentByEmail(scanner);
                        break;
                    case 3:
                        displayStudentByID(scanner);
                        break;
                    case 4:
                        exits = true;
                        break;
                    default: {
                        System.err.println("Mời bạn chọn từ 1 đến 4");
                    }
                }
            } catch (NullPointerException npe) {
                System.err.println("mời bạn nhập vào dạng số thích hợp");
            }

        } while (!exits);
    }

    void displayStudentByName(Scanner scanner) {
        System.out.println("Nhập tên tương đối cần tìm : ");
        do {
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.err.println("Tên nhập để tìm không được để trống");
            } else {
                List<Student> students = studentBusinessImp.getStudentByName(name);
                if (students.isEmpty()) {
                    System.err.println("Danh sách tìm trống không có đối tượng phù hợp");
                    break;
                } else {
                    System.out.println("Danh sách được tìm thấy gồm : \n");
                    headStudent();
                    students.forEach(System.out::println);
                    break;
                }
            }
        } while (true);
    }

    void displayStudentByID(Scanner scanner) {
        System.out.println("Mời nhập ID cần tìm : ");
        do {
            String inputID = scanner.nextLine();
            if (inputID.trim().isEmpty()) {
                System.err.println("Mời nhập ID cần tìm không được để trống!");
            } else {
                if (Validator.isInt(inputID)) {
                    int idStudent = Integer.parseInt(inputID);
                    Student student = studentBusinessImp.getStudentById(idStudent);
                    if (student == null) {
                        System.err.println("không tìm thấy đối tượng nào");
                        break;
                    } else {
                        System.out.println("Sinh viên tìm thấy là : \n");
                        headStudent();
                        System.out.println(student);
                        break;
                    }
                } else {
                    System.err.println("ID phải là dạng số nguyên dương!");
                }
            }

        } while (true);
    }

    void displayStudentByEmail(Scanner scanner) {
        System.out.println("Mời nhập email tương đối cần tìm kiếm : ");
        do {
            String inputEmail = scanner.nextLine();
            if (inputEmail.trim().isEmpty()) {
                System.err.println("Mời nhập email không được để email trống!");
            } else {
                List<Student> students = studentBusinessImp.getStudentByEmail(inputEmail);
                if (students.isEmpty()) {
                    System.err.println("Không tìm thấy sinh viên nào!");
                    break;
                } else {
                    System.out.println("Danh sách sinh viên tìm thấy : \n");
                    headStudent();
                    students.forEach(System.out::println);
                    break;
                }
            }

        } while (true);
    }

    void sortStudentMenu(Scanner scanner) {
        List<Student> students = studentBusinessImp.getAllStudents();
        if (students.isEmpty()) {
            System.err.println("Danh sách hiện đang trống!");
        } else {
            boolean exits = false;
            do {
                try {
                    System.out.println("========== SẮP XẾP THEO THỨ tự ==========");
                    System.out.println("1. Sắp xếp theo tên tăng dần");
                    System.out.println("2. Sắp xếp theo tên giảm dần");
                    System.out.println("3. Sắp xếp theo ID tăng dần");
                    System.out.println("4. Sắp xếp theo ID giảm dần");
                    System.out.println("5. thoát");
                    System.out.println("=========================================");
                    System.out.print("lựa chọn của bạn là : ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            headStudent();
                            students.stream().sorted(Comparator.comparing(student -> {
                                String[] parts = student.getName().split(" ");
                                return parts[parts.length - 1].toLowerCase();
                            })).forEach(System.out::println);
                            break;
                        case 2:
                            headStudent();
                            students.stream()
                                    .sorted(Comparator.comparing((Student student) -> {
                                        String[] parts = student.getName().split(" ");
                                        return parts[parts.length - 1].toLowerCase();
                                    }, Comparator.reverseOrder()))
                                    .forEach(System.out::println);
                            break;
                        case 3:
                            headStudent();
                            students.stream().sorted(Comparator.comparing(Student::getId)).forEach(System.out::println);
                            break;
                        case 4:
                            headStudent();
                            students.stream().sorted(Comparator.comparing(Student::getId).reversed()).forEach(System.out::println);
                            break;
                        case 5:
                            exits = true;
                            break;
                        default: {
                            System.err.println("Mời bạn nhập lựa chọn từ 1 đền 5");
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.err.println("Mời bạn nhập vào số nguyên dương phù hợp");
                }

            } while (!exits);
        }
    }

    public void headStudent() {
        System.out.printf(" %-5s | %-25s | %-20s | %-5s | %-20s | %-15s \n",
                "ID", "Name", "Email", "Sex", "Phone", "Created At");
        System.out.println("=====================================================================================================================");
    }

    public void pagingStudentMenu(Scanner scanner) {
        int totalPage = studentImp.studentOfTotalPage(ROW_OF_PAGE);
        List<Student> students = studentImp.pagingStudent(page, ROW_OF_PAGE);
        while (true) {
            String next = "";
            String pev = "";
            System.out.println("============ Danh sách sinh viên ============ ");
            System.out.printf("%-5s | %-5s | %-25s | %-20s | %-20s | %-5s | %-20s | %-15s \n",
                    "NO", "ID", "Name", "DOB", "Email", "Sex", "Phone", "Created At");
            System.out.println("====================================================================================================================");
            int stt = (page - 1) * ROW_OF_PAGE + 1;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Student student : students) {
                System.out.printf(" %-5d |%-5d | %-25s |%-20s| %-20s | %-5s | %-20s | %-15s\n", stt, student.getId(), student.getName(), student.getDob().format(dtf), student.getEmail(), student.isSex() ? "Nam" : "Nữ", student.getPhone(), student.getCreateAt().format(dtf));
                stt++;
            }
            if (page > 1) {
                pev = "[P]pev";
            }
            if (page < totalPage) {
                next = "[N]next";
            }
            String pagefooter = "Page : " + " " + next + " ";
            for (int i = 1; i <= totalPage; i++) {
                if (page == i) {
                    pagefooter += "[" + i + "]" + " ";
                } else {
                    pagefooter += i + " ";
                }
                pagefooter += pev;
            }
            System.out.println(pagefooter);
            System.out.println("Mời bạn nhập trang cần chọn hoặc N or P ngoài ra phím bất kì sẽ trở về menu trước :");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("n")) {
                page++;
            } else if (input.equalsIgnoreCase("p")) {
                page--;
            } else {
                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= totalPage) {
                        page = choice;
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }


    }
}

