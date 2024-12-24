package com.lagou.service;

import com.lagou.pojo.Course;

import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:10
 * @Description:
 */
public interface CourseService {
    public List<Course> findCourseList();
    public List<Course> findCourseByNameAndSatus(String courseName, String satus);
    public String saveCourseSalesInfo(Course course);
}
