<%@ page import="java.util.List" %>
<%@ page import="vo.StockVO" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--自选</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="../images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="style/daterangepicker.css"/>
    <link href="style/portfolioStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
    session.setAttribute("which_button", "p");
%>
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
                    <li class="active">
                        <a href="javascript: void(0)">
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

<%
    List<StockVO> stockList = (List<StockVO>) request.getAttribute("stockList");
%>

<div class="row">
    <div class="col-xs-6 col-md-2" id="sidebar">
        <div class="menu list-group">
            <a href="#" class="list-group-item active">实时数据</a>
            <a href="#" class="list-group-item">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表</a>
            <a href="#" class="list-group-item">详细数据</a>
            <a href="#" class="list-group-item">行情预测</a>
            <a href="#" class="list-group-item">最新资讯</a>

        </div>
        <div class="stockList">
            <div class="accordion-group">
                <table class="table table-hover text-center">
                    <tr>
                        <td>名称</td>
                        <td>代码</td>
                    </tr>
                    <%
                        StockVO stock;
                        for (int i = 0; i < stockList.size(); i++) {
                            stock = stockList.get(i);
                    %>
                    <tr>
                        <td class="stock">
                            <a class="accordion-toggle collapsed" data-toggle="collapse"
                               data-parent="#accordion-102144"
                               href="#accordion-element-<%=i%>"><%=stock.getName()%>
                            </a>
                        </td>
                        <td><%=stock.getId()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </div>
    <div class="col-xs-6 col-md-9" id="main-content">
        <%
            for (int i = 0; i < stockList.size(); i++) {
                stock = stockList.get(i);
        %>
        <div id="accordion-element-<%=i%>" class="accordion-body collapse">
            <div class="accordion-inner">
                <div class="primary-info">
                    <div class="row latest-data panel panel-primary"><!--最新数据-->
                        <div class="col-md-2 name-code">
                            <P class="text-center name"><%=stock.getName()%>
                            </P>
                            <p class="text-center code">（<%=stock.getId()%>）</p>
                        </div>
                        <div class="col-md-4">
                            <div class="inc-dec">
                                <p class="text-left text-danger price">
                                    366.68
                                    <small>
                                        <small>
                                            <small class="text-success">-6.16 (-1.65%)</small>
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
                </div>
                <%--简要信息栏End--%>
                <div class="graphs panel panel-primary">
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
                        <div class="tab-pane fade in active" id="分时图">分时图</div>
                        <div class="tab-pane fade" id="折线图">折线图</div>
                        <div class="tab-pane fade" id="月K线">月K线</div>
                        <div class="tab-pane fade" id="周K线">周K线</div>
                        <div class="tab-pane fade" id="日K线">...</div>
                    </div>
                </div>
                <%--统计图End--%>
                <div class="history-data well">
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

                                for (int j = stock.getDate().length - 1; j >= 0; j--) {
                            %>
                            <tr>
                                <td><%=stock.getDate()[j]%>
                                </td>
                                <td><%=stock.getHigh()[j]%>
                                </td>
                                <td><%=stock.getLow()[j]%>
                                </td>
                                <td><%=stock.getIncrease_decreaseNum()[j]%>
                                </td>
                                <td><%=stock.getIncrease_decreaseRate()[j]%>
                                </td>
                                <td><%=stock.getOpen()[j]%>
                                </td>
                                <td><%=stock.getClose()[j]%>
                                </td>
                                <td><%=stock.getVolume()[j]%>
                                </td>
                                <td><%=stock.getPe_ttm()[j]%>
                                </td>
                                <td><%=stock.getPb()[j]%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%--历史数据--%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<%--content--%>
<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/jquery.daterangepicker.js"></script>
<script>//日期选择框
$('#dom-id').dateRangePicker(configObject);
</script>
<script>
    var stockNum = <%=stockList.size()%>;//股票数量

    window.onload = function () {
        $('#accordion-element-0').collapse('show');
    };


    for (var i = 0; i < stockNum; i++) {
        $('#accordion-element-' + i).on('show.bs.collapse', function () {
            hideAll();
        });
//        $('#accordion-element-' + i).on('shown.bs.collapse', function () {
//            $('#stock-' + i).className("disable");
//        };
//        $('#accordion-element-' + i).on('hide.bs.collapse', function () {
//
//        });
    }

    function hideAll() {//隐藏所有股票信息
        for (var i = 0; i < stockNum; i++) {
            $('#accordion-element-' + i).collapse('hide');
        }
    }
</script>
</body>
</html>