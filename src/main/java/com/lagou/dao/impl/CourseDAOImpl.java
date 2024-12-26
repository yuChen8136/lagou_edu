package com.lagou.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lagou.dao.CourseDAO;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: username
 * @Date: 12/21/24 15:10
 * @Description:
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public List<Course> findCourseList() {
//        创建QueryRunner
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

//        编写Sql，判断是否删除，取出is_del=0的未删除数据
        String sql = "SELECT\n" +
                "\tid,\n" +
                "\tcourse_name,\n" +
                "\tprice,\n" +
                "\tsort_num,\n" +
                "\t`status` \n" +
                "FROM\n" +
                "\tcourse \n" +
                "WHERE\n" +
                "\tis_del = ?;";
//         执行查询
        try {
            List<Course> queryList = qr.query(sql, new BeanListHandler<Course>(Course.class), 0);
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findCourseByNameAndSatus(String courseName, String status) {
//        1. 创建QueryRunner
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
//        2. 编写sql,当前SQL为不定项查询
//        2.1 创建一个StringBuffer
        StringBuffer sb = new StringBuffer("SELECT id, course_name, sort_num, `status` FROM course WHERE 1=1 and is_del=?");

//        2.2 创建List集合，保存参数
        List<Object> params = new ArrayList<Object>();
        params.add(0);

//        2.3 判断传入的参数是否为空
        if (courseName != null && courseName != "") {
            sb.append(" and course_name like ?");
//            like查询，需要拼接
            courseName = ("%" + courseName + "%");
            params.add(courseName);
        }
//        2.4 判断传入的status是否为空
        if (status != null && status != "") {
            sb.append(" and status = ?");
//            将status转化为int
            int i = Integer.parseInt(status);
            params.add(status);
        }
//        3. 执行查询
        List<Course> courseList = null;
        try {
            courseList = qr.query(sb.toString(), new BeanListHandler<Course>(Course.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

//        4. 返回结果
        return courseList;
    }

    //    保存课程的营销信息
    @Override
    public int saveCourseSalesInfo(Course course) {
//        1.创建QueryRunner
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
//        2.编写sql
        String sql = "INSERT INTO course(\n" +
                "course_name,\n" +
                "brief,\n" +
                "teacher_name,\n" +
                "teacher_info,\n" +
                "preview_first_field,\n" +
                "preview_second_field,\n" +
                "discounts,\n" +
                "price,\n" +
                "price_tag,\n" +
                "share_image_title,\n" +
                "share_title,\n" +
                "share_description,\n" +
                "course_description,\n" +
                "course_img_url,\n" +
                "STATUS,\n" +
                "create_time,\n" +
                "update_time\n" +
                ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
//        3. 填写对应参数
        Object[] params = {
                course.getCourse_name(),
                course.getBrief(),
                course.getTeacher_name(),
                course.getTeacher_info(),
                course.getPreview_first_field(),
                course.getPreview_second_field(),
                course.getDiscounts(),
                course.getPrice(),
                course.getPrice_tag(),
                course.getShare_image_title(),
                course.getShare_title(),
                course.getShare_description(),
                course.getCourse_description(),
                course.getCourse_img_url(),
                course.getStatus(),
                course.getCreate_time(),
                course.getUpdate_time()
        };
//        4.执行插入操作
        int row = 0;
        try {
            row = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return row;
    }

    @Override
    public Course findCourseById(int id) {
//        1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

//        2.编写Sql
        String sql = "SELECT\n" +
                "\tid,\n" +
                "\tcourse_name,\n" +
                "\tbrief,\n" +
                "\tteacher_name,\n" +
                "\tteacher_info,\n" +
                "\tpreview_first_field,\n" +
                "\tpreview_second_field,\n" +
                "\tdiscounts,\n" +
                "\tprice,\n" +
                "\tprice_tag,\n" +
                "\tcourse_img_url,\n" +
                "\tshare_image_title,\n" +
                "\tshare_title,\n" +
                "\tshare_description,\n" +
                "\tcourse_description,\n" +
                "STATUS \n" +
                "FROM\n" +
                "\tcourse \n" +
                "WHERE\n" +
                "\tid = ?;";

//        3.执行查询
        try {
            Course course = qr.query(sql, new BeanHandler<Course>(Course.class), id);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateCourseSelesInfo(Course course) {
//        1.创建一个queryRunner
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
//        2.编写SQL
        String sql = "UPDATE course \n" +
                "SET course_name = ?,\n" +
                "brief = ?,\n" +
                "teacher_name = ?,\n" +
                "teacher_info = ?,\n" +
                "preview_first_field = ?,\n" +
                "preview_second_field = ?,\n" +
                "discounts = ?,\n" +
                "price = ?,\n" +
                "price_tag = ?,\n" +
                "share_image_title = ?,\n" +
                "share_title = ?,\n" +
                "share_description = ?,\n" +
                "course_description = ?,\n" +
                "course_img_url = ?,\n" +
                "update_time = ? \n" +
                "WHERE\n" +
                "\tid = ?";

        Object[] params = {
                course.getCourse_name(),
                course.getBrief(),
                course.getTeacher_name(),
                course.getTeacher_info(),
                course.getPreview_first_field(),
                course.getPreview_second_field(),
                course.getDiscounts(),
                course.getPrice(),
                course.getPrice_tag(),
                course.getShare_image_title(),
                course.getShare_title(),
                course.getShare_description(),
                course.getCourse_description(),
                course.getCourse_img_url(),
                course.getUpdate_time(),
                course.getId()
        };

        try {
            int row = qr.update(sql, params);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCourseStatus(Course course) {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "UPDATE course \n" +
                "SET STATUS =?,\n" +
                "update_time =? \n" +
                "FROM\n" +
                "\tcourse \n" +
                "WHERE\n" +
                "\tid =?;";
        Object[] params = {
                course.getStatus(),
                course.getUpdate_time(),
                course.getId()
        };
        int row = 0;
        try {
            row = qr.update(sql, params);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}