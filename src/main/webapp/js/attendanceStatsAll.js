$(
    //自动获取列表
    function () {
        initAttendanceStatsAll();
        initSelect();
    }
);

// 加载部门下拉框
$("#deptSelect").combotree({
    url:'json/deptSelect.json',
    height:26,
    width:'16%',
    onSelect:function () {
        var t=$("#deptSelect").combotree('tree');
        var n=t.tree('getSelected');
        var text=n.text;
        $("#deptSelect").combotree('setValue',text);

    }
})

//加载时间select
function initSelect() {
    var year, month, day;
    year = [];
    month = [];
    day = [];

    for (var i = 2000; i <= 2030; i++) {
        year.push({ "text": i, "id": i });
    }
    for (var j = 1; j <= 12; j++) {
        month.push({ "text": j, "id": j });
    }
    for (var k = 1; k <= 31; k++) {
        day.push({ "text": k, "id": k });
    }

    $("#selectY").combobox("loadData", year);
    $("#selectM").combobox("loadData", month);
    $("#selectD").combobox("loadData", day);

    $("#selectY").combobox('setValue', "全部");
    $("#selectM").combobox('setValue', "全部");
    $("#selectD").combobox('setValue', "全部");

}

//出勤列表加载
function initAttendanceStatsAll() {
    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: 'json/attendanceStatsAll.json',
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
        sortName: 'attendanceID',
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
                field:'departmentOne',
                title:'一级部门',
                width:100,
                align:'center'
            },
            {
                field:'departmentTow',
                title:'二级部门',
                width:100,
                align:'center'
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

