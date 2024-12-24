package com.lagou.dao;

import com.lagou.pojo.Course;

import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:09
 * @Description:
 */
//课程模块DAO接口
public interface CourseDAO {
    public List<Course> findCourseList();
    public List<Course> findCourseByNameAndSatus(String courseName, String satus);
    public int saveCourseSalesInfo(Course course);
}
