package com.lagou.dao;

import com.lagou.dao.impl.CourseDAOImpl;
import com.lagou.pojo.Course;
import com.lagou.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:28
 * @Description:
 */
public class CourseTest {

    CourseDAOImpl courseDAO = new CourseDAOImpl();

    //    测试课程列表查询
    @Test
    public void testFindCourseList() {
        List<Course> courseList = courseDAO.findCourseList();
        System.out.println(courseList);
    }

    @Test
    public void testFindCourseById() {
        List<Course> courseList = courseDAO.findCourseByNameAndSatus("微服务","");
        for (Course course : courseList) {
            System.out.println(course.getId()+" "+course.getCourse_name()+" "+course.getStatus());
        }
    }

    @Test
    public void testSaveCourseSalesInfo() {
        Course course = new Course();
        course.setCourse_name("爱情36计");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setPrice_tag("最新优惠价");
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情36计,就像一场游戏");
        course.setCourse_img_url("https://www.xx.com/xxx.jpg");
        course.setStatus(1); //1 上架 ,0 下架
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDAO.saveCourseSalesInfo(course);
        System.out.println(i);

    }

    //    更新课程信息
    @Test
    public void testUpdateCourse() {
//        根据id查询课程信息
        Course course = courseDAO.findCourseById(1);

//        修改课程营销信息
        course.setCourse_name("50哥");
        course.setTeacher_name("陈老师");

        int row = courseDAO.updateCourseSelesInfo(course);
        System.out.println(row);

    }
}