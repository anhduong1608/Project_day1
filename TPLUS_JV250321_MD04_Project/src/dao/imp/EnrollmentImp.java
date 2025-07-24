package dao.imp;

import dao.EnrollmentDao;
import entity.EnrollAndSudentAndCourse;
import entity.Enrollment;
import entity.Student;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentImp implements EnrollmentDao {
    @Override
    public boolean addEnrollment(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.save_enrollment(?,?)}");
            stmt.setInt(1, enrollment.getStudent_id());
            stmt.setInt(2, enrollment.getCourseId());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return false;
    }

    @Override
    public List<Student> findStudentByCourse(int courseId) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.find_student_by_course(?)}");
            stmt.setInt(1, courseId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return students;
    }

    @Override
    public boolean adminAppectEnrollment(int enrollmentId) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{CALL trainingManagement.admin_appect_enrollment(?)}");
            stmt.setInt(1, enrollmentId);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return false;
    }

    @Override
    public List<EnrollAndSudentAndCourse> findAllEnrollment() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<EnrollAndSudentAndCourse> enrollments = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.find_all_enrollment()}");
            rs = stmt.executeQuery();
            while (rs.next()) {
                EnrollAndSudentAndCourse enrollment = new EnrollAndSudentAndCourse();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudent_id(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setEnrollmentAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollment.setStatus(rs.getString("status"));
                enrollment.setCourseName(rs.getString("course_name"));
                enrollment.setStudentName(rs.getString("student_name"));
                enrollments.add(enrollment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return enrollments;
    }
}
