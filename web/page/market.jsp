<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <title>AnyQuant--大盘指数</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/marketStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/tab.js"></script>


    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <%--柱状图引包--%>
    <script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
    <script type="text/javascript" src="js/jqplot.barRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.categoryAxisRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.pointLabels.min.js"></script>

    <%--折线图引包--%>
    <script type="text/javascript" src="js/jqplot.dateAxisRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.logAxisRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.canvasTextRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.canvasAxisTickRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.ohlcRenderer.min.js"></script>
    <script type="text/javascript" src="js/jqplot.cursor.min.js"></script>
    <link rel="stylesheet" type="text/css" hrf="style/jquery.jqplot.min.css"/>
    <%--echarts引包--%>
    <script src="js/dist/echarts.js"></script>

    <script>

        <%--var line1 = [[]];--%>
        <%--<!-- data和volume是request中属性的名字-->--%>
        <%--<c:forEach items="${date}" var="date1" varStatus="loop">--%>
        <%--var date1 = "${date1}"--%>
        <%--var quantity = "${volume[loop.count-1]}"--%>
        <%--line1[0].push([date1, quantity]);--%>
        <%--</c:forEach>--%>
        <%--alert(line1);--%>

        <%--var plot1 = $.jqplot('chart', line1, {--%>
            <%--title: 'Google, Inc.',--%>
            <%--series: [{--%>
                <%--label: 'Google, Inc.',--%>
                <%--neighborThreshold: -1--%>
            <%--}],--%>
            <%--axes: {--%>
                <%--xaxis: {--%>
                    <%--renderer: $.jqplot.DateAxisRenderer,--%>
                    <%--tickRenderer: $.jqplot.CanvasAxisTickRenderer,--%>
                    <%--tickOptions: {--%>
                        <%--angle: -30--%>
                    <%--}--%>
                <%--},--%>
                <%--yaxis: {--%>
                    <%--renderer: $.jqplot.LogAxisRenderer,--%>
                    <%--tickOptions: {prefix: '$'}--%>
                <%--}--%>
            <%--},--%>
            <%--cursor: {--%>
                <%--show: true,--%>
                <%--zoom: true--%>
            <%--}--%>
        <%--});--%>
        <%
            long volume[] = new long[0];
            String date[] = new String[0];
            double high[] = new double[0];
            double adj_price[] = new double[0];
            double low[] = new double[0];
            double close[] = new double[0];
            double open[] = new double[0];
            double increase_decreaseRate[] = new double[0];
            double increase_decreaseNum[] = new double[0];
            if (request.getAttribute("volume") == null) {
                System.out.println("哎哟喂空的欸！volume");
            } else if (request.getAttribute("date") == null) {
                System.out.println("哎哟喂空的欸！date");
            } else {
                volume = (long[]) request.getAttribute("volume");
                date = (String[]) request.getAttribute("date");
                high = (double[]) request.getAttribute("high");
                adj_price = (double[]) request.getAttribute("adj_price");
                low = (double[]) request.getAttribute("low");
                close = (double[]) request.getAttribute("close");
                increase_decreaseRate = (double[]) request.getAttribute("open");
                increase_decreaseNum = (double[]) request.getAttribute("increase_decreaseRate");
    //             System.out.println(volume[0]+"aiyowei");
    //             System.out.println(date[0]+"yoyoyo");
            }
        %>





        //日K线 undone

        var lineData = [[]];
        <c:forEach items="${date}" var="dateShow" varStatus="loop">
        var dateShow = "${dateShow}"
        var highShow = "${high[loop.count-1]}"
        var lowShow = "${low[loop.count-1]}"
        var openShow = "${open[loop.count-1]}"
        var closeShow = "${close[loop.count-1]}"
        lineData[0].push([dateShow, highShow, lowShow, openShow, closeShow]);
        </c:forEach>



    </script>

    <link href="style/jquery.jqplot.css" rel="stylesheet" type="text/css"/>
    <link href="style/jquery.jqplot.min.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%--导航栏Start--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="true">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="../index.jsp">AnyQuant</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left">
                    <li>
                        <a href="../index.jsp">
                            <span class="glyphicon glyphicon-home"></span> 首页
                        </a>
                    </li>
                    <li>
                        <a href="portfolio.jsp">
                            <span class="glyphicon glyphicon-heart"></span> 自选
                        </a>
                    </li>
                    <li class="active">
                        <a href="javascript: void(0)">
                            <span class="glyphicon glyphicon-signal"></span> 大盘
                        </a>
                    </li>
                    <li>
                        <a href="picture.jsp">
                            <span class="glyphicon glyphicon-th-list"></span> 行情
                        </a>
                    </li>
                    <li>
                        <a href="history.jsp">
                            <span class="glyphicon glyphicon-time"></span> 历史
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="login.jsp">
                            <span class="glyphicon glyphicon-user"></span> 登录
                        </a>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>
<%--导航栏End--%>

<%--content Start--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-6 col-md-2">
            <div id="sidebar">
                <div class="list-group">
                    <ul class="menu" id="menu">
                        <li onclick="page_jump(this, 'latest-data')"
                            class="list-group-item text-center active">实时数据
                        </li>
                        <li onclick="page_jump(this, 'graphs')"
                            class="list-group-item text-center">
                            图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表
                        </li>
                        <li onclick="page_jump(this, 'history-data')"
                            class="list-group-item text-center">详细数据
                        </li>
                        <li onclick="page_jump(this, 'picture-prediction')"
                            class="list-group-item text-center">行情预测
                        </li>
                        <li onclick="page_jump(this, 'stock-news')"
                            class="list-group-item text-center">最新资讯
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-9">
            <div class="panel panel-default">
                <div class="latest-data" id="latest-data"><!--最新数据-->
                    <div class="row">
                        <div class="col-md-2">
                            <div class="name-code">
                                <P class="text-center name"> 沪深300
                                </P>
                                <p class="text-center code">（1B40003）</p>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="inc-dec">
                                <p class="text-left text-danger price">
                                    3776.3
                                    <small>
                                        <small>
                                            <small>
                                                +23.5 0.34
                                            </small>
                                        </small>
                                    </small>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="data">
                                <div class="data-top"><%--顶部数据，包含今开、最高、成交量--%>
                                    <div class="col-md-3 col-md-offset-1">
                                        <p class="text-left">今开：1892.2
                                        </p>
                                    </div>
                                    <div class="col-md-3">
                                        <p class="text-left">最高：1234
                                        </p>
                                    </div>
                                    <div class="col-md-5">
                                        <P class="text-left">成交量：34243
                                        </P>
                                    </div>
                                </div>
                                <div class="data-bottom"><%--底部数据，包含昨收、最低、成交额--%>
                                    <div class="col-md-3 col-md-offset-1">
                                        <p class="text-left">昨收：1234
                                        </p>
                                    </div>
                                    <div class="col-md-3">
                                        <p class="text-left">最低：1234
                                        </p>
                                    </div>
                                    <div class="col-md-5">
                                        <P class="text-left">成交额：1234
                                        </P>
                                    </div>
                                </div>
                            </div>
                            <%--data--%>
                        </div>
                    </div>
                </div>
                <%--最新数据End--%>
                <div class="graphs" id="graphs">

                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#分时图" data-toggle="tab">分时图</a>
                        </li>
                        <li>
                            <a href="#折线图" data-toggle="tab">折线图</a>
                        </li>
                        <li>
                            <a href="#月K线" data-toggle="tab">月K线</a>
                        </li>
                        <li>
                            <a href="#周K线" data-toggle="tab">周K线</a>
                        </li>
                        <li>
                            <a href="#日K线" data-toggle="tab">日K线</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="chart" style="width: 800px; height: 500px;"></div>
                        <div class="tab-pane fade in active" id="分时图">
                            <%--todo 用一个div把你要画的图放在这里就好了--%>

                            这里是分时图
                        </div>
                        <div class="tab-pane fade" id="折线图">
                         <div>
                            折线图
                         </div>
                        </div>
                        <div class="tab-pane fade" id="月K线">
                            月K线
                        </div>
                        <div class="tab-pane fade" id="周K线">
                            周K线
                        </div>
                        <div class="tab-pane fade" id="日K线">
                            <div>
                            <script>
                            require.config({
                            paths: {
                            echarts: 'js/dist'
                            }
                            });
                            require(
                            [
                            'echarts',
                            'echarts/chart/k'
                            ],
                            function (ec) {
                            var myChart = ec.init(document.getElementById('chart'));
                            var option = {
                            title: {
                            text: '2013年上半年上证指数'
                            },
                            tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                            var res = params[0].seriesName + ' ' + params[0].name;
                            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                            return res;
                            }
                            },
                            legend: {
                            data: ['上证指数']
                            },
                            toolbox: {
                            show: true,
                            feature: {
                            mark: {show: true},
                            dataZoom: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                            }
                            },
                            dataZoom: {
                            show: true,
                            realtime: true,
                            start: 50,
                            end: 100
                            },
                            xAxis: [
                            {
                            type: 'category',
                            boundaryGap: true,
                            axisTick: {onGap: false},
                            splitLine: {show: false},
                            data: [
                            "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                            "2013/1/31", "2013/2/1",

                            ]
                            }
                            ],
                            yAxis: [
                            {
                            type: 'value',
                            scale: true,
                            boundaryGap: [0.01, 0.01]
                            }
                            ],
                            series: [
                            {
                            name: '上证指数',
                            type: 'k',
                            data: [ // 开盘，收盘，最低，最高
                            [2320.26, 2302.6, 2287.3, 2362.94],
                            [2300, 2291.3, 2288.26, 2308.38],
                            [2295.35, 2346.5, 2295.35, 2346.92],
                            [2347.22, 2358.98, 2337.35, 2363.8],
                            [2360.75, 2382.48, 2347.89, 2383.76],
                            [2383.43, 2385.42, 2371.23, 2391.82],
                            [2377.41, 2419.02, 2369.57, 2421.15],

                            ]
                            }
                            ]
                            };

                            myChart.setOption(option);
                            }
                            );
                            </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--统计图End--%>
                <div class="history-data well" id="history-data">
                    <div class="table-responsive">
                        <table class="table table-striped text-center">
                            <thead>
                            <tr>
                                <th class="text-center">日期</th>
                                <th class="text-center">最高</th>
                                <th class="text-center">最低</th>
                                <th class="text-center">涨跌额</th>
                                <th class="text-center">涨跌幅</th>
                                <th class="text-center">开盘</th>
                                <th class="text-center">收盘</th>
                                <th class="text-center">成交量</th>
                                <th class="text-center">市盈率</th>
                                <th class="text-center">市净率</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (int j = 10; j >= 0; j--) {
                            %>
                            <tr>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                                <td><%=j%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%--历史数据End--%>
                <div id="picture-prediction">

                </div>
                <%--行情预测End--%>
                <div id="stock-news">

                </div>
                <%--实时资讯End--%>
            </div>
        </div>
    </div>
</div>
<%--content End--%>

</body>
</html>


