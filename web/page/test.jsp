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
    <span id="dom-id-1">
        <button type="button" class="btn btn-primary">date</button>
    </span>
    <%--<label for="startDate">--%>
    <%--<input id="startDate" type="text">--%>
    <%--<input id="endDate" type="text">--%>
    <%--</label>--%>
</div>
</body>
<script>
    $('#dom-id-1').dateRangePicker({
        format: 'YYYY-MM-DD',
        separator: ' to ',
        language: 'cn',
        startDate: '2016-01-01',
        endDate: '2016-05-17',
        autoClose: true,

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

//    $('#dom-id-1').dateRangePicker().bind('datepicker-change', function (event, obj) {
//                console.log(obj);
//                // obj will be something like this:
//                // {
//                //      date1: (Date object of the earlier date),
//                //      date2: (Date object of the later date),
//                //      value: "2013-06-05 to 2013-06-07"
//                // }
//            })
//            .bind('datepicker-apply', function (event, obj) {
//                console.log(obj);
//            })
//            .bind('datepicker-close', function () {
//                console.log('close');
//            });
</script>
</html>