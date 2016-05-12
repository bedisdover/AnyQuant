<%@ page import="presentation.graphs.ThreeDPieChart" %>
<%@ page import="servlet.PortfolioServlet" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--自选</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="/images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/portfolioStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navBar.jsp"/>
<%
    String test = (String) request.getAttribute("test");
%>

<h1><%=test%></h1>

<div class="stockList-div">
    <table class="table table-hover stockList">
        <tr>
            <td>名称</td>
            <td>代码</td>
        </tr>
        <tr>
            <td class="stock">
            </td>
            <td>test1</td>
        </tr>
        <tr>
            <td class="stock">test</td>
            <td>test1</td>
        </tr>
        <tr>
            <td class="stock">test</td>
            <td>test1</td>
        </tr>
    </table>
</div>
<%
    ThreeDPieChart pieChart = new ThreeDPieChart();
    String name[] = {"管理人员", "市场人员", "开发人员", "后勤人员", "财务人员"};
    double data[] = {25, 35, 20, 5, 15};
    String fileName = pieChart.generateThreeDPieChart("test", name, data);
    String graphURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;
%>
<div class="container">
    <img src="<%=graphURL%>" alt="" width="400" height="300">
</div>

<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>