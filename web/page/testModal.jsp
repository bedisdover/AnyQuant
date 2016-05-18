<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Bootstrap 实例 - 模态框（Modal）插件</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<h2>创建模态框（Modal）</h2>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal"
        data-target="#myModal">
    开始演示模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    股票列表
                </h4>
            </div>
            <div class="modal-body">
                <%
                    for (int i = 1; i < 5; i++) {
                %>
                <span><input type="checkbox" value="<%=i%>"> <%=i%> </span>
                <%
                    }
                %>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="change()">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    var change = function () {
        var stocks = document.getElementsByClassName('modal-body')[0].getElementsByTagName('input');


        for (var i = 0; i < stocks.length; i++) {
            console.log($(stocks[i]).val() + '-----' + stocks[i].checked);
        }
    };

    $('#myModal').on('hide.bs.modal', function () {
    })
</script>
</body>
</html>