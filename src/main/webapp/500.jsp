<%--
  Created by IntelliJ IDEA.
  User: yaowenhao
  Date: 2018/12/20
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>错误页面</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
</head>
<body>
<h3>网站中发生了一点问题....</h3>
<hr/>
<h4>
${requestScope.exceptionMessage}</h4>
</body>
</html>
