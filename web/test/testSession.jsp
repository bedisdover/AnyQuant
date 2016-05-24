<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-24
  Time: 下午2:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestSession</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
</head>
<body>
    <h1><%=session.getAttribute("UserId")%></h1>
</body>
</html>
