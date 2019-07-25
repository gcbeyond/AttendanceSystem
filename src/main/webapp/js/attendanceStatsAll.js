$(
    //自动获取列表
    function () {

        initSelect();
    }
);

// 加载部门下拉框
$("#departmentID").combotree({
    url:'/controller/department/departmentTree',
    height:26,
    width:'16%',
    onSelect:function () {
        var t=$("#departmentID").combotree('tree');
        var n=t.tree('getSelected');
        var text=n.text;
        $("#departmentID").combotree('setValue',text);

    }
})

//加载时间select
function initSelect() {
    var year, month, day;
    year = [];
    month = [];
    day = [];

    for (var i = 2018; i <= 2030; i++) {
        year.push({ "text": i, "id": i });
    }
    for (var j = 1; j <= 12; j++) {
        month.push({ "text": j, "id": j });
    }
    for (var k = 1; k <= 31; k++) {
        day.push({ "text": k, "id": k });
    }

    $("#year").combobox("loadData", year);
    $("#month").combobox("loadData", month);
    $("#day").combobox("loadData", day);

    $("#year").combobox('setValue', "全部");
    $("#month").combobox('setValue', "全部");
    $("#day").combobox('setValue', "全部");

}

//出勤列表加载
function initAttendanceStatsAll(isURL) {
    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: isURL,
        fitColumns: true,
        striped: true,
        pagination: true,
        pageSize: 10,
        width: '100%',
        rownumbers: true,
        pageList: [10, 20],
        pageNumber: 1,
        nowrap: true,
        height: 'auto',
        sortName: 'cardNumber',
        checkOnSelect: false,
        sortOrder: 'asc',
        toolbar: '#tabelBut',
        columns:[[
            {
                checkbox:true,
                field:'no',
                width:100,
                align:'center'
            },
            {
                field:'cardNumber',
                title:'员工卡号',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'员工姓名',
                width:100,
                align:'center'
            },
            {
                field:'firstDName',
                title:'一级部门',
                width:100,
                align:'center'
            },
            {
                field:'secondDName',
                title:'二级部门',
                width:100,
                align:'center'
            },
            {
                field:'attendanceDate',
                title:'出勤日期',
                width:100,
                align:'center',
                formatter: function (val, row) {
                    return formatDate(new Date(row.attendanceDate))
                }
            },
            {
                field:'attendanceAM',
                title:'上午&nbsp;出勤状态',
                width:100,
                align:'center'
            },
            {
                field:'attendancePM',
                title:'下午&nbsp;出勤状态',
                width:100,
                align:'center'
            }
        ]]
    })
}

//提交检索
function findAttendanceStatsAll() {
    var searchRadio = $("input:radio:checked").val();

    var params = "";
    if (searchRadio == 1) {
        var year = $("#year").combobox('getValue');
        var month = $("#month").combobox('getValue');
        var day = $("#day").combobox('getValue');
        params =  "searchRadio=1" + "&year=" + year + "&month=" + month + "&day=" + day;
    } else {
        var date1 = $("#date1").val();
        var date2 = $("#date2").val();
        var checkType = "";
        $("input:checkbox").each(function () {
            if ($(this).prop("checked") == true) {
                checkType += $(this).val() + ",";
            }
        });
        var departmentID = $("#departmentID").val();
        var employeeName = $("#employeeName").val();

        params = "searchRadio=2" + "&date1=" + date1 + "&date2=" + date2 + "&checkType="
            + checkType + "&departmentID=" + departmentID + "&employeeName=" + employeeName;
    }
    initAttendanceStatsAll("/controller/attendanceStatsAll/findAttendanceStatsAll?"+params);
}


//时间格式化
function formatDate(val) {
    return val.getFullYear() + "-" +
        (val.getMonth() + 1) + "-" +
        val.getDate();
}
