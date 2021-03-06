<%--
  Created by IntelliJ IDEA.
  User: YaoWenHao
  Date: 2018/12/6
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理投票</title>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css"/>
    <script>!function (e) {
        var c = {nonSecure: "8123", secure: "8124"}, t = {nonSecure: "http://", secure: "https://"},
            r = {nonSecure: "127.0.0.1", secure: "gapdebug.local.genuitec.com"},
            n = "https:" === window.location.protocol ? "secure" : "nonSecure";
        script = e.createElement("script"), script.type = "text/javascript", script.async = !0, script.src = t[n] + r[n] + ":" + c[n] + "/codelive-assets/bundle.js", e.getElementsByTagName("head")[0].appendChild(script)
    }(document);</script>
</head>
<body>

<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-5"
     data-genuitec-path="/vote/WebRoot/WEB-INF/pages/top.jsp">
    <img src="<%=path%>/images/logo.gif"/>
</div>
<div id="navbar" class="wrap">
    <div class="profile">
        您好，${sessionScope.user.name}
        <span class="return"><a href="<%=path%>/list">返回列表</a></span>
        <span class="addnew"><a href="<%=path%>/jsp/add.jsp">添加新投票</a></span>
        <span class="modify"><a href="<%=path%>/modify">维护</a></span>
    </div>
    <div class="search">
        <form method="post" action="/vote/search">
            <input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit"
                                                                                   class="input-button" value=""/>
        </form>
    </div>
</div>

<div id="vote" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-8"
     data-genuitec-path="/vote/WebRoot/WEB-INF/pages/modify.jsp">
    <h2>投票列表</h2>
    <ul class="list">
        <c:choose>
            <c:when test="${empty requestScope.subjectList}">
                <li class="odd">
                    <p class="info">无投票项目</p>
                </li>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.subjectList}" var="subject">
                    <li class="odd">
                        <h4>
                            <em><a href="<%=path%>/doModify?id=${subject.id}">维护</a></em>
                            <a href="<%=path%>/doModify?id=${subject.id}">${subject.title}</a>
                        </h4>
                        <p class="info">共有 ${subject.optionNumbers}个选项，已有${subject.recordNumbers} 个网友参与了投票。</p>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div id="footer" class="wrap">
    艾特优软件 &copy; 版权所有
</div>
</body>
</html>
