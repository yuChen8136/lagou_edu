package com.lagou.base;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Auther: username
 * @Date: 12/21/24 11:54
 * @Description:
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取参数，要访问的方法名
        String methodName = req.getParameter("methodName");
//        2.判断执行对应的方法
        if (methodName != null) {
//            使用反射进行优化
            try {
//                获取字节码文件对象
                Class<?> c = this.getClass();
//                根据传入的方法名获取对应的方法对象
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//                调用method对象的invoke方法执行对应的功能
                method.invoke(this, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求的功能不存在！");
            }

        }
//        if ("addCourse".equals(methodName)) {
//            addCourse(req, resp);
//        } else if ("findByName".equals(methodName)) {
//            findByName(req, resp);
//        } else if ("findByStatus".equals(methodName)) {
//            findByStatus(req, resp);
//        } else {
//            System.out.println("请求的功能不存在！");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}