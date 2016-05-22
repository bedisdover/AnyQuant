// <%--固定侧边栏Start--%>
var jWindow = $(window);
var window_height = document.body.clientHeight;

jWindow.scroll(function () {
    var scrollHeight = jWindow.scrollTop();

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

/*图表大小End*/
