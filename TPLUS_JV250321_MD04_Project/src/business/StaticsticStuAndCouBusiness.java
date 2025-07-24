package business;

import entity.StatisticStudentAndCourse;
import entity.StatisticStudentByCourse;

import java.util.List;

public interface StaticsticStuAndCouBusiness {
    StatisticStudentAndCourse getStatisticStudentAndCourse();
    List<StatisticStudentByCourse> getStatisticStudentByCourse();
    List<StatisticStudentByCourse> getStatisticStudentByCourseByAdmin();
}
