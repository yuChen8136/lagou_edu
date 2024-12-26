<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="<%= contextPath %>/upload">
    <input type="file" name="upload"/>
    <br>
    <input type="text" name="name"/>
    <input type="text" name="password"/>
    <input type="submit" name="文件上传"/>
</form>

<img src="/upload/81fb10c1bf344baf8b35f5cacdffb49e_架构图.png">
</body>
</html>
