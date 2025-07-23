package dao;

import entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();
    boolean insert(Course course);
    boolean isCourseName(String courseName);
    boolean isCourseId(Integer courseId);
    Course findCourseById(Integer courseId);
    boolean update(Course course);
}
