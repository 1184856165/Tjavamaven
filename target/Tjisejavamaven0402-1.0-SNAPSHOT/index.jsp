<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/31
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css">

<!--jquery的框架：一定要先导入，因为bootstrap的框架需要使用jquery-->
<script src="plugins/jquery-1.12.4/jquery-1.12.4.min.js" type="text/javascript"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>

<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form class="form-horizontal" action="LoginServlet" method="post">
    <div class="form-group" style="margin-top: 300px">
        <label for="userName" class="col-sm-2 col-md-offset-3 control-label">用户名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名">
        </div>
    </div>
    <div class="form-group">
        <label for="userPsw" class="col-sm-2  col-md-offset-3 control-label">密码</label>
        <div class="col-sm-2">
            <input type="password" class="form-control" id="userPsw" name="userPsw" placeholder="请输入密码">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-5 col-sm-2">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
    </div>
</form>
</body>
</html>
