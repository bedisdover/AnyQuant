<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 实例 - 折叠面板</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="style/daterangepicker.css"/>
    <script src="js/moment.min.js"></script>
    <script src="js/jquery.daterangepicker.js"></script>
</head>
<body>
<div>
    <%--<span id="dom-id-1">--%>
    <%--<button type="button" class="btn btn-primary">date</button>--%>
    <%--</span>--%>
    <label>
        <input id="dom-id-1" type="text">
        <input id="dom-id-2" type="text">
    </label>
</div>
</body>
<script>
    $('#dom-id-1, #dom-id-2').dateRangePicker({
        format: 'YYYY-MM-DD',
        separator: ' to ',
        language: 'cn',
        startDate: '2016-01-01',
        endDate: '2016-05-17',
        autoClose: true,
        showShortcuts: true,

        getValue: function () {
            if ($('#dom-id-1').val() && $('#dom-id-2').val())
                return $('#dom-id-1').val() + ' to ' + $('#dom-id-2').val();
            else
                return '';
        },
        setValue: function (s, s1, s2) {
            $('#dom-id-1').val(s1);
            $('#dom-id-2').val(s2);
        }
    });

    $('#dom-id-2').dateRangePicker().bind('datepicker-close', function () {
        console.log($('#dom-id-1').val());
        console.log($('#dom-id-2').val());
    });

</script>
</html>