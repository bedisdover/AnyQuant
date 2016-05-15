<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
    <link rel="stylesheet" type="text/css" hrf="style/jquery.jqplot.min.css" />




    <%
        long volume[]=new long[0];
        String date[]=new String[0];
        double high[]=new double[0];
        double adj_price[]=new double[0];
        double low[]=new double[0];
        double close[]=new double[0];
        double open[]=new double[0];
        double increase_decreaseRate[]=new double[0];
        double increase_decreaseNum[]=new double[0];
        if(request.getAttribute("volume")==null  ){
            System.out.println("哎哟喂空的欸！volume");
        }else if(request.getAttribute("date")==null){
            System.out.println("哎哟喂空的欸！date");
        }else {
            volume= (long[]) request.getAttribute("volume");
            date= (String[]) request.getAttribute("date");
            high=(double[]) request.getAttribute("high");
            adj_price=(double[]) request.getAttribute("adj_price");
            low=(double[]) request.getAttribute("low");
            close=(double[]) request.getAttribute("close");
            increase_decreaseRate=(double[]) request.getAttribute("open");
            increase_decreaseNum=(double[]) request.getAttribute("increase_decreaseRate");
//             System.out.println(volume[0]+"aiyowei");
//             System.out.println(date[0]+"yoyoyo");
        }
    %>




    <script type="text/javascript">
        function lineChart(){
//            goog = [["6/22/2009",425.32], ["6/8/2009",424.84], ["5/26/2009",417.23], ["5/11/2009",390],
//                ["4/27/2009",393.69], ["4/13/2009",392.24], ["3/30/2009",369.78], ["3/16/2009",330.16], ["3/2/2009",308.57],
//                ["2/17/2009",346.45], ["2/2/2009",371.28], ["1/20/2009",324.7], ["1/5/2009",315.07], ["12/22/2008",300.36],
//                ["12/8/2008",315.76], ["11/24/2008",292.96], ["11/10/2008",310.02], ["10/27/2008",359.36], ["10/13/2008",372.54],
//                ["9/29/2008",386.91], ["9/15/2008",449.15], ["9/2/2008",444.25], ["8/25/2008",463.29],  ["8/11/2008",510.15],
//                ["7/28/2008",467.86], ["7/14/2008",481.32], ["6/30/2008",537], ["6/16/2008",546.43], ["6/2/2008",567],
//                ["5/19/2008",544.62], ["5/5/2008",573.2], ["4/21/2008",544.06], ["4/7/2008",457.45], ["3/24/2008",438.08],
//                ["3/10/2008",437.92], ["2/25/2008",471.18], ["2/11/2008",529.64], ["1/28/2008",515.9], ["1/14/2008",600.25],
//                ["12/31/2007",657], ["12/17/2007",696.69], ["12/3/2007",714.87], ["11/19/2007",676.7], ["11/5/2007",663.97],
//                ["10/22/2007",674.6], ["10/8/2007",637.39], ["9/24/2007",567.27], ["9/10/2007",528.75], ["8/27/2007",515.25]];

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
                        renderer:$.jqplot.DateAxisRenderer,
                        tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                        tickOptions: {
                            angle: -30
                        }
                    },
                    yaxis: {
                        renderer: $.jqplot.LogAxisRenderer,
                        tickOptions:{ prefix: '$' }
                    }
                },
                cursor:{
                    show: true,
                    zoom: true
                }
            });
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
                    <a href="#broken-line" aria-controls="broken-line" role="tab" data-toggle="tab" onclick="lineChart()">折线图</a>
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

<%--row--%>
<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
</body>

</html>