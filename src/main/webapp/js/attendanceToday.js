$(
    //自动获取列表
    function () {

        initAttendanceToday('/controller/attendanceToday/findAllAttendanceRecords', formatDate(new Date()));
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
                field:'tempDepartmentID',
                title:'员工部门',
                width:100,
                align:'center',
                hidden: true
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
                        return '<div>'+ formatDate(new Date(row.attendanceDate)) +'</div>';
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
                formatter : function(val, row, index) {
                    var arr = new Array(13);
                    for (var i = 0; i < 13; i++) {
                        if ((i + 1) == row.attendanceType) {
                            arr[i] = "<option value=\"" + (i + 1) + "\" selected>";
                        } else {
                            arr[i] = "<option value=\"" + (i + 1) + "\" >";
                        }
                    }
                    return "<select onchange='select_onchange(this," + index + ")'> " +
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
                width:50,
                align:'left',
                formatter:function (val, row, index) {
                    var isUrl = '/controller/note/findNoteToAttendance?employeeID=' + row.employeeID + '&attendanceDate=' + toDay;
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="searchSomeNotes(\'' + isUrl + '\',\'' + index + '\')">查询</a> ';
                    return e;
                }
            },
            {
                field : 'noteID',
                title : '请假单',
                width : 100,
                align : 'center'
            },
            {
                field : 'attendanceID',
                title : 'id',
                width : 100,
                align : 'center',
            }
        ]]
    })
}

//查询出勤
function findSome() {
    var deptSelect = $("#deptSelect").val();
    var attendanceTime = $("#attendanceTime").val();
    var attendanceDate = $("#attendanceDate").val();
    var isUrl = '/controller/attendanceToday/findSomeAttendanceRecords?' +
        'deptSelect=' + deptSelect +
        '&attendanceDate=' + attendanceDate +
        '&attendanceTime=' + attendanceTime;

    initAttendanceToday(isUrl, formatDate(new Date(attendanceDate)));
}

//请假单列表加载
function searchSomeNotes(isUrl, index) {
    // 加载表格
    $("#noteTable").datagrid({
        title: "请假单列表",
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
        sortName: 'noteID',
        checkOnSelect: false,
        sortOrder: 'asc',
        columns: [[
            {
                checkbox: true,
                field: 'no',
                width: 100,
                align: 'center'
            },
            {
                field: 'noteID',
                title: '编号',
                width: 100,
                align: 'center'
            },
            {
                field: 'cardNumber',
                title: '卡号',
                width: 100,
                align: 'center'
            },
            {
                field: 'employeeName',
                title: '申请人',
                width: 100,
                align: 'center'
            },
            {
                field: 'fillInTime',
                title: '填写时间',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    return formatDate(new Date(row.fillInTime));
                }
            },
            {
                field: 'typeName',
                title: '请假类型',
                width: 100,
                align: 'center'
            },
            {
                field: "opr",
                title: '操作',
                width: 180,
                align: 'center',
                formatter: function (val, row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="addNote(\'' + row.noteID + '\', \'' + index + '\')">添加</a> ';
                    return e;
                }
            }
        ]]
    });

    $("#selectNoteBox").dialog({
        title: "请假单选择",
        width: 650,
        height: 300,
        closed: false,
        modal: true,
        shadow: true
    })

}

//时间格式化
function formatDate(val) {
    return val.getFullYear() + "-" +
        (val.getMonth() + 1) + "-" +
        val.getDate();
}

//将查询到的noteID添加回表格中
function addNote(noteID, index) {
    $("#selectNoteBox").dialog({
        closed: true
    });

    $('#table').datagrid('updateRow',{
        index: parseInt(index),
        row: {
            noteID:noteID
        }
    });
}

//记录每行type的值
function select_onchange(obj, index) {
    var rows = $("#table").datagrid("getRows");
    rows[index].attendanceType = obj.value;
}

//提交保存
function saveAttendanceToday() {
    var rows = $("#table").datagrid("getRows");

    for( var i = 0 ; i < rows.length ; i++ ) {
        var params = "";
        var row = rows[i];
        var attendanceID = row.attendanceID;
        var cardNumber = row.cardNumber;
        var noteID = row.noteID;
        var employeeID = row.employeeID;
        var attendanceType = row.attendanceType;
        var attendanceDate = formatDate(new Date(row.attendanceDate));

        if (attendanceDate == 'NaN-NaN-NaN') {
            attendanceDate = formatDate(new Date());
        }
        var tempDepartmentID = row.tempDepartmentID;

        params += "attendanceID=" + attendanceID + "&";
        params += "employeeID=" + employeeID + "&";
        params += "cardNumber=" + cardNumber + "&";
        params += "attendanceType=" + attendanceType + "&";
        params += "noteID=" + noteID + "&";
        params += "attendanceDate=" + attendanceDate + "&";
        params += "attendanceTime=" + $("#attendanceTime").combobox("getValue") + "&";
        params += "tempDepartmentID=" + tempDepartmentID ;

        $.ajax(
            {
                type : "POST",
                url : "/controller/attendanceToday/addAttendanceRecord",
                data : params,
                success:function( data ) {
                    alertF(data);
                }
            }
        );

    }
}

var ai = 0;
function alertF(data) {
    ai = ai+1;
    if (ai < 2) {
        $.messager.alert('信息提示', data=="true" ? "存储成功！" : "存储失败！");
    }
}