package com.lagou.web.servlet;

import com.lagou.utils.UUIDUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.spi.FileTypeDetector;
import java.util.List;

/**
 * @Auther: username
 * @Date: 12/24/24 08:40
 * @Description:
 */
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.创建磁盘文件工厂
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
//        2.创建文件上传核心类
        JakartaServletFileUpload  upload = new JakartaServletFileUpload(factory);

//        2.1.设置上传文件的编码
        upload.setHeaderCharset(StandardCharsets.UTF_8);

//        2.2.判断表单是否为文件上传表单
        boolean multipartContent = upload.isMultipartContent(req);
        if (multipartContent) {
//            3.解析request，获取表单项集合
            List<FileItem> list = upload.parseRequest(req);
            if (list != null) {
//                4.遍历集合，获取表单项
                for (FileItem item : list) {
//                    5.判断当前表单项是否为普通表单项
                    if (item.isFormField()) {
//                        普通表单项
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        System.out.println(fieldName + ":" + fieldValue);
                    } else {
//                        文件上传项
                        String fieldName = item.getName();
//                        使用UUID拼接新的文件名，保证不重复
                        String newFileName = UUIDUtils.getUUID() + "_" + fieldName;
//                        获取输入流
                        InputStream inputStream = item.getInputStream();
//                        创建输出流
//                        FileOutputStream outputStream = new FileOutputStream("/labs/lagou_edu/upload/" + newFileName);
//                        1.获取项目运行目录
                        String realPath = req.getServletContext().getRealPath("/");
//                        2.截取到webapps目录位置 /opt/apache-tomcat-10.1.34/webapps/lagou_edu/
                        realPath = realPath.substring(0, realPath.lastIndexOf("/lagou_edu"));
//                        3，拼接路径，将图片保存至upload
                        FileOutputStream outputStream = new FileOutputStream(realPath + "/upload/" + newFileName);

//                        使用IOUtils完成文件的copy
                        IOUtils.copy(inputStream, outputStream);
//                        关闭流
                        inputStream.close();
                        outputStream.close();
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}