package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
//    根据ID获取课程信息
    public void findCourseById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService cs = new CourseServiceImpl();
        Course course = cs.findCourseById(Integer.parseInt(req.getParameter("id")));

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                Course.class,
                "id",
                "course_name",
                "brief",
                "teacher_name",
                "teacher_info",
                "preview_first_field",
                "preview_second_field",
                "discounts",
                "price",
                "price_tag",
                "course_img_url",
                "share_image_title",
                "share_title",
                "share_description",
                "course_description",
                "STATUS"
                );
        String result = JSON.toJSONString(course, filter);
        resp.getWriter().print(result);
    }
//    修改课程状态
    public void updateCourseStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取参数
        String status = req.getParameter("status");
        CourseService cs = new CourseServiceImpl();
//        根据课程id查询课程信息
        Course course = cs.findCourseById(Integer.parseInt(req.getParameter("id")));
//        判断课程信息状态，进行取反设置
        if (status.equals("0")) {
//            设置为1
            course.setStatus(1);
            course.setUpdate_time(DateUtils.getDateFormart());
        } else {
//            设置为0
            course.setStatus(0);
            course.setUpdate_time(DateUtils.getDateFormart());
        }
//        修改状态
        Map<String, Integer> map = cs.updateCourseStatus(course);
//        返回响应结果
        String result = JSONObject.toJSONString(map);
        resp.getWriter().print(result);
    }
}