package com.lagou.service;

import com.lagou.pojo.Course;
import com.lagou.service.impl.CourseServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:53
 * @Description:
 */
public class TestCourseService {

    CourseServiceImpl courseService = new CourseServiceImpl();

    @Test
    public void testCourseService() {
        List<Course> courseList = courseService.findCourseList();
        System.out.println(courseList);
    }
}