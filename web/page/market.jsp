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
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <!--todo icon-->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home.jsp">AnyQuant</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="home.jsp">首页</a></li>
                    <li><a href="portfolio.jsp">自选</a></li>
                    <li class="active"><a href="market.jsp">大盘 <span class="sr-only">(current)</span></a></li>
                    <li><a href="picture.jsp">行情</a></li>
                    <li><a href="history.jsp">历史</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.jsp">登录</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>
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
        <div class="row latest-data">
            <!--最新数据-->
            <div class="col-md-2 name-code">
                <P class="text-center name">沪深300</P>
                <p class="text-center code">（1B0300）</p>
            </div>
            <div class="col-md-4 inc-dec">test</div>
            <div class="col-md-6 data">test</div>
        </div>
        <div class="chart">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#">分时图</a></li>
                <li role="presentation"><a href="#">K线图</a></li>
                <li role="presentation"><a href="#">折线图</a></li>
            </ul>
        </div>
    </div>
    <div class="col-md-1">

    </div>
</div>
</body>
</html>