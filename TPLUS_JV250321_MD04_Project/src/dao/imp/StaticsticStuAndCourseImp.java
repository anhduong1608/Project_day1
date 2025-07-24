package dao.imp;

import dao.StaticsticStudentAndCourseDao;
import entity.StatisticStudentAndCourse;
import entity.StatisticStudentByCourse;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaticsticStuAndCourseImp implements StaticsticStudentAndCourseDao {
    @Override
    public StatisticStudentAndCourse getStatisticStudentAndCourseById() {
        Connection connection = null;
        CallableStatement callSt = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call trainingManagement.total_course_and_student()}");
            resultSet = callSt.executeQuery();
            if (resultSet.next()) {
                StatisticStudentAndCourse statisticStudentAndCourse = new StatisticStudentAndCourse();
                statisticStudentAndCourse.setTotalCourse(resultSet.getInt("total_course"));
                statisticStudentAndCourse.setTotalStudent(resultSet.getInt("total_student"));
                return statisticStudentAndCourse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, connection);
        }
        return null;
    }

    @Override
    public List<StatisticStudentByCourse> getStatisticStudentByCourse() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet resultSet = null;
        List<StatisticStudentByCourse> statisticStudentByCourses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL trainingManagement.total_student_by_course()}");
            resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                StatisticStudentByCourse statisticStudentByCourse = new StatisticStudentByCourse();
                statisticStudentByCourse.setCourseId(resultSet.getInt("id"));
                statisticStudentByCourse.setCourseName(resultSet.getString("name"));
                statisticStudentByCourse.setStudentTotal(resultSet.getInt("student_total"));
               statisticStudentByCourses.add(statisticStudentByCourse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return statisticStudentByCourses;
    }

    @Override
    public List<StatisticStudentByCourse> getStatisticStudentByCourseByAdmin() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<StatisticStudentByCourse> statisticStudentByCourse = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.limit_five_course()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                StatisticStudentByCourse statisticStudentByCourse1 = new StatisticStudentByCourse();
                statisticStudentByCourse1.setCourseId(rs.getInt("id"));
                statisticStudentByCourse1.setCourseName(rs.getString("name"));
                statisticStudentByCourse1.setStudentTotal(rs.getInt("student_total"));
                statisticStudentByCourse.add(statisticStudentByCourse1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return statisticStudentByCourse;
    }

}
