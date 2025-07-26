package presentation.adminofmenu;

import business.imp.StaticsticStuAndCouBusinessImp;
import entity.StatisticStudentAndCourse;
import entity.StatisticStudentByCourse;

import java.util.List;
import java.util.Scanner;

public class MenuStatistic {
    private final StaticsticStuAndCouBusinessImp staticsticStuAndCouBusinessImp;

    public MenuStatistic() {
        staticsticStuAndCouBusinessImp = new StaticsticStuAndCouBusinessImp();
    }

    public void menuStatistic(Scanner sc) {
        boolean exit = false;
        do {
            try {
                System.out.println("1. Thống kê lượng sinh viên và khóa học");
                System.out.println("2. Thống kê tổng số học viên theo từng khóa");
                System.out.println("3. Thống kê top 5 khóa học đông sinh viên nhất");
                System.out.println("4. Liệt kê khóa học có trên 10 học viên");
                System.out.print("5. thoát");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        statisticStudentAndCourse(sc);
                        break;
                    case 2:
                        statisticStudentByCourse(sc);
                        break;
                    case 3:
                        limitFive(sc);
                        break;
                    case 4:
                        upTen(sc);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default: {
                        System.err.println("Mời nhập đúng số 1 đến 5");
                    }

                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số thích hợp!");
            }

        } while (!exit);
    }

    void statisticStudentAndCourse(Scanner sc) {
        StatisticStudentAndCourse statisticStudentAndCourse = staticsticStuAndCouBusinessImp.getStatisticStudentAndCourse();
        if (statisticStudentAndCourse == null) {
            System.err.println("giữ liệu trống");
        } else
            System.out.println("====================================================");
        System.out.println(statisticStudentAndCourse);
        System.out.println("====================================================");
    }

    void statisticStudentByCourse(Scanner sc) {
        List<StatisticStudentByCourse> statisticStudentByCourses = staticsticStuAndCouBusinessImp.getStatisticStudentByCourse();
        if (statisticStudentByCourses.isEmpty()) {
            System.err.println("giữ liệu trống");
        } else {
            System.out.println("Danh sách thống kê theo khóa học : ");
            System.out.println("=========================================================================");
            statisticStudentByCourses.forEach(System.out::println);
        }
    }

    void limitFive(Scanner sc) {
        List<StatisticStudentByCourse> statisticStudentByCourses1 = staticsticStuAndCouBusinessImp.getStatisticStudentByCourseByAdmin();
        if (statisticStudentByCourses1.isEmpty()) {
            System.err.println("Danh sách hiện không có đối tượng nào");
        } else {
            System.out.println("năm khóa học hiện nhiều học viên học nhất : ");
            System.out.println("=========================================================================");
            statisticStudentByCourses1.forEach(System.out::println);
        }
    }

    void upTen(Scanner sc) {
        List<StatisticStudentByCourse> statisticStudentByCourses = staticsticStuAndCouBusinessImp.upTenCourse1();
        if (statisticStudentByCourses.isEmpty()) {
            System.err.println("Danh sách rỗng!");
        } else {
            System.out.println("Danh sách có các khóa có học sinh hơn 10 là : ");
            System.out.println("=========================================================================");
            statisticStudentByCourses.forEach(System.out::println);
        }
    }
}
