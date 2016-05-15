<%@ page import="servlet.PortfolioServlet" %>
<%@ page import="vo.StockNewsVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-4-28
  Time: 下午11:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AnyQuant</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="images/icon.png" rel="icon"/>
    <link href="page/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="page/style/docs.min.css" rel="stylesheet" type="text/css"/>
    <link href="page/style/homeStyle.css" rel="stylesheet" type="text/css"/>
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
                <a class="navbar-brand" href="javascript: void(0)">AnyQuant</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left">
                    <li class="active">
                        <a href="javascript: void(0)">
                            <span class="glyphicon glyphicon-home"></span> 首页
                        </a>
                    </li>
                    <li>
                        <a href="page/portfolio.jsp">
                            <span class="glyphicon glyphicon-heart"></span> 自选
                        </a>
                    </li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="glyphicon glyphicon-signal"></span> 大盘
                            <strong class="caret"></strong></a>
                        <ul class="dropdown-menu inverse">
                            <li>
                                <a href="page/market.jsp">上证指数</a>
                            </li>
                            <li>
                                <a href="page/market.jsp">深证成指</a>
                            </li>
                            <li>
                                <a href="page/market.jsp">沪深300</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="page/picture.jsp">
                            <span class="glyphicon glyphicon-th-list"></span> 行情
                        </a>
                    </li>
                    <li>
                        <a href="page/history.jsp">
                            <span class="glyphicon glyphicon-time"></span> 历史
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="page/login.jsp">
                            <span class="glyphicon glyphicon-user"></span> 登录
                        </a>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>

<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <main class=bs-docs-masthead>
                <%--<div class=container>--%>
                <span class="bs-docs-booticon-lg">AnyQuant</span>
                <div class="container"></div>
                <p class=lead>
                    在线股票分析平台，提供数据查询及股票分析服务
                </p>
                <%--</div>--%>
            </main>
        </div>
        <div class="item">
            <main class=bs-docs-masthead>
                <div class=container>
                    <span class=" bs-docs-booticon-lg">AnyQuant</span>
                    <div class="container"></div>
                    <p class=lead>
                        在线股票分析平台，提供数据查询及股票分析服务
                    </p>
                </div>
            </main>
        </div>
        <div class="item">
            <main class=bs-docs-masthead>
                <div class=container>
                    <span class=" bs-docs-booticon-lg">AnyQuant</span>
                    <div class="container"></div>
                    <p class=lead>
                        在线股票分析平台，提供数据查询及股票分析服务
                    </p>
                </div>
            </main>
        </div>
        <div class="item">
            <main class=bs-docs-masthead>
                <div class=container>
                    <span class=" bs-docs-booticon-lg">AnyQuant</span>
                    <div class="container"></div>
                    <p class=lead>
                        在线股票分析平台，提供数据查询及股票分析服务
                    </p>
                </div>
            </main>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div>

<div class="bs-docs-featurette describe">
    <div class="container">
        <h2 class=bs-docs-featurette-title>AnyQuant初体验</h2>
        <p class="lead">开启AnyQuant之旅</p>
        <div class="row bs-docs-featured-sites">
            <div class="col-xs-6 col-sm-3">
                <div class="describe-box">
                    <p class="lead">实时数据</p>
                    &nbsp;&nbsp;实时准确的股票数据，全方位数据展示
                </div>
            </div>
            <div class="col-xs-6 col-sm-3">
                <div class="describe-box">
                    <p class="lead">行情分析</p>
                    &nbsp;&nbsp;在线股票分析平台，提供数据查询及股票分析服务
                </div>
            </div>
            <div class="col-xs-6 col-sm-3">
                <div class="describe-box">
                    <p class="lead">大盘走势</p>
                    &nbsp;&nbsp;在线股票分析平台，提供数据查询及股票分析服务
                </div>
            </div>
            <div class="col-xs-6 col-sm-3">
                <div class="describe-box">
                    <p class="lead">精准预测</p>
                    &nbsp;&nbsp;在线股票分析平台，提供数据查询及股票分析服务
                </div>
            </div>
        </div>
    </div>
</div>

<%
    List<StockNewsVO> stockNews = (List<StockNewsVO>) request.getAttribute("news");
%>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="container">
            <div class="accordion" id="accordion-102144">
                <div class="accordion-group">
                    <%
                        for (int i = 0; i < stockNews.size(); i++) {
                            String title = stockNews.get(i).getTitle();
                            String content = stockNews.get(i).getContent();
                    %>
                    <h2>
                        <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion-102144"
                           href="#accordion-element-<%=i%>"><%=title%>
                        </a>
                    </h2>
                    <div id="accordion-element-<%=i%>" class="accordion-body collapse">
                        <div class="accordion-inner">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=content%>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class=bs-docs-footer role=contentinfo>
    <div class=container>
        <ul class=bs-docs-footer-links>
            <li><a href="http://114.55.35.12/141250111_cseiii_AnyQuant/AnyQuant.git">GitHub</a></li>
            <li><a href="#">Twitter</a></li>
            <li><a href="#">Examples</a></li>
            <li><a href="#">About</a></li>
        </ul>
        <p>
            &copy; Ultraviolet 团队开发 2016
        </p>
    </div>
</footer>
</body>
<script src="page/js/jquery.min.js"></script>
<script src="page/js/bootstrap.min.js"></script>
<script>
    $('#myCarousel').carousel({
        interval: 3000,
    });
</script>
</html>
