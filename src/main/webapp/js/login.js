/**
 * Created by Administrator on 2017/11/8.
 */
$("#adminAccount").validatebox({
        required:true,
        missingMessage:"请输入用户名",
        invalidMessage:"用户名不能为空"
})
$("#adminPwd").validatebox({
        required:true,
        missingMessage:"请输入密码",
        invalidMessage:"密码不能为空"
})

//点击提交
function submitForm() {

    $("#ff").form('submit',{
        url:"/controller/login/loginUser",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "false") {
                $.messager.show({
                    title: '提示',
                    msg: '登录失败'
                });

            } else {
                var json = eval("("+data+")")
                setCookie(json.adminAccount, json.adminRight);

                window.location.href ="main.html";
                $("#table").datagrid('reload');
                $.messager.show({
                    title: '提示',
                    msg: '登录成功'
                });
            }
        }
    })

}

//清空修改
function clearForm(){
    $('#ff').form('clear');
}

function setCookie(adminName, adminRight){
    var d = new Date();
    d.setTime(d.getTime()+(24*60*60*1000));
    var expires = "expires="+d;
    document.cookie = "adminAccount=" + adminName  + "; " + expires;
    document.cookie = "adminRight=" + adminRight + "; " + expires;
}
