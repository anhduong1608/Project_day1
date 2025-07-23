package business;

import entity.Course;

import java.util.List;

public interface CourseBusinessDao {
    List<Course> getCourses();
    boolean addCourse(Course course);
    boolean checkCourseName(String courseName);
    boolean checkCourseId(Integer courseId);
    Course getCourseById(Integer courseId);
    boolean updateCourse(Course course);
    boolean deleteCourse(Integer courseId);
    List<Course> getCourseByName(String courseName);

}
