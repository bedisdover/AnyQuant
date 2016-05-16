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
    <link href="/images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/marketStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/tab.js"></script>


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
    <script src="${pageContext.request.contextPath}/page/js/echarts.js"></script>

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


    <script type="text/javascript">

//        zooming.html
        function lineChart() {

            var line1 = [[]];
            <!-- data和volume是request中属性的名字-->
            <c:forEach items="${date}" var="date1" varStatus="loop">
            var date1 = "${date1}"
            var quantity = "${volume[loop.count-1]}"
            line1[0].push([date1, quantity]);
            </c:forEach>
            //alert(line1);


            var plot1 = $.jqplot('chart', line1, {
                title: 'Google, Inc.',
                series: [{
                    label: 'Google, Inc.',
                    neighborThreshold: -1
                }],
                axes: {
                    xaxis: {
                        renderer: $.jqplot.DateAxisRenderer,
                        tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                        tickOptions: {
                            angle: -30
                        }
                    },
                    yaxis: {
                        renderer: $.jqplot.LogAxisRenderer,
                        tickOptions: {prefix: '$'}
                    }
                },
                cursor: {
                    show: true,
                    zoom: true
                }
            });
        }

//日K线 undone
        function dailyKLine(){
            var line1 = [[]];
            <!-- data和volume是request中属性的名字-->
            <c:forEach items="${date}" var="dateShow" varStatus="loop">
            var dateShow = "${dateShow}"
            var highShow = "${high[loop.count-1]}"
            var lowShow= "${low[loop.count-1]}"
            var openShow= "${open[loop.count-1]}"
            var closeShow= "${close[loop.count-1]}"
            line1[0].push([dateShow, highShow,lowShow,openShow,closeShow]);
            </c:forEach>

            var option = {
                title : {
                    text: '2013年上半年上证指数'
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
                    data:['上证指数']
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
                        data : [
                            "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                            "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6",
                            "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20",
                            "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27",
                            "2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6",

                        ]
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
                            [2190.1,2148.35,2126.22,2190.1]
                        ]
                    }
                ]
            };



        }
    </script>
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
                    <a href="#broken-line" aria-controls="broken-line" role="tab" data-toggle="tab"
                       onclick="lineChart()">折线图</a>
                </li>
                <li role="presentation">
                    <a href="#daily_k" aria-controls="daily_k" role="tab" data-toggle="tab"
                    onclick="dailyKLine()">日K线</a>
                </li>
                <li role="presentation">
                    <a href="#weekly_k" aria-controls="weekly_k" role="tab" data-toggle="tab">周K线</a>
                </li>
                <li role="presentation">
                    <a href="#monthly_k" aria-controls="monthly_k" role="tab" data-toggle="tab">月K线</a>
                </li>
            </ul>


            <div class="tab-content">
                <%--<div role="tabpanel" class="tab-pane active" id="time-series">--%>
                <%--<img src="<%=graphURL_1%>">--%>
                <%--</div>--%>
                <%--<div role="tabpanel" class="tab-pane" id="broken-line">--%>
                <%--<img src="<%=graphURL_2%>">--%>
                <%--</div>--%>
                <%--<div role="tabpanel" class="tab-pane" id="daily_k">--%>
                <%--<img src="<%=graphURL_3%>">--%>
                <%--</div>--%>
                <div role="tabpanel" class="tab-pane" id="weekly_k">test4</div>
                <div role="tabpanel" class="tab-pane" id="monthly_k">test5</div>
            </div>
        </div>
    </div>
    content
    <div class="col-md-1">

    </div>

    <div id="chart" style="width: 800px; height: 500px;"></div>

    <footer class=bs-docs-footer role=contentinfo>
        <jsp:include page="footer.jsp"/>
    </footer>
    </div>
</body>

</html>