<%--
  Created by IntelliJ IDEA.
  User: YaoWenHao
  Date: 2018/12/6
  Time: 9:14
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
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>发布新投票</title>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css"/>
    <script type="text/javascript">
        var isIE = !!document.all;

        function AddOption() {
            var voteoptions = document.getElementById("voteoptions");
            var _p = document.createElement("p");
            var _input = document.createElement("input");
            _input.type = "text";
            _input.className = "input-text";
            _input.setAttribute("name", "options");
            _p.appendChild(_input);
            var _a = document.createElement("a");
            _a.className = "del";
            _a.setAttribute("href", "javascript:;");
            if (isIE) {
                _a.attachEvent("onclick", DelOption);
            } else {
                _a.addEventListener("click", DelOption, false);
            }
            _a.appendChild(document.createTextNode("删除"));
            _p.appendChild(_a);
            voteoptions.appendChild(_p);
        }

        function DelOption(e) {
            if (!e) e = window.event;
            var a = e.srcElement || e.target;
            var obj = a.parentNode;
            obj.parentNode.removeChild(obj);
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


<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-5"
     data-genuitec-path="/VoteSite/WebRoot/top.jsp">
    <img src="<%=path%>/images/logo.gif"/>
</div>
<div id="navbar" class="wrap">
    <div class="profile">
        您好，${sessionScope.user.name}
        <span class="return"><a href="<%=path%>/jsp/list.jsp">返回列表</a></span>
        <span class="addnew"><a href="<%=path%>/jsp/add.jsp">添加新投票</a></span>
    </div>
    <div class="search">
        <form method="post" action="<%=path%>/Add">
            <input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit"
                                                                                   class="input-button" value=""/>
        </form>
    </div>
</div>

<div id="voteManage" class="box" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-0"
     data-genuitec-path="<%=path%>/jsp/add.jsp">
    <h2>添加新投票</h2>
    <div class="content">
        <form method="post" action="<%=path%>/Add">
            <dl>
                <dt>投票内容：</dt>
                <dd>
                    <input type="hidden" name="id" value=""/>
                    <input type="text" class="input-text" name="title" value="${subject.title}"/>
                </dd>
                <dt>投票类型：</dt>
                <dd>
                    <input type="radio" name="voteType" value="1" <c:if test="${subject.number eq '1'}">checked="checked"</c:if>/>单选
                    <input type="radio" name="voteType" value="2" <c:if test="${subject.number eq '2'}">checked="checked"</c:if>/>多选
                </dd>
                <dt>投票选项：</dt>

                <dd id="voteoptions">
                    <p><input type="text" class="input-text" name="options"/></p>
                    <p><input type="text" class="input-text" name="options"/></p>
                </dd>
                <dt></dt>
                <dd style="color: red">
                    ${requestScope.errorMessage}
                </dd>

                <dt></dt>
                <dd class="button">
                    <input type="image" src="<%=path%>/images/button_submit.gif"/>
                    <a href="javascript:" onclick="AddOption()">增加选项</a>
                    <a href="<%=path%>">取消操作</a>
                </dd>
            </dl>
        </form>
    </div>
</div>
<div id="footer" class="wrap">
    艾特优软件 &copy; 版权所有
</div>
</body>
</html>
