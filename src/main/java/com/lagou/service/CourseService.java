package com.lagou.service;

import com.lagou.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * @Auther: username
 * @Date: 12/21/24 15:10
 * @Description:
 */
public interface CourseService {
    public List<Course> findCourseList();
    public List<Course> findCourseByNameAndSatus(String courseName, String satus);
    public String saveCourseSalesInfo(Course course);
//    根据ID查询营销课程信息
    public Course findCourseById(Integer id);
//    修改营销信息
    public String updateCourseSalesInfo(Course course);
//    修改课程状态
    public Map<String, Integer> updateCourseStatus(Course course);
}
