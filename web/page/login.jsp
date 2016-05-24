<%--
  Created by IntelliJ IDEA.
  User: lenovo2014
  Date: 2016/5/10
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AnyQuant--登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,inital-scale=1">
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
    <link href="../images/icon.png" rel="icon"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/loginStyle.css" rel="stylesheet"/>
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
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>

<div class="container">
    <form id="loginForm" action="check.jsp" method="post" class="form-horizontal">
        <fieldset>
            <legend><label><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</label></legend>
            <div class="form-group" id="midDiv">
                <label class="col-md-3 control-label" for="mid">Email Address</label>
                <div class="col-md-5">
                    <input type="text" id="mid" name="mid" class="form-control" placeholder="Email">
                </div>
                <div class="col-md-4" id="midSpan"></div>
            </div>
            <div class="form-group" id="passwordDiv">
                <label class="col-md-3 control-label" for="password">Password</label>
                <div class="col-md-5">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                </div>
                <div class="col-md-4" id="passwordSpan"></div>
            </div>
            <div class="form-group" id="butDiv">
                <div class="col-md-5 col-md-offset-3">
                    <button type="submit" id="subBut" class="btn btn-primary">Submit</button>
                    <button type="reset" id="rstBut" class="btn btn-warning">Reset</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
