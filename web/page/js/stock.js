// <%--固定侧边栏Start--%>
var jWindow = $(window);
var window_height = document.body.clientHeight;

var graphsHeight = $('#graphs').offset().top;
var historyHeight = $('#history-data').offset().top;
var pictureHeight= $('#picture-prediction').offset().top;

var menuList = $('#menu').children();//侧边栏菜单列表

jWindow.scroll(function () {
    var scrollHeight = jWindow.scrollTop();

    if (scrollHeight > historyHeight) {
        console.log(scrollHeight + '----' + pictureHeight);
    }

    if (scrollHeight < window_height - 157) {//157为footer高度
        $("#sidebar").css({
            position: "relative",
            top: scrollHeight + "px"
        });
    }
});
// <%--固定侧边栏End--%>

/*图表大小Start*/
var width = $('#main-content').innerWidth();
$('#graphs div').css({
    'width': width + 'px',
    'height': '400px'
});

$('#radar-chart').css({
    'width': width - 30 + 'px',//panel-body padding=15
    'height': '400px'
});
/*图表大小End*/
