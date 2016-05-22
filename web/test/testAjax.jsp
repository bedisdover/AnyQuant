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
    String string = "test_string";
%>

<div id="test">
    <%=string%>
</div>

<script>
    $('input[type=button]').click(function () {
        $.ajax({
            url: '/portfolio?data=' + Math.random(),
            type: 'POST',
            data: 'post',
            success: function (data) {
//                var temp = eval(data);
//                alert(typeof temp);
                <%=string%> = data;
                alert('<%=string%>' + '----' + data);
            }
        });
    });

    $(document).ajaxStop(function () {
        alert('<%=string%>');
    });

</script>
</body>
</html>
