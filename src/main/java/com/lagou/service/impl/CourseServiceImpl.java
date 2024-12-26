package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDAO;
import com.lagou.dao.impl.CourseDAOImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Course findCourseById(Integer id) {
        return dao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {
        int row = dao.updateCourseSelesInfo(course);
        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        int row = dao.updateCourseStatus(course);
        Map<String, Integer> map = new HashMap<>();

        if (row > 0) {
            if (course.getStatus() == 0) {
                map.put("status",0);
            } else {
                map.put("status",1);
            }
        }
        return map;
    }
}