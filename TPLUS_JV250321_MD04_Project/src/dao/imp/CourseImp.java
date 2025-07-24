package dao.imp;

import dao.CourseDao;
import entity.Course;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseImp implements CourseDao {
    @Override
    public List<Course> findAll() {
        Connection conn = null;
        CallableStatement cs = null;
        List<Course> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call trainingManagement.find_all_course()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getDate("create_at").toLocalDate());
                list.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(cs, conn);
        }
        return list;
    }

    @Override
    public boolean insert(Course course) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{call trainingManagement.save_course(?,?,?)}");
            cs.setString(1, course.getName());
            cs.setInt(2, course.getDuration());
            cs.setString(3, course.getInstructor());
            int affected = cs.executeUpdate();
            if (affected > 0) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(cs, conn);
        }
        return false;
    }

    @Override
    public boolean isCourseName(String courseName) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.is_course_name(?,?)}");
            callSt.setString(1, courseName);
            callSt.registerOutParameter(2, java.sql.Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(2);
            if (result == 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public boolean isCourseId(Integer courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.is_course_id(?,?)}");
            callSt.setInt(1, courseId);
            callSt.registerOutParameter(2, java.sql.Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(2);
            if (result == 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public Course findCourseById(Integer courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Course course = new Course();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_course_by_id(?)}");
            callSt.setInt(1, courseId);
            rs = callSt.executeQuery();
            if (rs.next()) {
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getDate("create_at").toLocalDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(null, conn);
        }
        return course;
    }

    @Override
    public boolean update(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.update_course(?,?,?,?,?)}");
            callSt.setInt(1, course.getId());
            callSt.setString(2, course.getName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());
            callSt.setDate(5, Date.valueOf(course.getCreateAt()));
            int affected = callSt.executeUpdate();
            if (affected > 0) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public boolean delete(Integer courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.delete_by_id(?)}");
            callSt.setInt(1, courseId);
            int affected = callSt.executeUpdate();
            if (affected > 0) {
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return false;
    }

    @Override
    public List<Course> findCourseByName(String courseName) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_course_by_name(?)}");
            callSt.setString(1, courseName);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getDate("create_at").toLocalDate());
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return courseList;
    }
}
