package com.lagou.web.servlet;

import com.lagou.base.Constants;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;
import com.lagou.utils.UUIDUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: username
 * @Date: 12/24/24 15:39
 * @Description:
 */
@WebServlet("/courseSelesInfo")
public class CourseSelesInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.保存课程营销信息
//        1.1.收集表单数据，收集到Course对象中
//        1.1.1.创建Course对象
        Course course = new Course();
//        1.1.2.创建map对象，用于保存表单数据
        Map<String, Object> map = new HashMap<>();
//        1.1.3.创建磁盘工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory.Builder().get();
//        1.1.4.文件上传核心对象
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
//        1.1.5.解析request对象，获取表单项集合
        List<FileItem> list = upload.parseRequest(req);
//        1.1.6.遍历集合，判断普通表单项、文件表单项
        for (FileItem item : list) {
            if (item.isFormField()) {
                // 普通表单项，获取表单项数据，并且保存到map
                String fieldName = item.getFieldName();
                String value = item.getString(StandardCharsets.UTF_8);
                System.out.println(fieldName + "=" + value);
                map.put(fieldName, value);
            } else {
                // 文件上传项
//                1.获取文件名
                String name = item.getName();
                String newName = UUIDUtils.getUUID()+" "+name;
//                2.获取输入流
                InputStream inputStream = item.getInputStream();
//                3.获取项目路径
                String realPath = req.getServletContext().getRealPath("/");
//                4.更新上传路径
                realPath = realPath.substring(0, realPath.lastIndexOf("/lagou_edu"));
//                5.创建输出流
                OutputStream outputStream = new FileOutputStream(realPath+"/upload/"+newName);

                IOUtils.copy(inputStream, outputStream);
//                6.关闭流
                outputStream.close();
                inputStream.close();
//                7.将图片路径进行保存
                map.put("course_img_url", Constants.LOCAL_URL+"/upload/"+newName);
            }
        }
//        1.2.使用beanUtils将map中的对象放到Course对象中
        try {
            BeanUtils.populate(course, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
//        1.3.补全数据
        String dataFormat = DateUtils.getDateFormart();
//        1.4.业务处理
        CourseService cs = new CourseServiceImpl();
        String result;

        if (map.get("id") != null) {
//            修改操作
            course.setUpdate_time(dataFormat); // 修改时间
            result = cs.updateCourseSalesInfo(course);
        } else {
//            新建操作
            course.setCreate_time(dataFormat); // 创建时间
            course.setUpdate_time(dataFormat);
            course.setStatus(1); // 上架
            result = cs.saveCourseSalesInfo(course);
//        1.5.响应结果
            resp.getWriter().write(result);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}