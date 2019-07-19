$(
    //自动获取列表
    function () {
        initEmployeeList('/controller/employee/findAllEmployees');
    }
);

// 加载部门下拉框
$("#deptSelect").combotree({
    url:'/controller/department/departmentTree',
    height:26,
    width:'16%',
    onBeforeSelect:function(node) {
        var tree = $(this).tree;
        var isLeaf = tree("isLeaf", node.target);
        if (!isLeaf) {
            $("#deptSelect").treegrid(unselect)
        }
    }
})

// 加载编辑框部门下拉框修改
$("#deptSelect2, #deptSelect3").combotree({
    url:'/controller/department/departmentTree',
    height:26,
    width:197,
    onBeforeSelect:function(node) {
        var tree = $(this).tree;
        var isLeaf = tree("isLeaf", node.target);
        if (!isLeaf) {
            $("#deptSelect").treegrid(unselect)
        }
    }
})

// 加载编辑框部门下拉框添加
$("#deptSelect3").combotree({
    url:'/controller/department/departmentTree',
    height:26,
    width:197,
    onBeforeSelect:function(node) {
        var tree = $(this).tree;
        var isLeaf = tree("isLeaf", node.target);
        if (!isLeaf) {
            $("#deptSelect").treegrid(unselect)
        }
    }
})

// 加载弹出职位下拉框修改
$("#positionSelect").combotree({
    url:'/controller/position/positionTree',
    height:26,
    width:'197',
})

// 加载弹出职位下拉框添加
$("#positionSelect2").combotree({
    url:'/controller/position/positionTree',
    height:26,
    width:'197',
})

//员工列表加载
function initEmployeeList(isUrl) {
    // 加载表格
    $("#table").datagrid({
        title: "员工列表",
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
                    var rowEdit = [row.employeeID, row.employeeName, row.employeeGender, row.positionName,
                        row.departmentName, row.cardNumber, row.employeeState, row.employeeMemo];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delOne(\'' + row.employeeID + '\')">删除</a> ';
                    return e+d;

                }

            }
        ]]
    })
}

//添加员工
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

//编辑员工
function editOne(rowEdit) {

    $("#editBox").dialog({
        title:"员工编辑",
        width: 650,
        height: 300,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#editForm').form('load',{
        employeeID: rows[0],
        employeeName: rows[1],
        employeeGender: rows[2],
        positionName: rows[3],
        departmentName: rows[4],
        cardNumber: rows[5],
        employeeState: rows[6],
        employeeMemo: rows[7]
    });
    $("#editForm input[name='employeeID']").attr("readonly", "readonly");
}

//删除员工
function delOne(employeeID) {
    $.messager.confirm('提示信息', '是否删除所选择员工',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/employee/removeOneEmployee',
                    data: {
                        employeeID: employeeID
                    },
                    beforeSend: function () {
                        $("#table").datagrid('loading');

                    },
                    success: function (data) {

                        if (data == "true") {
                            $("#table").datagrid("loaded");
                            $("#table").datagrid("load");
                            $("#table").datagrid("unselectRow");
                            $.messager.show({
                                title: '提示信息',
                                msg: "员工删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "员工删除失败"
                            })
                        }
                    }
                })
            }
        }
    );
}

//提交添加
function submitAdd() {
    $("#addForm").form('submit',{
        url:"/controller/employee/addOneEmployee",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#addBox").dialog({
                    closed: true
                });
                $("#table").datagrid('reload');
                $.messager.show({
                    title: '提示',
                    msg: '信息保存成功'
                });
            } else {
                $.messager.show({
                    title: '提示',
                    msg: '信息保存失败'
                });
            }
        }
    })

}

//提交修改
function submitEdit() {
    $("#editForm").form('submit',{
        url:"/controller/employee/editOneEmployee",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#editBox").dialog({
                    closed: true
                });
                $("#table").datagrid('reload');
                $.messager.show({
                    title: '提示',
                    msg: '信息修改成功'
                });

            } else {
                $.messager.show({
                    title: '提示',
                    msg: '信息修改失败'
                });
            }
        }
    })
}

//清空添加
function clearAdd(){
    $('#addForm').form('clear');
}

//清空修改
function clearEdit(){
    $('#editForm').form('clear');
}

//查询
function findSomeEmployees() {
    var empSearch = $("#empSearch").val();
    var deptSelect = $("#deptSelect").val();

    initEmployeeList('/controller/employee/findSomeEmployees?' +
        'empSearch=' + empSearch +
        '&deptSelect=' + deptSelect
    );
}
