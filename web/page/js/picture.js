// <%--固定侧边栏Start--%>
var jWindow = $(window);
var window_height = document.body.clientHeight;

jWindow.scroll(function () {
    var scrollHeight = jWindow.scrollTop();

    if (scrollHeight < window_height) {//157为footer高度
        $("#sidebar").css({
            position: "relative",
            top: scrollHeight + "px"
        });
    }
});
// <%--固定侧边栏End--%>

// <%--侧边栏导航Start--%>
function click_scroll(target) {
    var scroll_offset = $("#" + target).offset();  //得到pos这个div层的offset，包含两个值，top和left
    $('body, html').animate({
        scrollTop: scroll_offset.top - 100  //让body的scrollTop等于pos的top，就实现了滚动
    }, 0);
}
// <%--侧边栏导航End--%>

/*激活提示框Start*/
$('.badge').tooltip({
    placement: 'left'
});
$('#increase_collapse, #decrease_collapse, #volume_collapse').tooltip({
    placement: 'right',
});
/*激活提示框End*/


/*表格排序Start*/
    $(function () {
    for (var i = 0; i < 3; i++) {
    $('#basic-sort-' + i + ' table').stickySort({sortable: true});
}
});
/*表格排序End*/

