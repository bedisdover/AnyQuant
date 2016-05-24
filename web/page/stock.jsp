<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-23
  Time: 下午1:08
--%>
<%@ page import="bl.ManageSelfSelectStock" %>
<%@ page import="bl.ShowCurrentData" %>
<%@ page import="vo.CurrentStockVO" %>
<%@ page import="vo.StockNewsVO" %>
<%@ page import="vo.StockVO" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="static util.Format.dateFormat" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="zh-CN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    StockVO stockVO = (StockVO) request.getAttribute("stockVO");
    String userID = (String) session.getAttribute("userID");

    List<StockNewsVO> newsList = (List<StockNewsVO>) request.getAttribute("newsList");

    boolean followed = false;

    if (userID != null) {
        ManageSelfSelectStock selfSelectStock = new ManageSelfSelectStock();
        List<String> stockList = selfSelectStock.getAllInterestedStock(userID);

        followed = stockList.contains(stockVO.getId());
    }

    String textColor;
%>
<head>
    <title>AnyQuant--<%=stockVO.getName()%>
    </title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../images/icon.png" rel="icon"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/stockStyle.css" rel="stylesheet" type="text/css"/>
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
                    <li>
                        <a href="market.jsp">
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
                <span class="btn btn-danger" id="add-portfolio" data-toggle="button">
                    添加自选&nbsp;&nbsp;<span class="glyphicon glyphicon-plus"></span></span>
                <span class="btn btn-danger" id="minus-portfolio" data-toggle="button">
                    取消自选&nbsp;&nbsp;<span class="glyphicon glyphicon-minus"></span></span>
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
                        <li><a href="stockContrast.jsp?id=<%=stockVO.getId()%>"
                               target="_blank">股票对比</a></li>
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
                                    <P class="text-center name"><%=stockVO.getName()%>
                                    </P>
                                    <p class="text-center code">（<%=stockVO.getId()%>）</p>
                                </div>
                            </div>
                            <%
                                ShowCurrentData currentData = new ShowCurrentData();
                                CurrentStockVO currentStockVO = currentData.showCurrentData(stockVO.getId());
                                if (currentStockVO.getIncrease_decreaseNum().charAt(0) == '-') {
                                    textColor = "text-success";
                                } else if (currentStockVO.getIncrease_decreaseNum().equals("0")) {
                                    textColor = "";
                                } else {
                                    textColor = "text-danger";
                                }
                            %>
                            <div class="col-md-3">
                                <div class="inc-dec">
                                    <p class="text-left <%=textColor%> price">
                                        <%=currentStockVO.getCurrentPrice()%>
                                        <small>
                                            <small>
                                                <small class="<%=textColor%>">
                                                    <%=currentStockVO.getIncrease_decreaseNum()%>
                                                    (<%=currentStockVO.getIncrease_decreaseRate()%>)
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
                                            <p class="text-left">今开：<%=currentStockVO.getOpen()%>
                                            </p>
                                        </div>
                                        <div class="col-md-3">
                                            <p class="text-left">最高：<%=currentStockVO.getHigh()%>
                                            </p>
                                        </div>
                                        <div class="col-md-5">
                                            <P class="text-left">成交量：<%=currentStockVO.getVolume()%>
                                            </P>
                                        </div>
                                    </div>
                                    <div class="data-bottom"><%--底部数据，包含昨收、最低、成交额--%>
                                        <div class="col-md-3 col-md-offset-1">
                                            <p class="text-left">昨收：<%=currentStockVO.getClose()%>
                                            </p>
                                        </div>
                                        <div class="col-md-3">
                                            <p class="text-left">最低：<%=currentStockVO.getLow()%>
                                            </p>
                                        </div>
                                        <div class="col-md-5">
                                            <P class="text-left">成交额：<%=currentStockVO.getVolume_value()%>
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
                                <div class="tab-pane fade in active" id="分时图">分时图</div>
                                <div class="tab-pane fade" id="折线图">折线图</div>
                                <div class="tab-pane fade" id="月K线">月K线</div>
                                <div class="tab-pane fade" id="周K线">周K线</div>
                                <div class="tab-pane fade" id="日K线">...</div>
                            </div>
                        </div>
                    </div>
                    <%--统计图End--%>
                    <div class="history-data" id="history-data">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                详细数据
                            </div>
                            <div class="options">
                                <button type="button" class="btn btn-primary" onclick="showDatePicker()">日期范围
                                </button>
                                <label id="date" style="display: none;">
                                    <%
                                        Calendar calendar = Calendar.getInstance();
                                        String endDate = stockVO.getDate()[stockVO.getDate().length - 1];
                                        try {
                                            calendar.setTime(dateFormat.parse(endDate));
                                        } catch (ParseException e) {
                                            //若发生异常，设为当前时间
                                            calendar.getTime();
                                        }
                                        calendar.add(Calendar.MONTH, -1);
                                        String startDate = dateFormat.format(calendar.getTime());
                                    %>
                                    <input id="startDate" value=<%=startDate%>> 至
                                    <input id="endDate" readonly value="<%=endDate%>">
                                </label>
                            </div>
                            <div class="table-responsive">
                            </div>
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
                                    for (int i = stockVO.getDate().length - 1; i >= 0; i--) {
                                        if (stockVO.getIncrease_decreaseNum()[i] > 0) {
                                            textColor = "text-danger";
                                        } else if (stockVO.getIncrease_decreaseNum()[i] < 0) {
                                            textColor = "text-success";
                                        } else {
                                            textColor = "";
                                        }
                                %>
                                <tr>
                                    <td><%=stockVO.getDate()[i]%>
                                    </td>
                                    <td><%=stockVO.getHigh()[i]%>
                                    </td>
                                    <td><%=stockVO.getLow()[i]%>
                                    </td>
                                    <td class="<%=textColor%>"><%=stockVO.getIncrease_decreaseNum()[i]%>
                                    </td>
                                    <td class="<%=textColor%>"><%=stockVO.getIncrease_decreaseRate()[i]%>
                                    </td>
                                    <td><%=stockVO.getOpen()[i]%>
                                    </td>
                                    <td><%=stockVO.getClose()[i]%>
                                    </td>
                                    <td><%=stockVO.getVolume()[i]%>
                                    </td>
                                    <td><%=stockVO.getPe_ttm()[i]%>
                                    </td>
                                    <td><%=stockVO.getPb()[i]%>
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
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                行情预测
                            </div>
                            <div class="panel-body">
                                <div class="chart" id="radar-chart"></div>
                            </div>
                        </div>
                    </div>
                    <%--行情预测End--%>
                    <div id="stock-news">
                        <div class="accordion" id="accordion-102144">
                            <div class="accordion-group">
                                <%
                                    for (int i = 0; i < newsList.size(); i++) {
                                        String title = newsList.get(i).getTitle();
                                        String content = newsList.get(i).getContent();
                                %>

                                <h2>
                                    <a class="accordion-toggle collapsed" data-toggle="collapse"
                                       data-parent="#accordion-102144"
                                       href="#accordion-element-<%=i%>"><%=title%>
                                    </a>
                                </h2>
                                <div id="accordion-element-<%=i%>" class="accordion-body collapse in">
                                    <div class="accordion-inner well">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=content%>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <%--实时资讯End--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--content End--%>

<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/jquery.daterangepicker.js"></script>
<script src="js/stock.js"></script>

<!-- ECharts单文件引入 -->
<script src="js/dist/echarts.js"></script>

<%--添加/取消关注切换Start--%>
<script>
    $(function () {
        if (<%=followed%>) {
            $('#add-portfolio').hide();
        } else {
            $('#minus-portfolio').hide();
        }
    });

    $('#add-portfolio').on('click', function () {
        <%--if (<%=userID == null%>) {//若未登陆，跳转至登陆界面--%>
//            top.location = 'login.jsp';
//        } else {//添加自选
        $.ajax({
            url: '/portfolio',
            type: 'POST',
            data: 'userID=<%=userID%>&add=<%=stockVO.getId()%>',
            success: function () {
                $('#add-portfolio').hide();
                $('#minus-portfolio').show();
            }
        });
//        }
    });
    $('#minus-portfolio').on('click', function () {//取消自选
        $.ajax({
            url: '/portfolio',
            type: 'POST',
            data: 'userID=<%=userID%>&minus=<%=stockVO.getId()%>',
            success: function () {
                $('#minus-portfolio').hide();
                $('#add-portfolio').show();
            }
        });
    });
</script>
<%--添加/取消关注切换End--%>

<%--侧边栏导航Start--%>
<script>
    var menu = document.getElementById('menu');
    var items = menu.getElementsByTagName('li');
    function page_jump(item, target) {
        var selected = getSelected();

        items[selected].className = 'list-group-item text-center';
        item.className = 'list-group-item text-center active';

        scroll(target);
    }

    function getSelected() {
        for (var i = 0; i < items.length; i++) {
            if (items[i].className == 'list-group-item text-center active') {
                return i;
            }
        }
    }

    function scroll(target) {
        var scroll_offset = $("#" + target).offset();  //得到pos这个div层的offset，包含两个值，top和left
        $('body, html').animate({
            scrollTop: scroll_offset.top - 60,  //让body的scrollTop等于pos的top，就实现了滚动
        }, 0);
    }
</script>
<%--侧边栏导航End--%>

<%--雷达图Start--%>
<script>
    // 路径配置
    require.config({
        paths: {
            echarts: 'js/dist'
        }
    });
    // 使用
    require(
            [
                'echarts',
                'echarts/chart/radar' // 使用雷达图就加载radar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('radar-chart'), 'helianthus');

                var option = {
                    title: {
                        text: '<%=stockVO.getName()%>',
                        subtext: '银行股指标分析'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        x: 'center',
                        data: ['华夏银行', '南京银行']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: false},
                            dataView: {show: false, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    polar: [
                        {
                            indicator: [
                                {text: '乖离率', max: 100},
                                {text: '相对强弱指标', max: 100},
                                {text: '威廉超买超卖指标', max: 100},
                                {text: '人气指标', max: 100},
                                {text: '意愿指标', max: 100}
                            ],
                            radius: 130
                        }
                    ],
                    series: [
                        {
                            name: '银行股指标数据',
                            type: 'radar',
                            itemStyle: {
                                normal: {
                                    areaStyle: {
                                        type: 'default'
                                    }
                                }
                            },
                            data: [
                                {
                                    value: [97, 42, 88, 94, 90],
                                    name: '华夏银行'
                                },
                                {
                                    value: [97, 32, 74, 95, 88],
                                    name: '南京银行'
                                }
                            ]
                        }
                    ]
                };

                // 为echarts对象加载数据
                myChart.setOption(option);
            }
    );
</script>
<%--雷达图End--%>

<%--日期选择框Start--%>
<script>
    var showDatePicker = function () {//显示日期选择框
        $('#date').css({
            display: "inline"
        });
    };
    $('#startDate, #endDate').dateRangePicker({
        format: 'YYYY-MM-DD',
        language: 'cn',
        startDate: '2016-01-01',
        endDate: '2016-05-17',
        autoClose: true,

        getValue: function () {
            if ($('#startDate').val() && $('#endDate').val())
                return $('#startDate').val() + ' to ' + $('#endDate').val();
            else
                return '';
        },
        setValue: function (s, s1, s2) {
            $('#startDate').val(s1);
            $('#endDate').val(s2);
        }
    });
    $('#endDate').dateRangePicker().bind('datepicker-change', function (event, obj) {
        console.log($('#startDate').val());
        console.log($('#endDate').val());
        //todo 刷新
    });
</script>
<%--日期选择框End--%>


</body>
</html>