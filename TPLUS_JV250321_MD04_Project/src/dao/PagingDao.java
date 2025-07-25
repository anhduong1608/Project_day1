package dao;

import entity.Course;

import java.util.List;

public interface PagingDao {
    int getTotalPagesCourse(int rowOfPage);
    List<Course> PagingCourse(int page, int rowOfPage);
}
