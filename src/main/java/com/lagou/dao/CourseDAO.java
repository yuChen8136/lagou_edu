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
//    查询课程列表信息
    public List<Course> findCourseList();
//    根据条件查询课程列表信息
    public List<Course> findCourseByNameAndSatus(String courseName, String satus);
//    新建/保存课程营销信息
    public int saveCourseSalesInfo(Course course);
//    根据ID查询课程信息
    public Course findCourseById(int id);
//    修改课程营销信息
    public int updateCourseSelesInfo(Course course);
//    修改课程状态
    public int updateCourseStatus(Course course);
}
