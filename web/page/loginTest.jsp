<%--
  Created by IntelliJ IDEA.
  User: lenovo2014
  Date: 2016/5/10
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bootstrap+jsp</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,inital-scale=1">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <link href="/images/icon.png" rel="icon"/>
    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="style/loginStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navBar.jsp"/>
<div class="container">
    <form id="loginForm" action="other.jsp" method="post" class="form-horizontal">
        <fieldset>
            <legend><label><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</label></legend>
            <div class="form-group" id="midDiv">
                <label class="col-md-3 control-label" for="mid">用户名</label>
                <div class="col-md-5">
                    <input type="text" id="mid" name="mid" class="form-control" placeholder="请输入登录名">
                </div>
                <div class="col-md-4" id="midSpan"></div>
            </div>
            <div class="form-group" id="passwordDiv">
                <label class="col-md-3 control-label" for="password">用户名</label>
                <div class="col-md-5">
                    <input type="text" id="password" name="password" class="form-control" placeholder="请输入登录密码">
                </div>
                <div class="col-md-4" id="passwordSpan"></div>
            </div>
            <div class="form-group" id="butDiv">
                <div class="col-md-5 col-md-offset-3">
                    <button type="submit" id="subBut" class="btn btn-xs btn-primary">登录</button>
                    <button type="reset" id="rstBut" class="btn btn-xs btn-warning">重置</button>
                </div>
            </div>
        </fieldset>
    </form>

</div>
</body>
</html>
