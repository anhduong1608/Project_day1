package business.imp;

import business.CourseBusinessDao;
import dao.imp.CourseImp;
import entity.Course;
import entity.CourseEnrollment;

import java.util.List;

public class CourseBusinessImp implements CourseBusinessDao {
    private final CourseImp courseImp;

    public CourseBusinessImp() {
        courseImp = new CourseImp();
    }

    @Override
    public List<Course> getCourses() {
        return courseImp.findAll();
    }

    @Override
    public boolean addCourse(Course course) {
        return courseImp.insert(course);
    }

    @Override
    public boolean checkCourseName(String courseName) {
        return courseImp.isCourseName(courseName);
    }

    @Override
    public boolean checkCourseId(Integer courseId) {
        return courseImp.isCourseId(courseId);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseImp.findCourseById(courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseImp.update(course);
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return courseImp.delete(courseId);
    }

    @Override
    public List<Course> getCourseByName(String courseName) {
        return courseImp.findCourseByName(courseName);
    }

    @Override
    public List<CourseEnrollment> findCourseByStuId(Integer studentId) {
        return courseImp.findCourseByStuId(studentId);
    }
}
