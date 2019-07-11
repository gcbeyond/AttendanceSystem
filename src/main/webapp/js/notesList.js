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

//请假单列表加载
function initAttendanceStatsAll() {
    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: 'json/notesList.json',
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
                field:'fillInTime',
                title:'填写时间',
                width:100,
                align:'center'
            },
            {
                field:'cardNumber',
                title:'卡号',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'申请人',
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
                field:'noteTypeName',
                title:'请假类型',
                width:100,
                align:'center'
            },
            {
                field:'startDate',
                title:'起始日期',
                width:100,
                align:'center'
            },
            {
                field:'endDate',
                title:'结束日期',
                width:100,
                align:'center'
            },
            {
                field:'operatorName',
                title:'代理人',
                width:100,
                align:'center'
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val,row) {
                    var rowEdit = [row.cardNumber, row.employeeName, row.operatorID, row.operatorName,
                        row.noteTypeID, row.noteTypeName, row.fillInTime, row.cause, row.startDate,
                        row.endDate, row.directorSign, row.administrationSign, row.presidentSign];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delOne(\'' + row.employeeID + '\')">删除</a> ';
                    return e+d;
                }
            }
        ]]
    })
}

//添加看请假单
function addOne() {
    $('#addForm').form('clear');
    $("#addBox").dialog({
        title: "请假单添加",
        width: 650,
        height: 300,
        closed: false,
        modal: true,
        shadow: true
    })
}

//编辑请假单
function editOne(rowEdit) {

    $("#addBox").dialog({
        title:"请假单编辑",
        width: 650,
        height: 300,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#addForm').form('load',{
        employeeName: rows[1]+"("+ rows[0] +")",
        operatorName: rows[3]+"("+ rows[2] +")",
        noteTypeID: rows[4],
        fillInTime: rows[6],
        cause: rows[7],
        startDate: rows[8],
        endDate: rows[9],
        directorSign: rows[10],
        administrationSign: rows[11],
        presidentSign: rows[12]
    });
//弄完记笔记·
    if (rows[2].val() == null) {
        alert(rows[2]);
        $("input[name='operatorName']").val("");
    }
    $("input[name='employeeName']").attr("readonly", "readonly");
}

//删除请假单
function delOne(index) {

    $.messager.confirm("提示", "是否要删除该员工",
        function (flag) {
            if (flag == false) {
                return;
            }
        })
    $("#table").datagrid("deleteRow", index);
    $.messager.show({
        title: "提示",
        msg: "删除员工成功"
    });

}
