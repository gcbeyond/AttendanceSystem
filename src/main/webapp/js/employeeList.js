$(
    //自动获取列表
    function () {
        initEmployeeList();
    }
);

//员工列表加载
function initEmployeeList() {
    // 加载表格
    $("#table").datagrid({
        title: "员工列表",
        iconCls: "icon-left02",
        url: 'json/employeeList.json',
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
                title:'编号',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'姓名',
                width:100,
                align:'center'
            },
            {
                field:'employeeGender',
                title:'性别',
                width:100,
                align:'center',
                formatter:function (val) {
                    if(val=='1'){
                        return '<div style="color: green">'+'男'+'</div>';
                    }
                    else{
                        return '<div style="color: red">'+'女'+'</div>';
                    }
                }
            },
            {
                field:'positionName',
                title:'职位',
                width:100,
                align:'center'

            }, {
                field:'departmentName',
                title:'部门',
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
                field:'employeeState',
                title:'状态',
                width:100,
                align:'center',
                formatter:function (val) {
                    if(val=='1'){
                        return '<div>'+'正常'+'</div>';
                    }
                    else{
                        return '<div style="color: red">'+'锁定'+'</div>';
                    }
                }
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val,row) {
                    var rowEdit = [row.employeeID, row.employeeName, row.employeeGender, row.positionName, row.departmentName, row.cardNumber, row.employeeState, row.employeeMemo]
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delOne(\'' + row.employeeID + '\')">删除</a> ';
                    return e+d;

                }

            }
        ]]
    })
}

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

// 加载编辑框部门下拉框
$("#deptSelect2").combotree({
    url:'json/deptSelect.json',
    height:26,
    width:197,
    onSelect:function () {
        var t=$("#deptSelect2").combotree('tree');
        var n=t.tree('getSelected');
        var text=n.text;
        $("#deptSelect2").combotree('setValue',text);

    }
})

//添加员工
function addOne() {
    $('#addForm').form('clear');
    $("#addBox").dialog({
        title: "员工编辑",
        width: 650,
        height: 300,
        closed: false,
        modal: true,
        shadow: true
    })
}

//编辑员工
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
    $('#addForm').form('load',{
        employeeID: rows[0],
        employeeName: rows[1],
        employeeGender: rows[2],
        positionName: rows[3],
        departmentName: rows[4],
        cardNumber: rows[5],
        employeeState: rows[6],
        employeeMemo: rows[7]
    });
    $("input[name='employeeID']").attr("readonly", "readonly");
}

//删除员工
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
