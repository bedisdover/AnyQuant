<%@ page import="vo.StockVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--行情</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../reference/table/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="../reference/table/css/default.css">
    <link type="text/css" href="../reference/table/css/styles.css" media="all" rel="stylesheet"/>
    <link type="text/css" href="../reference/table/css/stickysort.css" media="all" rel="stylesheet"/>
    <link href="style/pictureStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="true">
                    <%--<span class="sr-only">Toggle navigation</span>--%>
                    <%--<!--todo icon-->--%>
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
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="glyphicon glyphicon-signal"></span> 大盘
                            <strong class="caret"></strong></a>
                        <ul class="dropdown-menu inverse">
                            <li>
                                <a href="market.jsp">上证指数</a>
                            </li>
                            <li>
                                <a href="market.jsp">深证成指</a>
                            </li>
                            <li>
                                <a href="market.jsp">沪深300</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="javascript: void(0)">
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

<%
    List<StockVO> increase_rank = (List<StockVO>) session.getAttribute("increase_rank");
    List<StockVO> decrease_rank = (List<StockVO>) session.getAttribute("decrease_rank");
    List<StockVO> volume_rank = (List<StockVO>) session.getAttribute("volume_rank");

    int stock_num = increase_rank.size();//股票数目
    int increase_num = 0;//涨幅大于0的股票数目

    if (increase_rank.get(0).getIncrease_decreaseNum()[0] > 0) {
        for (int i = 0; i < increase_rank.size(); i++) {
            if (increase_rank.get(i).getIncrease_decreaseNum()[0] <= 0) {
                increase_num = i;
                break;
            }
        }
    }
%>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-6 col-md-2">
            <div id="sidebar">
                <div class="list-group">
                    <ul class="menu">
                        <li onclick="click_scroll('volume_rank')">
                            <a href="javascript: void(0)" class="list-group-item
                                    list-group-item-warning text-left active">今日行情
                                <span class="badge" title="股票总数"><%=stock_num%></span>
                            </a>
                        </li>
                        <li onclick="click_scroll('increase_rank')">
                            <a href="javascript: void(0)" class="list-group-item
                        list-group-item-danger text-left active">涨幅榜
                                <span class="badge" title="上涨数量"><%=increase_num%></span></a>
                        </li>
                        <li onclick="click_scroll('decrease_rank')">
                            <a href="javascript: void(0)" class="list-group-item
                        list-group-item-success text-left active">
                                跌幅榜<span class="badge" title="下跌数量"><%=stock_num - increase_num%></span></a>
                        </li>
                        <li onclick="click_scroll('volume_rank')">
                            <a href="javascript: void(0)" class="list-group-item
                            text-left active">成交量榜</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-10">
            <div class="rank-list well">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#increase_rank">涨幅榜</a>
                            </h4>
                        </div>
                        <div id="increase_rank" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <div id="basic-sort-0">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            <tfoot>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            </tfoot>
                                            <tbody>
                                            <%
                                                String textColor;
                                                for (StockVO stock : increase_rank) {
                                                    if (stock.getIncrease_decreaseNum()[0] > 0) {
                                                        textColor = "text-danger";
                                                    } else if (stock.getIncrease_decreaseNum()[0] < 0) {
                                                        textColor = "text-success";
                                                    } else {
                                                        textColor = "";
                                                    }
                                            %>
                                            <tr>
                                                <td class="text-center"><%=stock.getName()%>
                                                </td>
                                                <td class="text-center"><%=stock.getId()%>
                                                </td>
                                                <td class="text-center"><%=stock.getHigh()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getLow()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.getIncrease_decreaseNum()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.rateToString(stock.getIncrease_decreaseRate()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getOpen()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getClose()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.volumeToString(stock.getVolume()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getPe_ttm()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getPb()[0]%>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-success active">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#decrease_rank">跌幅榜</a>
                            </h4>
                        </div>
                        <div id="decrease_rank" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <div id="basic-sort-1">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            <tfoot>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            </tfoot>
                                            <tbody>
                                            <%
                                                for (StockVO stock : decrease_rank) {
                                                    if (stock.getIncrease_decreaseNum()[0] > 0) {
                                                        textColor = "text-danger";
                                                    } else if (stock.getIncrease_decreaseNum()[0] < 0) {
                                                        textColor = "text-success";
                                                    } else {
                                                        textColor = "";
                                                    }
                                            %>
                                            <tr>
                                                <td class="text-center"><%=stock.getName()%>
                                                </td>
                                                <td class="text-center"><%=stock.getId()%>
                                                </td>
                                                <td class="text-center"><%=stock.getHigh()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getLow()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.getIncrease_decreaseNum()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.rateToString(stock.getIncrease_decreaseRate()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getOpen()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getClose()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.volumeToString(stock.getVolume()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getPe_ttm()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getPb()[0]%>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#volume_rank">成交量榜</a>
                            </h4>
                        </div>
                        <div id="volume_rank" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <div id="basic-sort-2">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            <tfoot>
                                            <tr>
                                                <th class="text-center">名称</th>
                                                <th class="text-center">代码</th>
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
                                            </tfoot>
                                            <tbody>
                                            <%
                                                for (StockVO stock : volume_rank) {
                                                    if (stock.getIncrease_decreaseNum()[0] > 0) {
                                                        textColor = "text-danger";
                                                    } else if (stock.getIncrease_decreaseNum()[0] < 0) {
                                                        textColor = "text-success";
                                                    } else {
                                                        textColor = "";
                                                    }
                                            %>
                                            <tr>
                                                <td class="text-center"><%=stock.getName()%>
                                                </td>
                                                <td class="text-center"><%=stock.getId()%>
                                                </td>
                                                <td class="text-center"><%=stock.getHigh()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getLow()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.getIncrease_decreaseNum()[0]%>
                                                </td>
                                                <td class="text-center <%=textColor%>"><%=stock.rateToString(stock.getIncrease_decreaseRate()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getOpen()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getClose()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.volumeToString(stock.getVolume()[0])%>
                                                </td>
                                                <td class="text-center"><%=stock.getPe_ttm()[0]%>
                                                </td>
                                                <td class="text-center"><%=stock.getPb()[0]%>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--排行榜End--%>
        </div>
    </div>
</div>

<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/picture.js"></script>
<%--表格排序组件--%>
<script src="../reference/table/js/jquery.ba-throttle-debounce.min.js"></script>
<script src="../reference/table/js/jquery.stickysort.js"></script>

</body>
</html>