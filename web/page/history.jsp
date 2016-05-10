<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>AnyQuant--历史</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="/images/icon.png" rel="icon"/>
    <link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="style/historyStyle.css" rel="stylesheet" type="text/css"/>
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
                <a class="navbar-brand" href="../index.jsp">AnyQuant</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="../index.jsp">首页</a></li>
                    <li><a href="portfolio.jsp">自选</a></li>
                    <li><a href="market.jsp">大盘</a></li>
                    <li><a href="picture.jsp">行情</a></li>
                    <li class="active"><a href="javascript: void(0)">历史 <span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.jsp">登录</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>
</body>
</html>