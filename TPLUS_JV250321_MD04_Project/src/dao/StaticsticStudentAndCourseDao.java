package dao;

import entity.StatisticStudentAndCourse;
import entity.StatisticStudentByCourse;

import java.util.List;

public interface StaticsticStudentAndCourseDao {
    StatisticStudentAndCourse getStatisticStudentAndCourseById();
    List<StatisticStudentByCourse> getStatisticStudentByCourse();
    List<StatisticStudentByCourse> getStatisticStudentByCourseByAdmin();


}
