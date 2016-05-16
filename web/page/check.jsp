<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="po.UserPO" %>
<%@ page import="database.UserInfo" %>
<html>
<body>
<%
    String mid = request.getParameter("mid");
    String password = request.getParameter("password");
%>
<%
    boolean flag = false;
    UserInfo ui = new UserInfo();
    UserPO userPO = new UserPO();
    userPO = ui.getUserInfo(mid,password);
    if(userPO!=null){
        flag = true;
        session.setAttribute("UserId",userPO.getId());
    }
%>
<%
    if(flag){
        if (session.getAttribute("which_button").equals("h")) {

%>
<h1>用户登录成功，欢迎光临！</h1>
<meta http-equiv="Refresh" content="1;url= history.jsp">
<%
    }else if(session.getAttribute("which_button").equals("h")){
%>
<h1>用户登录成功，欢迎光临！</h1>
<meta http-equiv="Refresh" content="1;url= portfolio.jsp">
<%
    }
}else{
%>
<h1>用户登录失败，请<a href="login.jsp">重新登录</a></h1>
<%
    }
%>
</body>
<head>
    <title>AnyQuant--login</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/loginStyle.css" rel="stylesheet" type="text/css"/>
</head>
</html>
