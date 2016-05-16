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
    tsdjfaldf
    <label>
        <input id="dom-id-1"> to <input id="dom-id-2">
    </label>
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
</script>
</html>