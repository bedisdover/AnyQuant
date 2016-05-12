<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>AnyQuant--login</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/loginStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
    String mid = request.getParameter("mid");
    String password = request.getParameter("password");
%>
<%!
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/yootk";
    public static final String DBUSER = "root";
    public static final String PASSWORD = "mysqladmin";
%>
<%
    boolean flag = false;
    Class.forName(DBDRIVER);
    Connection conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
    Statement stmt = conn.createStatement();
    String sql = "SELECT COUNT(*) FROM member WHERE mid='" + mid + "'AND password='" + password + "'";
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
        if(rs.getInt(1)>0){
            flag = true;
        }
    }
    conn.close();
%>
<%
    if(flag){
%>
        <h1>用户登录成功，欢迎光临</h1>
<%
    }else{
%>
        <h1>用户登录失败，重新<a href="loginTest.jsp">登录</a></h1>
<%
    }
%>
</body>
</html>
