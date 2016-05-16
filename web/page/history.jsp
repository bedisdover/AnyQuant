<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--历史</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/historyStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navBar.jsp"/>
<%
    session.setAttribute("which_button","h");
%>
<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>