package dao.imp;

import dao.PagingDao;
import entity.Course;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagingImp implements PagingDao {
    @Override
    public int getTotalPagesCourse(int rowOfPage) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL trainingManagement.total_page_course(?,?)}");
            cs.setInt(1, rowOfPage);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.execute();
            int totalPage = cs.getInt(2);
            return totalPage;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(cs, conn);
        }
        return 0;
    }

    @Override
    public List<Course> PagingCourse(int page, int rowOfPage) {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL trainingManagement.paging_course(?,?)}");
            cs.setInt(1, page);
            cs.setInt(2, rowOfPage);
           rs= cs.executeQuery();
           while (rs.next()) {
               Course course = new Course();
               course.setId(rs.getInt(1));
               course.setName(rs.getString(2));
               course.setDuration(rs.getInt(3));
               course.setInstructor(rs.getString(4));
               course.setCreateAt(rs.getDate(5).toLocalDate());
               courses.add(course);
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(cs, conn);
        }
        return courses;
    }
}
