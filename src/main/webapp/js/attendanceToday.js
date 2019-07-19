$(
    //自动获取列表
    function () {
        initAttendanceToday('/controller/attendanceRecord/findAllAttendanceRecords', formatDate(new Date()));
    }
);

// 加载部门下拉框
$("#deptSelect").combotree({
    url: '/controller/department/departmentTree',
    height: 26,
    width: '16%'
});

//出勤列表加载
function initAttendanceToday(isUrl, toDay) {

    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: isUrl,
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
                title:'考勤编号',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'员工名称',
                width:100,
                align:'center'
            },
            {
                field:'departmentName',
                title:'员工部门',
                width:100,
                align:'center'
            },
            {
                field:'attendanceDate',
                title:'出勤日期',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    if(row.attendanceDate != undefined){
                        return '<div>'+ row.attendanceDate +'</div>';
                    }else{
                        return '<div>'+ toDay +'</div>';
                    }
                }
            },
            {
                field:'attendanceType',
                title:'出勤状态',
                width:100,
                align:'center',
                formatter : function(val, row) {
                    var arr = new Array(13);
                    for (var i = 0; i < 13; i++) {
                        if ((i + 1) == row.attendanceType) {
                            arr[i] = "<option value=\"" + (i + 1) + "\" selected>";
                        } else {
                            arr[i] = "<option value=\"" + (i + 1) + "\" >";
                        }
                    }
                    return " <select id=\"attendanceType" + (i+1) +"\">\n" +
                                    arr[0] + "出勤</option>\n" +
                                    arr[1] + "公休</option>\n" +
                                    arr[2] + "迟到</option>\n" +
                                    arr[3] + "旷工</option>\n" +
                                    arr[4] + "外出</option>\n" +
                                    arr[5] + "出差</option>\n" +
                                    arr[6] + "加班</option>\n" +
                                    arr[7] + "倒休</option>\n" +
                                    arr[8] + "事假</option>\n" +
                                    arr[9] + "病假</option>\n" +
                                    arr[10] + "婚假</option>\n" +
                                    arr[11] + "丧假</option>\n" +
                                    arr[12] + "产假</option>\n" +
                            "</select>";
                }
            },
            {
                field:"opr",
                title:'&nbsp;&nbsp;关联表单',
                width:250,
                align:'left',
                formatter:function (val,row) {

                    e = '<a  id="add" data-id="98" class=" operA"  onclick="searchOne(\'' + row.cardNumber + '\')">查询</a> ';
                    return e;
                }

            }
        ]]
    })
}

//查询出勤
function findSome() {
    var deptSelect = $("#deptSelect").val();
    var attendanceTime = $("#attendanceTime").val();
    var attendanceDate = $("#attendanceDate").val();
    var isUrl = '/controller/attendanceRecord/findSomeAttendanceRecords?' +
        'deptSelect=' + deptSelect +
        '&attendanceDate=' + attendanceDate +
        '&attendanceTime=' + attendanceTime;

    initAttendanceToday(isUrl, attendanceDate);
}

//时间格式化
function formatDate(val) {
    return val.getFullYear() + "-" +
        (val.getMonth() + 1) + "-" +
        val.getDate();
}