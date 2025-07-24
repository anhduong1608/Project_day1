package business.imp;

import business.StaticsticStuAndCouBusiness;
import dao.imp.StaticsticStuAndCourseImp;
import entity.StatisticStudentAndCourse;
import entity.StatisticStudentByCourse;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaticsticStuAndCouBusinessImp implements StaticsticStuAndCouBusiness {
    private final StaticsticStuAndCourseImp staticsticStuAndCouImp;

    public StaticsticStuAndCouBusinessImp() {
        staticsticStuAndCouImp = new StaticsticStuAndCourseImp();
    }

    @Override
    public StatisticStudentAndCourse getStatisticStudentAndCourse() {
        return staticsticStuAndCouImp.getStatisticStudentAndCourseById();
    }

    @Override
    public List<StatisticStudentByCourse> getStatisticStudentByCourse() {
        return staticsticStuAndCouImp.getStatisticStudentByCourse();
    }

    @Override
    public List<StatisticStudentByCourse> getStatisticStudentByCourseByAdmin() {
        return staticsticStuAndCouImp.getStatisticStudentByCourseByAdmin();
    }

    @Override
    public List<StatisticStudentByCourse> upTenCourse1() {
        return staticsticStuAndCouImp.upTenCourse();
    }


}
