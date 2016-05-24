<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-22
  Time: 下午2:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestAjax</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
    <script src="../page/js/jquery.js"></script>
</head>
<body>
<input type="button" value="test">

<%
    //    request.setAttribute("test", "test123");
    List<String> testList = new ArrayList<>();
    testList.add("str1");
    String string = (String) session.getAttribute("test");
%>

<div id="test">
    <%=string%>
</div>

<form id="form" method="post" action="/portfolio">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="checkbox" name="name" id="name"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="checkbox" name="password-text" id="password"></td>
        </tr>
    </table>
</form>

<script>
    $('input[type=button]').click(function () {

        var test = {
            'name': 'tom',
            'age': 30
        };

        $.ajax({
            url: '/portfolio?data=' + Math.random(),
            type: 'POST',
            data: 'test'+ $('#form').serialize(),
            success: function (data) {
                var temp = eval(data);
                alert(1);
//                $('#test').html(temp[0].name);
            }
        });
    });

    $(document).ajaxStop(function () {
        <%--alert('<%=string%>');--%>
    });

</script>
</body>
</html>
