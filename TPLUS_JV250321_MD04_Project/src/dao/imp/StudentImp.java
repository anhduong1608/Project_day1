package dao.imp;

import dao.StudentDao;
import entity.Course;
import entity.Student;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentImp implements StudentDao {
    @Override
    public String getPasswordByEmailStudent(String email) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.get_pass_by_email(?)}");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return "";
    }

    @Override
    public boolean isEmailStudent(String email) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call trainingManagement.get_pass_by_email(?,?)}");
            stmt.setString(1, email);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();
            int pass = stmt.getInt(2);
            if (pass == 1) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(stmt, conn);
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_all_student()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.save_student(?,?,?,?,?,?)}");
            callSt.setString(1, student.getName());
            callSt.setDate(2, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(3, student.getEmail());
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.setString(6, student.getPassword());
            int rs = callSt.executeUpdate();
            if (rs > 0) {
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
    public Student getStudentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_student_by_id()}");
            callSt.setInt(1, id);
            rs = callSt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(callSt, conn);
        }
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.update_student(?,?,?,?,?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setDate(3, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(4, student.getEmail());
            callSt.setBoolean(5, student.isSex());
            callSt.setString(6, student.getPhone());
            callSt.setString(7, student.getPassword());
            int rs = callSt.executeUpdate();
            if (rs > 0) {
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
    public boolean isStudentId(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.is_student_id(?,?)}");
            callSt.setInt(1, id);
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
    public boolean deleteStudent(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.delete_student_by_id(?)}");
            callSt.setInt(1, id);
            int rs = callSt.executeUpdate();
            if (rs > 0) {
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
    public List<Student> getStudentByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_student_by_email(?)}");
            callSt.setString(1, email);
            rs = callSt.executeQuery();
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
            ConnectionDB.closeConnection(callSt, conn);
        }
        return students;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_student_by_name(?)}");
            callSt.setString(1, name);
            rs = callSt.executeQuery();
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
            ConnectionDB.closeConnection(callSt, conn);
        }
        return students;
    }

    @Override
    public List<Course> getCourseByStudentId(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call trainingManagement.find_course_enrollment(?)}");
            callSt.setInt(1, id);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(callSt, conn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return courses;
    }
}
