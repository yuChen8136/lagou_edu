package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:12
 * @Description:
 */
@WebServlet("/course")
public class CourseServlet extends BaseServlet {
//    查询课程信息列表
    public void findCourseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接收参数
//        2.业务处理
        CourseService cs = new CourseServiceImpl();
        List<Course> courseList = cs.findCourseList();
//        响应结果
//        指定要转换的字段
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                "id","course_name","price","status","sort_num");
        String result = JSON.toJSONString(courseList, filter);
        resp.getWriter().print(result);
    }
//    根据条件查询课程信息
    public void findCourseByNameAndSatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService cs = new CourseServiceImpl();
        String courseName = req.getParameter("courseName");
        String courseSatus = req.getParameter("status");
        List<Course> courseList = cs.findCourseByNameAndSatus(courseName, courseSatus);
//        响应结果
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                "id","course_name","price","status","sort_num");
        String result = JSON.toJSONString(courseList, filter);
        resp.getWriter().print(result);
    }


}