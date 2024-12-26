package com.lagou.testBeanUtils;

import com.lagou.pojo.Course;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: username
 * @Date: 12/24/24 15:28
 * @Description:
 */
public class TestBeanUtils {
    @Test
    public void test01() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        1.创建一个Course对象
        Course course = new Course();
//        2.创建一个map存储Course
        Map<String, Object> map = new HashMap<>();
//        3.向map集合中添加数据，map中的key要与Course的属性名保持一致，value的数据类型要与Course的属性的数据类型保持一致
        map.put("id", 1);
        map.put("course_name", "大数据");
        map.put("brief", "包含所有大数据流行的技术");
        map.put("teacher_name", "周星星");
        map.put("teacher_info", "非著名演员");

        //将map中的数据封装到 course中
        BeanUtils.populate(course, map);

        System.out.println(
                course.getId() + " " +
                course.getCourse_name() + " " +
                course.getBrief() + " " +
                course.getTeacher_name() + " " +
                course.getTeacher_info());

//        设置属性 获取属性
        BeanUtils.setProperty(course, "price", 100.0);

        String price = BeanUtils.getProperty(course, "price");
        System.out.println(price);
    }
}