<%--
  Created by IntelliJ IDEA.
  User: YaoWenHao
  Date: 2018/12/6
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>投票列表</title>
    <link type="text/css" rel="stylesheet" href="/vote/css/style.css" />
    <script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body>

<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-5" data-genuitec-path="/vote/WebRoot/WEB-INF/pages/top.jsp">
    <img src="/vote/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
    <div class="profile">
        您好，Jack
        <span class="return"><a href="/vote/list">返回列表</a></span>
        <span class="addnew"><a href="/vote/m/add">添加新投票</a></span>
        <span class="modify"><a href="/vote/m/modify">维护</a></span>
    </div>
    <div class="search">
        <form method="post" action="/vote/search">
            <input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
        </form>
    </div>
</div>

<div id="vote" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc4-2" data-genuitec-path="/vote/WebRoot/WEB-INF/pages/list.jsp">
    <h2>投票列表</h2>
    <ul class="list">


        <li  class="odd" >
            <h4>
                <a href="/vote/m/vote?id=6">TEST Five</a>
            </h4>
            <div class="join"><a href="/vote/m/vote?id=6">我要参与</a></div>
            <p class="info">共有 2个选项，已有 1个网友参与了投票。</p>
        </li>

        <li  >
            <h4>
                <a href="/vote/m/vote?id=7">Test six</a>
            </h4>
            <div class="join"><a href="/vote/m/vote?id=7">我要参与</a></div>
            <p class="info">共有 2个选项，已有 2个网友参与了投票。</p>
        </li>

    </ul>
</div>
<div id="footer" class="wrap">
    艾特优软件 &copy; 版权所有
</div>
</body>
</html>
