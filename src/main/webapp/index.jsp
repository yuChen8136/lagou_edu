<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<body>
<h2>Hello World!</h2>
<a href="<%= contextPath %>/course?methodName=findCourseList">查看课程列表</a>
<a href="<%= contextPath %>/course?methodName=findCourseList">查看课程列表</a>

</body>
</html>
