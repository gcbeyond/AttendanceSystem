$(
    function () {
    //生成默认时间
    document.getElementById('attendanceDate').valueAsDate = new Date();

});

function initAttendanceStatsToday() {
    var attendanceDate = $("#attendanceDate").val();
    var attendanceTime = $("#attendanceTime").val();
    var params = "attendanceDate=" + attendanceDate + "&attendanceTime=" + attendanceTime;
    $.ajax(
        {
            type:"POST",
            url:"/controller/attendanceStatsToday/findStatsToday",
            dataType:"json",
            data : params,
            success:function( data )
            {
                setHtml(data);
            }
        }
    );
}

//生成html
function setHtml(datas) {
    var data = datas.rows;
    var attendanceDate = $("#attendanceDate").val();
    var attendanceTime = $("#attendanceTime").val();
    var surface = "";
    var firstDName = "";
    var secondDName = "";

    surface += "<p id='p1' style='font-size: 24px' align='center'>" + attendanceDate + "(" + attendanceTime + ")出勤情况汇总</p>";
    for (var i = 0; i < data.length; i++) {
        if (data[i].firstDName != firstDName) {
            surface += "<hr/>";
            firstDName = data[i].firstDName;
            surface += "<p id='p1' style='font-size: 16px'>" + data[i].firstDName + "</p>";
        }
        if (data[i].secondDName != secondDName) {
            secondDName = data[i].secondDName;
            surface += "<p id='p1' style='font-size: 16px'>" + data[i].secondDName + "</p>";
        }
        surface += "<p id='p1' style='font-size: 12px'>" + data[i].employeeName + "(" + data[i].typeName + ")</p>";
    }
    $("#statsToday").append(surface);

}