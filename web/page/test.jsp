<%--suppress ALL --%>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 实例 - 默认的分页</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<ul class="pagination" id="pagination">
    <li class="disabled" onclick="changePage(0)"><a href="#test-1">&laquo;</a></li>
    <li onclick="changePage(1)" class="active"><a href="#test-1">1</a></li>
    <li onclick="changePage(2)"><a href="#test-2">2</a></li>
    <li onclick="changePage(3)"><a href="#test-3">3</a></li>
    <li onclick="changePage(4)"><a href="#test-4">4</a></li>
    <li onclick="changePage(5)"><a href="#test-5">5</a></li>
    <li onclick="changePage(-1)"><a href="#">&raquo;</a></li>
</ul>

<div id="pagination-content">
    <div id="test-1" style="display: block;">
        test-1
    </div>
    <div id="test-2" style="display: none;">
        test-2
    </div>
    <div id="test-3" style="display: none;">
        test-3
    </div>
    <div id="test-4" style="display: none;">
        test-4
    </div>
    <div id="test-5" style="display: none;">
        test-5
    </div>
</div>

<script>
    var pagination = document.getElementById('pagination');
    var pages = pagination.getElementsByTagName('li');

    var pageContents = document.getElementById('pagination-content').getElementsByTagName('div');

    var changePage = function (pageNum) {
        var selected = getSelected();
        if (pageNum == 0) {
            if (selected != 1) {
                pages[selected].className = '';
                pages[selected - 1].className = 'active';

                pageContents[selected - 1].style.display = 'none;';
                pageContents[selected - 2].style.display = 'block;';
            }
        } else if (pageNum == -1) {
            if (selected != pages.length - 2) {
                pages[selected].className = '';
                pages[selected + 1].className = 'active';

                pageContents[selected - 1].style.display = 'none;';
                pageContents[selected].style.display = 'block;';
            }
        } else {
            pages[selected].className = '';
            pages[pageNum].className = 'active';

            pageContents[selected - 1].style.display = 'none;';
            pageContents[pageNum - 1].style.display = 'block;';
        }

        if (getSelected() == 1) {
            pages[0].className = 'disabled';
        } else {
            pages[0].className = '';
        }
        if (getSelected() == pages.length - 2) {
            pages[pages.length - 1].className = 'disabled';
        } else {
            pages[pages.length - 1].className = '';
        }
    };

    var getSelected = function () {
        for (var i = 1; i < pages.length - 1; i++) {
            if (pages[i].className == 'active') {
                return i;
            }
        }
    }
</script>
</body>
</html>