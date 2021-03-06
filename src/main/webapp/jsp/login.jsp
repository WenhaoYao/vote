<%--
  Created by IntelliJ IDEA.
  User: YaoWenHao
  Date: 2018/12/6
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登录页面</title>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css"/>
    <script type="text/javascript">
        window.onload = function () {
            var input = document.getElementById("loginBox").getElementsByTagName("input");
            for (i = 0; i < input.length; i++) {
                var type = input[i].getAttribute("type");
                if (type == "text" || type == "password") {
                    input[i].onfocus = function () {
                        this.className += " input-text-over";
                    }
                    input[i].onblur = function () {
                        this.className = this.className.replace(/input-text-over/, "");
                    }
                } else if (type == "submit") {
                    input[i].onmouseover = function () {
                        this.className += " input-submit-over";
                    }
                    input[i].onmouseout = function () {
                        this.className = this.className.replace(/input-button-over/, "");
                    }
                }
            }
        }
    </script>
    <script>!function (e) {
        var c = {nonSecure: "8123", secure: "8124"}, t = {nonSecure: "http://", secure: "https://"},
            r = {nonSecure: "127.0.0.1", secure: "gapdebug.local.genuitec.com"},
            n = "https:" === window.location.protocol ? "secure" : "nonSecure";
        script = e.createElement("script"), script.type = "text/javascript", script.async = !0, script.src = t[n] + r[n] + ":" + c[n] + "/codelive-assets/bundle.js", e.getElementsByTagName("head")[0].appendChild(script)
    }(document);</script>
</head>
<body>
<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-3"
     data-genuitec-path="<%=path%>/jsp/login.jsp">
    <img src="<%=path%>/images/logo.gif"/>
</div>
<div id="login" class="wrap">
    <div class="main">
        <div class="corner"></div>
        <div class="introduce">
            <h2>艾特优软件</h2>
            <p>网上调查系统...</p>
        </div>
        <div class="login">
            <h2>用户登录</h2>
            <form method="post" action="<%=path%>/DoLoginServlet">
                <dl id="loginBox">
                    <dt>用户名：</dt>
                    <dd><input type="text" class="input-text" name="name" value="${requestScope.user.name}"/></dd>
                    <dt>密　码：</dt>
                    <dd><input type="password" class="input-text" name="pwd" value=""/></dd>
                    ${requestScope.errorMessage}
                    <dt></dt>
                    <dd><input type="checkbox" name="remember" value="1"/>10天内免登录</dd>
                    <dt></dt>
                    <dd><input type="submit" class="input-button" value="登录"/> <a
                            href="<%=path%>/RegisterServlet">新用户注册</a></dd>
                </dl>
            </form>
            <div class="error"></div>
        </div>
    </div>
</div>
<div class="wrap">
</div>
<div id="footer" class="wrap">
    艾特优软件 &copy; 版权所有
</div>
</body>
</html>
