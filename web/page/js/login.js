$(function(){  //页面加载的操作处理
    //需要做一个针对mid或者password输入的字段丢失焦点的事件处理
    $("#mid").on("blur",function () {
        validateMid()
    });
    $("#password").on("blur",function () {
        validatePassword()
    });
    //对于整个表单需要进行提交前的检查操作
    $("#loginForm").on("submit",function () {
        return validateMid()&&validatePassword();  //验证都通过后可以提交
    });
});

function validateMid(){
    return validateEmpty("mid");
}

function validatePassword(){
    return validateEmpty("password");
}

/**
 * 判断输入元素是否为空，如果为空用过has-error提示错误
 * 在xxSpan中显示为空的错误信息
 * @param eleId
 */
function validateEmpty(eleId){
    if($("#" + eleId).val() == ""){  //为空
        $("#" +eleId + "Div").attr("class","form-group has-error");
        $("#" +eleId + "Span").html("<span class = 'text-danger'>该字段的内容不允许为空!</span>");
        return false;
    }else{  //不为空
        $("#" +eleId + "Div").attr("class","form-group has-success");
        $("#" +eleId + "Span").html("<span class = 'text-success'>该字段的内容输入合法!</span>");
        return true;
    }
}