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
    <%--<script type="text/javascript" src="js/jquery.js"></script>--%>
    <%--<script type="text/javascript" src="js/tab.js"></script>--%>


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


//            String dateshort[] = new String[0];
//            double highshort[] = new double[0];
//            double lowshort[] = new double[0];
//            double closeshort[] = new double[0];
//            double openshort[] = new double[0];



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
                open = (double[]) request.getAttribute("open");
                increase_decreaseRate = (double[]) request.getAttribute("increase_decreaseRate");
                increase_decreaseNum = (double[]) request.getAttribute("increase_decreaseNum");



//                dateshort = (String[]) request.getAttribute("dateshort");
//                highshort = (double[]) request.getAttribute("highshort");
//                lowshort = (double[]) request.getAttribute("lowshort");
//                closeshort = (double[]) request.getAttribute("closeshort");
//                openshort = (double[]) request.getAttribute("openshort");
            }
        %>


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
            <div id="main-content">
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
                    <div class="graphs">
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
                        <div id="graphs">
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="分时图">
                                    <div class="chart" id="timeSeriesChart"></div>
                                </div>
                                <div class="tab-pane fade" id="折线图">
                                    <div class="chart" id="lineChart"></div>
                                </div>
                                <div class="tab-pane fade" id="月K线">
                                    <div class="chart" id="monthlyKLine"></div>
                                </div>
                                <div class="tab-pane fade" id="周K线">
                                    <div class="chart" id="weeklyKLine"></div>
                                </div>
                                <div class="tab-pane fade" id="日K线">
                                    <div class="chart" id="dailyKLine"></div>
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
</div>
<%--content End--%>
<script src="js/market.js"></script>
<script>



    var dataDate = [];
    var dataVolume=[];
    var dataAdjPrice=[];
    var dataHigh = [];
    var dataLow = [];
    var dataOpen= [];
    var dataClose = [];
    var dataIncreaseRate=[];
    var dataIncreaseNum=[];

    var dataHighLow = [[]];
    var dataHighLowOpenClose=[[]];

    <c:forEach items="${date}" var="dateShow" varStatus="loop">
    var dateShow = "${dateShow}"
    var volumeShow = "${volume[loop.count-1]}"
    var highShow = "${high[loop.count-1]}"
    var lowShow = "${low[loop.count-1]}"
    var openShow = "${open[loop.count-1]}"
    var closeShow = "${close[loop.count-1]}"
    var adjPriceShow = "${adj_price[loop.count-1]}"
    var increaseRateShow = "${increase_decreaseRate[loop.count-1]}"
    var increaseNumShow = "${increase_decreaseNum[loop.count-1]}"
    dataDate.push(dateShow);

    dataHigh.push(highShow);
    dataLow.push(lowShow);
    dataOpen.push(highShow);
    dataClose.push(lowShow);
    dataVolume.push(volumeShow);
    dataAdjPrice.push(adjPriceShow);
    dataIncreaseRate.push(increaseRateShow);
    dataIncreaseNum.push(increaseNumShow);


    dataHighLow[0].push([dateShow,lowShow,highShow]);
    dataHighLowOpenClose[0].push([openShow,closeShow,lowShow,highShow])//开盘、收盘、最低、最高
    </c:forEach>

//short
    <%--var dataDateshort = [];--%>
    <%--var dataHighshort = [];--%>
    <%--var dataLowshort = [];--%>
    <%--var dataHighLowshort = [[]];--%>
    <%--var dataHighLowOpenCloseshort=[[]];--%>
    <%--<c:forEach items="${dateshort}" var="dateShowshort" varStatus="loop">--%>
    <%--var dateshowshort = "${dateShowshort}"--%>
    <%--var highshowshort = "${highshort[loop.count-1]}"--%>
    <%--var lowshowshort = "${lowshort[loop.count-1]}"--%>
    <%--var openshowshort = "${openshort[loop.count-1]}"--%>
    <%--var closeshowshort = "${closeshort[loop.count-1]}"--%>
    <%--dataDateshort.push(dateshowshort);--%>
    <%--//开盘、收盘、最低、最高--%>
    <%--dataHighshort.push(highshowshort);--%>
    <%--dataLowshort.push(lowshowshort);--%>
    <%--dataHighLowshort[0].push([dateshowshort,lowshowshort,highshowshort]);--%>
    <%--dataHighLowOpenCloseshort[0].push([openShow,closeShow,lowShow,highShow])--%>
    <%--</c:forEach>--%>

</script>
<%--分时图Start--%>
<script>
    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/line'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('timeSeriesChart'));
                var option = {
                    title: {
                        text: '沪深300折线图',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
//                        图例
                        data: ['最高价', '最低价']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap : false,
                            data: dataDate


                        }
                    ],
                    yAxis: [
                        {

                            type: 'value',
                            axisLine : {onZero: false},
                            axisLabel: {
                                formatter: '{value} 元'
                            },

                        }
                    ],
                    series: [
                        {
                            name: '最高价',
                            type: 'line',
                            data: dataHigh


//                            markPoint: {
//                                data: [
//                                    {type: 'max', name: '最大值'},
//                                    {type: 'min', name: '最小值'}
//                                ]
//                            },
//                            markLine: {
//                                data: [
//                                    {type: 'average', name: '平均值'}
//                                ]
//                            }

                        },
                        {
                            name: '最低价',
                            type: 'line',
                            data: dataLow

                        }
                    ]
                };
                myChart.setOption(option);
            }
    );

</script>
<%--分时图End--%>

<%--折线图最高价、最低价Start--%>
<script>
    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/line'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('lineChart'));
                var option = {
                    title: {
                        text: '沪深300折线图',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
//                        图例
                        data: ['最高价', '最低价']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap : false,
                            data: dataDate


            }
                    ],
                    yAxis: [
                        {

                            type: 'value',
                            axisLine : {onZero: false},
                            axisLabel: {
                                formatter: '{value} 元'
                            },

                        }
                    ],
                    series: [
                        {
                            name: '最高价',
                            type: 'line',
                            data: dataHigh


//                            markPoint: {
//                                data: [
//                                    {type: 'max', name: '最大值'},
//                                    {type: 'min', name: '最小值'}
//                                ]
//                            },
//                            markLine: {
//                                data: [
//                                    {type: 'average', name: '平均值'}
//                                ]
//                            }

                        },
                        {
                            name: '最低价',
                            type: 'line',
                            data: dataLow
//                            markPoint: {
//                                data: [
//                                    {type: 'max', name: '最大值'},
//                                    {type: 'min', name: '最小值'}
//                                ]
//                            },
//                            markLine: {
//                                data: [
//                                    {type: 'average', name: '平均值'}
//                                ]
//                            }
                        }
                    ]
                };
                myChart.setOption(option);
            }
    );

</script>
<%--折线图End--%>

<%--日K线Start--%>
<script>


    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/k'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('dailyKLine'));
                var option = {
                    title: {
                        text: '沪深300日K线'
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
                        data: ['沪深300']
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
                            data: dataDate
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
                            name: '沪深300',
                            type: 'k',
                            data: dataHighLowOpenClose
                            // 开盘，收盘，最低，最高
                        }
                    ]
                };

                myChart.setOption(option);
            }
    );
</script>
<%--日K线End--%>

<%--周K线Start--%>
<script>
    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/k'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('weeklyKLine'));
                var option = {
                    title : {
                        text: '沪深300周K线'
                    },
                    tooltip : {
                        trigger: 'axis',
                        formatter: function (params) {
                            var res = params[0].seriesName + ' ' + params[0].name;
                            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                            return res;
                        }
                    },
                    legend: {
                        data:['沪深300']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataZoom : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    dataZoom : {
                        show : true,
                        realtime: true,
                        start : 50,
                        end : 100
                    },
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : true,
                            axisTick: {onGap:false},
                            splitLine: {show:false},
                            data : dataDate
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            scale:true,
                            boundaryGap: [0.01, 0.01]
                        }
                    ],
                    series : [
                        {
                            name:'上证指数',
                            type:'k',
                            data:[ // 开盘，收盘，最低，最高
                                [2320.26,2302.6,2287.3,2362.94],
                                [2300,2291.3,2288.26,2308.38],
                                [2295.35,2346.5,2295.35,2346.92],
                                [2347.22,2358.98,2337.35,2363.8],
                                [2360.75,2382.48,2347.89,2383.76],
                                [2383.43,2385.42,2371.23,2391.82],
                                [2377.41,2419.02,2369.57,2421.15],
                                [2425.92,2428.15,2417.58,2440.38],
                                [2411,2433.13,2403.3,2437.42],
                                [2432.68,2434.48,2427.7,2441.73],
                                [2430.69,2418.53,2394.22,2433.89],
                                [2416.62,2432.4,2414.4,2443.03],
                                [2441.91,2421.56,2415.43,2444.8],
                                [2420.26,2382.91,2373.53,2427.07],
                                [2383.49,2397.18,2370.61,2397.94],
                                [2378.82,2325.95,2309.17,2378.82],
                                [2322.94,2314.16,2308.76,2330.88],
                                [2320.62,2325.82,2315.01,2338.78],
                                [2313.74,2293.34,2289.89,2340.71],
                                [2297.77,2313.22,2292.03,2324.63],
                                [2322.32,2365.59,2308.92,2366.16],
                                [2364.54,2359.51,2330.86,2369.65],
                                [2332.08,2273.4,2259.25,2333.54],
                                [2274.81,2326.31,2270.1,2328.14],
                                [2333.61,2347.18,2321.6,2351.44],
                                [2340.44,2324.29,2304.27,2352.02],
                                [2326.42,2318.61,2314.59,2333.67],
                                [2314.68,2310.59,2296.58,2320.96],
                                [2309.16,2286.6,2264.83,2333.29],
                                [2282.17,2263.97,2253.25,2286.33],
                                [2255.77,2270.28,2253.31,2276.22],
                                [2269.31,2278.4,2250,2312.08],
                                [2267.29,2240.02,2239.21,2276.05],
                                [2244.26,2257.43,2232.02,2261.31],
                                [2257.74,2317.37,2257.42,2317.86],
                                [2318.21,2324.24,2311.6,2330.81],
                                [2321.4,2328.28,2314.97,2332],
                                [2334.74,2326.72,2319.91,2344.89],
                                [2318.58,2297.67,2281.12,2319.99],
                                [2299.38,2301.26,2289,2323.48],
                                [2273.55,2236.3,2232.91,2273.55],
                                [2238.49,2236.62,2228.81,2246.87],
                                [2229.46,2234.4,2227.31,2243.95],
                                [2234.9,2227.74,2220.44,2253.42],
                                [2232.69,2225.29,2217.25,2241.34],
                                [2196.24,2211.59,2180.67,2212.59],
                                [2215.47,2225.77,2215.47,2234.73],
                                [2224.93,2226.13,2212.56,2233.04],
                                [2236.98,2219.55,2217.26,2242.48],
                                [2218.09,2206.78,2204.44,2226.26],
                                [2199.91,2181.94,2177.39,2204.99],
                                [2169.63,2194.85,2165.78,2196.43],
                                [2195.03,2193.8,2178.47,2197.51],
                                [2181.82,2197.6,2175.44,2206.03],
                                [2201.12,2244.64,2200.58,2250.11],
                                [2236.4,2242.17,2232.26,2245.12],
                                [2242.62,2184.54,2182.81,2242.62],
                                [2187.35,2218.32,2184.11,2226.12],
                                [2213.19,2199.31,2191.85,2224.63],
                                [2203.89,2177.91,2173.86,2210.58],
                                [2170.78,2174.12,2161.14,2179.65],
                                [2179.05,2205.5,2179.05,2222.81],
                                [2212.5,2231.17,2212.5,2236.07],
                                [2227.86,2235.57,2219.44,2240.26],
                                [2242.39,2246.3,2235.42,2255.21],
                                [2246.96,2232.97,2221.38,2247.86],
                                [2228.82,2246.83,2225.81,2247.67],
                                [2247.68,2241.92,2231.36,2250.85],
                                [2238.9,2217.01,2205.87,2239.93],
                                [2217.09,2224.8,2213.58,2225.19],
                                [2221.34,2251.81,2210.77,2252.87],
                                [2249.81,2282.87,2248.41,2288.09],
                                [2286.33,2299.99,2281.9,2309.39],
                                [2297.11,2305.11,2290.12,2305.3],
                                [2303.75,2302.4,2292.43,2314.18],
                                [2293.81,2275.67,2274.1,2304.95],
                                [2281.45,2288.53,2270.25,2292.59],
                                [2286.66,2293.08,2283.94,2301.7],
                                [2293.4,2321.32,2281.47,2322.1],
                                [2323.54,2324.02,2321.17,2334.33],
                                [2316.25,2317.75,2310.49,2325.72],
                                [2320.74,2300.59,2299.37,2325.53],
                                [2300.21,2299.25,2294.11,2313.43],
                                [2297.1,2272.42,2264.76,2297.1],
                                [2270.71,2270.93,2260.87,2276.86],
                                [2264.43,2242.11,2240.07,2266.69],
                                [2242.26,2210.9,2205.07,2250.63],
                                [2190.1,2148.35,2126.22,2190.1]
                            ]
                        }
                    ]
                };




                myChart.setOption(option);
            }
    );
</script>
<%--周K线End--%>


<%--月K线Start--%>
<script>

    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/k'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('monthlyKLine'));
                var option = {
                    title : {
                        text: '沪深300月K线'
                    },
                    tooltip : {
                        trigger: 'axis',
                        formatter: function (params) {
                            var res = params[0].seriesName + ' ' + params[0].name;
                            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                            return res;
                        }
                    },
                    legend: {
                        data:['沪深300']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataZoom : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    dataZoom : {
                        show : true,
                        realtime: true,
                        start : 50,
                        end : 100
                    },
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : true,
                            axisTick: {onGap:false},
                            splitLine: {show:false},
                            data : dataDate
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            scale:true,
                            boundaryGap: [0.01, 0.01]
                        }
                    ],
                    series : [
                        {
                            name:'沪深300',
                            type:'k',
                            data:[ // 开盘，收盘，最低，最高
                                [2320.26,2302.6,2287.3,2362.94],
                                [2300,2291.3,2288.26,2308.38],
                                [2295.35,2346.5,2295.35,2346.92],
                                [2347.22,2358.98,2337.35,2363.8],
                                [2360.75,2382.48,2347.89,2383.76],
                                [2383.43,2385.42,2371.23,2391.82],
                                [2377.41,2419.02,2369.57,2421.15],
                                [2425.92,2428.15,2417.58,2440.38],
                                [2411,2433.13,2403.3,2437.42],
                                [2432.68,2434.48,2427.7,2441.73],
                                [2430.69,2418.53,2394.22,2433.89],
                                [2416.62,2432.4,2414.4,2443.03],
                                [2441.91,2421.56,2415.43,2444.8],
                                [2420.26,2382.91,2373.53,2427.07],
                                [2383.49,2397.18,2370.61,2397.94],
                                [2378.82,2325.95,2309.17,2378.82],
                                [2322.94,2314.16,2308.76,2330.88],
                                [2320.62,2325.82,2315.01,2338.78],
                                [2313.74,2293.34,2289.89,2340.71],
                                [2297.77,2313.22,2292.03,2324.63],
                                [2322.32,2365.59,2308.92,2366.16],
                                [2364.54,2359.51,2330.86,2369.65],
                                [2332.08,2273.4,2259.25,2333.54],
                                [2274.81,2326.31,2270.1,2328.14],
                                [2333.61,2347.18,2321.6,2351.44],
                                [2340.44,2324.29,2304.27,2352.02],
                                [2326.42,2318.61,2314.59,2333.67],
                                [2314.68,2310.59,2296.58,2320.96],
                                [2309.16,2286.6,2264.83,2333.29],
                                [2282.17,2263.97,2253.25,2286.33],
                                [2255.77,2270.28,2253.31,2276.22],
                                [2269.31,2278.4,2250,2312.08],
                                [2267.29,2240.02,2239.21,2276.05],
                                [2244.26,2257.43,2232.02,2261.31],
                                [2257.74,2317.37,2257.42,2317.86],
                                [2318.21,2324.24,2311.6,2330.81],
                                [2321.4,2328.28,2314.97,2332],
                                [2334.74,2326.72,2319.91,2344.89],
                                [2318.58,2297.67,2281.12,2319.99],
                                [2299.38,2301.26,2289,2323.48],
                                [2273.55,2236.3,2232.91,2273.55],
                                [2238.49,2236.62,2228.81,2246.87],
                                [2229.46,2234.4,2227.31,2243.95],
                                [2234.9,2227.74,2220.44,2253.42],
                                [2232.69,2225.29,2217.25,2241.34],
                                [2196.24,2211.59,2180.67,2212.59],
                                [2215.47,2225.77,2215.47,2234.73],
                                [2224.93,2226.13,2212.56,2233.04],
                                [2236.98,2219.55,2217.26,2242.48],
                                [2218.09,2206.78,2204.44,2226.26],
                                [2199.91,2181.94,2177.39,2204.99],
                                [2169.63,2194.85,2165.78,2196.43],
                                [2195.03,2193.8,2178.47,2197.51],
                                [2181.82,2197.6,2175.44,2206.03],
                                [2201.12,2244.64,2200.58,2250.11],
                                [2236.4,2242.17,2232.26,2245.12],
                                [2242.62,2184.54,2182.81,2242.62],
                                [2187.35,2218.32,2184.11,2226.12],
                                [2213.19,2199.31,2191.85,2224.63],
                                [2203.89,2177.91,2173.86,2210.58],
                                [2170.78,2174.12,2161.14,2179.65],
                                [2179.05,2205.5,2179.05,2222.81],
                                [2212.5,2231.17,2212.5,2236.07],
                                [2227.86,2235.57,2219.44,2240.26],
                                [2242.39,2246.3,2235.42,2255.21],
                                [2246.96,2232.97,2221.38,2247.86],
                                [2228.82,2246.83,2225.81,2247.67],
                                [2247.68,2241.92,2231.36,2250.85],
                                [2238.9,2217.01,2205.87,2239.93],
                                [2217.09,2224.8,2213.58,2225.19],
                                [2221.34,2251.81,2210.77,2252.87],
                                [2249.81,2282.87,2248.41,2288.09],
                                [2286.33,2299.99,2281.9,2309.39],
                                [2297.11,2305.11,2290.12,2305.3],
                                [2303.75,2302.4,2292.43,2314.18],
                                [2293.81,2275.67,2274.1,2304.95],
                                [2281.45,2288.53,2270.25,2292.59],
                                [2286.66,2293.08,2283.94,2301.7],
                                [2293.4,2321.32,2281.47,2322.1],
                                [2323.54,2324.02,2321.17,2334.33],
                                [2316.25,2317.75,2310.49,2325.72],
                                [2320.74,2300.59,2299.37,2325.53],
                                [2300.21,2299.25,2294.11,2313.43],
                                [2297.1,2272.42,2264.76,2297.1],
                                [2270.71,2270.93,2260.87,2276.86],
                                [2264.43,2242.11,2240.07,2266.69],
                                [2242.26,2210.9,2205.07,2250.63],
                                [2190.1,2148.35,2126.22,2190.1]
                            ]
                        }
                    ]
                };

                myChart.setOption(option);
            }
    );
</script>
<%--月K线End--%>

</body>
</html>


