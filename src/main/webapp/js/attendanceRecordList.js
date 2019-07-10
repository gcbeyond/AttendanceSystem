$(
    //自动获取列表
    function () {
        initEmployeeList();
    }
);
//出勤列表加载
function initEmployeeList() {
    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: 'json/attendanceRecordList.json',
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
        sortName: 'employeeID',
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
                field:'employeeID',
                title:'员工编号',
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
                field:'employeeName',
                title:'员工部门',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'出勤日期',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'出勤状态',
                width:100,
                align:'center',
                formatter:function (val) {
                    return
                    '<select>' +
                    '<option value="1"> '+ 出勤 + '</option>' +
                    '<option value="2"> '+ 请假 + '</option>'
                    +'</select>';
                }
            },
            {
                field:"opr",
                title:'关联表单',
                width:100,
                align:'center',
                formatter:function (val,row) {
                    // var rowEdit = [row.employeeID, row.employeeName, row.employeeGender, row.positionID, row.departmentID, row.cardNumber, row.employeeState, row.employeeMemo]
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + row.employeeID + '\')">查询</a> ';
                    return e;

                }

            }
        ]]
    })
}

//添加出勤
function addOne() {

    $("#addBox").dialog({
        title: "员工编辑",
        width: 650,
        height: 300,
        closed: false,
        modal: true,
        shadow: true
    })
}

//编辑出勤
function editOne(rowEdit) {
    $("#addBox").dialog({
        title:"员工编辑",
        width: 650,
        height: 300,
        closed: false,
        modal:true,
        shadow:true
    })
    var rows = rowEdit.split(",");
    alert(rows[1]);
    $('#addForm').form('load',{
        employeeID: rows[0],
        employeeName: rows[1],
        employeeGender: rows[2],
        positionID: rows[3],
        departmentID: rows[4],
        cardNumber: rows[5],
        employeeState: rows[6],
        employeeMemo: rows[7]
    });
    $("input[name='employeeID']").attr("readonly", "readonly");
}
