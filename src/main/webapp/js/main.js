/**
 * Created by Administrator on 2017/11/8.
 */

$(function () {
        getCookie();

});
$("#mainBox").layout({
        fit:true,
        border:false
})
$("#mean").menu('show',{
        left: 200,
        top: 100
})
$("#left01").accordion({
        border:false

})
$("#con").tabs({
        fit:true,
        border:false
})
$("#myMes").dialog({
        title:"个人信息详情",
        width:400,
        height:420,
        modal:true,
        iconCls:'icon-mes',
        maximizable:true,
        closed: true

})
function openMes() {
        $("#myMes").dialog({
                closed: false

        })

}
function saveExit() {
        $.messager.confirm('退出确认','你是否退出系统？',function () {
                document.cookie = "adminAccount=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                document.cookie = "adminRight=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                window.location.href ="login.html";
        })

}

$(".topText a").click(function () {
        $(this).addClass('textActive').siblings().removeClass('textActive');

})
$("#left01  a").click(function () {
        var testVal=$(this).text();
        var thisUrl=$(this).attr('href');
        var con = '<iframe scrolling="no" frameborder="0"  src="'+thisUrl+'" style="width:100%;height:100%;">';
        $('#con').tabs('add',{
                title: testVal,
                selected: true,
                closable:true,
               content:con


        });

})
$("#con").tabs({
        onSelect:function (tit,ind) {
                if(ind==0){
                        $("#ifDiv").attr('src',"home.html");
                }

        }
})


function getCookie(){

        if (document.cookie == null || document.cookie == "") {
                window.location.href ="login.html";
        }

        var name = "adminAccount=";
        var right = "adminRight=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++)
        {
                var c = ca[i].trim();
                if (c.indexOf(name) == 0) {
                        var n =  c.substring(name.length,c.length);
                        $("#adminName").html(n);
                }
                if (c.indexOf(right) == 0) {
                        var r =  c.substring(right.length,c.length);
                        if (r == 1) {
                                $("#attendance").panel('close');
                        } else {
                                $("#infoList").panel('close');
                        }
                }
        }
}