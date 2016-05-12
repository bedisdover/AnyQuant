<%@ page import="presentation.graphs.ThreeDPieChart" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--大盘指数</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="/images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/marketStyle.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/tab.js"></script>

    <script src="js/excanvas.js"></script>
    <script src="js/excanvas.min.js"></script>
    <script src="js/jquery.jqplot.js"></script>
    <script src="js/jquery.jqplot.min.js"></script>
    <script src="js/jquery.js"></script>
    <link href="style/jquery.jqplot.css" rel="stylesheet" type="text/css"/>
    <link href="style/jquery.jqplot.min.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="navBar.jsp"/>
<div class="row">
    <div class="col-md-2 vertical-nav">
        <div class="container-fluid">
            <div class="market-index-list">
                <ul class="nav nav-pills nav-stacked market-index-list">
                    <li role="presentation" class="active"><a href="#">上证指数</a></li>
                    <li role="presentation"><a href="#">深证成指</a></li>
                    <li role="presentation"><a href="#">沪深300</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-md-9 content">
        <div class="row latest-data"><!--最新数据-->
            <div class="col-md-2 name-code">
                <P class="text-center name">沪深300</P>
                <p class="text-center code">（1B0300）</p>
            </div>
            <div class="col-md-4">
                <div class="inc-dec">
                    <p class="text-left price">
                        366.68
                        <small>
                            <small>
                                <small>-6.16 (-1.65%)</small>
                            </small>
                        </small>
                    </p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="data">
                    <div class="data-top"><%--顶部数据，包含今开、最高、成交量--%>
                        <div class="col-md-4">
                            <p class="text-left">今开：372.72</p>
                        </div>
                        <div class="col-md-4">
                            <p class="text-left">最高：373.64</p>
                        </div>
                        <div class="col-md-4">
                            <P class="text-left">成交量：60.03万手</P>
                        </div>
                    </div>
                    <div class="data-bottom"><%--底部数据，包含昨收、最低、成交额--%>
                        <div class="col-md-4">
                            <p class="text-left">昨收：372.84</p>
                        </div>
                        <div class="col-md-4">
                            <p class="text-left">最低：366.50</p>
                        </div>
                        <div class="col-md-4">
                            <P class="text-left">成交额：4.92亿</P>
                        </div>
                    </div>
                </div>
                <%--data--%>
            </div>
        </div>
        <%-- latest-data --%>
        <div class="chart-list">
            <%--<ul class="nav nav-tabs chart-tabs">--%>
            <%--<li role="presentation" class="active"><a href="javascript:void(0)">分时图</a></li>--%>
            <%--<li role="presentation"><a href="javascript:void(0)">K线图</a></li>--%>
            <%--<li role="presentation"><a href="javascript:void(0)">折线图</a></li>--%>
            <%--</ul>--%>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#time-series" aria-controls="time-series" role="tab" data-toggle="tab">分时图</a>
                </li>
                <li role="presentation">
                    <a href="#broken-line" aria-controls="broken-line" role="tab" data-toggle="tab">折线图</a>
                </li>
                <li role="presentation">
                    <a href="#daily_k" aria-controls="daily_k" role="tab" data-toggle="tab">日K线</a>
                </li>
                <li role="presentation">
                    <a href="#weekly_k" aria-controls="weekly_k" role="tab" data-toggle="tab">周K线</a>
                </li>
                <li role="presentation">
                    <a href="#monthly_k" aria-controls="monthly_k" role="tab" data-toggle="tab">月K线</a>
                </li>
            </ul>
            <%
                ThreeDPieChart pieChart = new ThreeDPieChart();
                String name[] = {"管理人员", "市场人员", "开发人员", "后勤人员", "财务人员"};
                double data[] = {25, 35, 20, 5, 15};
                String fileName_1 = pieChart.generateThreeDPieChart("test1", name, data);
                String fileName_2 = pieChart.generateThreeDPieChart("test2", name, data);
                String fileName_3 = pieChart.generateThreeDPieChart("test3", name, data);

                String graphURL_1 = request.getContextPath() + "/DisplayChart?filename=" + fileName_1;
                String graphURL_2 = request.getContextPath() + "/DisplayChart?filename=" + fileName_2;
                String graphURL_3 = request.getContextPath() + "/DisplayChart?filename=" + fileName_3;
            %>
            <%
                long volume[]= (long[]) request.getAttribute("volume");
                String date[]= (String[]) request.getAttribute("date");


            %>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="time-series">
                    <img src="<%=graphURL_1%>">
                </div>
                <div role="tabpanel" class="tab-pane" id="broken-line">
                    <img src="<%=graphURL_2%>">
                </div>
                <div role="tabpanel" class="tab-pane" id="daily_k">
                    <img src="<%=graphURL_3%>">
                </div>
                <div role="tabpanel" class="tab-pane" id="weekly_k">test4</div>
                <div role="tabpanel" class="tab-pane" id="monthly_k">test5</div>
            </div>
        </div>
        <%-- chart-list --%>
    </div>
    <%--content--%>
    <div class="col-md-1">

    </div>
</div>
<%--row--%>
<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>