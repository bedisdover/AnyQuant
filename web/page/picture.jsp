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
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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
    List<StockVO> increase_rank = (List<StockVO>) request.getAttribute("increase_rank");
    List<StockVO> decrease_rank = (List<StockVO>) request.getAttribute("decrease_rank");
    List<StockVO> volume_rank = (List<StockVO>) request.getAttribute("volume_rank");
%>

<div class="row">
    <div class="col-xs-6 col-md-2" id="sidebar">
        <div class="menu list-group">
            <a href="#" class="list-group-item active">涨幅榜</a>
            <a href="#" class="list-group-item">跌幅榜</a>
            <a href="#" class="list-group-item">成交量榜</a>
        </div>
    </div>
    <div class="col-xs-6 col-md-9">
        <div class="rank-list well-lg">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse"
                               href="#collapseOne">
                                <div class="table-responsive">
                                    <table class="table table-striped text-center">
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
                                        <tbody>
                                        <%

                                            for (StockVO stock : increase_rank) {
                                        %>
                                        <tr>
                                            <td><%=stock.getName()%></td>
                                            <td><%=stock.getId()%></td>
                                            <td><%=stock.getHigh()[0]%></td>
                                            <td><%=stock.getLow()[0]%></td>
                                            <td><%=stock.getIncrease_decreaseNum()[0]%></td>
                                            <td><%=stock.getIncrease_decreaseRate()[0]%></td>
                                            <td><%=stock.getOpen()[0]%></td>
                                            <td><%=stock.getClose()[0]%></td>
                                            <td><%=stock.getVolume()[0]%></td>
                                            <td><%=stock.getPe_ttm()[0]%></td>
                                            <td><%=stock.getPb()[0]%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                            cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                            vice lomo.
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse"
                               href="#collapseTwo">
                                点击我进行展开，再次点击我进行折叠。第 2 部分
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                            cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                            vice lomo.
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse"
                               href="#collapseThree">
                                点击我进行展开，再次点击我进行折叠。第 3 部分
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson
                            cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
                            vice lomo.
                        </div>
                    </div>
                </div>
            </div>
        </div><%--排行榜End--%>
    </div>
</div>
<footer class=bs-docs-footer role=contentinfo>
    <jsp:include page="footer.jsp"/>
</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>//默认展开榜单
$(function () { $('#collapseTwo').collapse('show')});
</script>
</body>
</html>