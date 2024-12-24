package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDAO;
import com.lagou.dao.impl.CourseDAOImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:11
 * @Description:
 */
public class CourseServiceImpl implements CourseService {

    CourseDAO dao = new CourseDAOImpl();

    @Override
    public List<Course> findCourseList() {
        List<Course> courseList = dao.findCourseList();
        return courseList;
    }

    @Override
    public List<Course> findCourseByNameAndSatus(String courseName, String satus) {
        List<Course> courseList = dao.findCourseByNameAndSatus(courseName, satus);
        return courseList;
    }

    @Override
    public String saveCourseSalesInfo(Course course) {
//        补全课程营销信息
        String dateFormart = DateUtils.getDateFormart();
        course.setCreate_time(dateFormart);
        course.setUpdate_time(dateFormart);
        course.setStatus(1); //上架
//        2.执行插入操作
        int result = dao.saveCourseSalesInfo(course);

        return result > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }
}