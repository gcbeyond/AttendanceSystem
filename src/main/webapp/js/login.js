/**
 * Created by Administrator on 2017/11/8.
 */
$("#user").validatebox({
        required:true,
        missingMessage:"请输入用户名",
        invalidMessage:"用户名不能为空"
})
$("#pass").validatebox({
        required:true,
        missingMessage:"请输入密码",
        invalidMessage:"密码不能为空"
})
//加载时验证
if(!$("#user").validatebox('isValid')){
        $("#user").focus();

}
else if(!$("#pass").validatebox('isValid')){
        $("#pass").focus();
}
//点击提交
$("#btn").click(function () {
        if(!$("#user").validatebox('isValid')){
                $("#user").focus();

        }
        else if(!$("#pass").validatebox('isValid')){
                $("#pass").focus();
        }
        else{
                $.messager.alert("消息框","提交成功",'info');
        }


})
