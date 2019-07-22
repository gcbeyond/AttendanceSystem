$(
    //自动获取列表
    function () {
        initAdminPopedomList('/controller/admin/findAllAdmins');
    }
);

// 加载部门下拉框
$("#deptSelect").combotree({
    url:'/controller/department/departmentTree',
    height:26,
    width:'167',
    onBeforeSelect:function(node) {
        var tree = $(this).tree;
        var isLeaf = tree("isLeaf", node.target);
        if (!isLeaf) {
            $("#deptSelect").treegrid(unselect)
        }
    }
})


//权限列表加载
function initAdminPopedomList(isUrl) {
    // 加载表格
    $("#table").datagrid({
        title: "权限列表",
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
        sortName: 'adminAccount',
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
                field:'adminID',
                title:'账号',
                width:100,
                align: 'center',
                hidden:true
            },
            {
                field:'adminAccount',
                title:'账号',
                width:100,
                align: 'center'
            },
            {
                field:'adminName',
                title:'用户名',
                width:100,
                align:'center'
            },
            {
                field:'adminPwd',
                title:'密码',
                width:100,
                align:'center'

            }, {
                field:'firstDName',
                title:'一级部门（编号）',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    if (row.firstDName != undefined) {
                        return '<div>' + row.firstDName + '(' + row.firstDID + ')' + '</div>';
                    }
                }
            },
            {
                field:'secondDName',
                title:'二级部门（编号）',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    if (row.secondDName != undefined) {
                        return '<div>'+ row.secondDName + '(' + row.secondDID + ')' +'</div>';
                    }
                }
            },
            {
                field:'adminRight',
                title:'角色',
                width:100,
                align:'center',
                formatter:function (val) {
                    if(val == '1'){
                        return '<div style="color: red">'+'管理员'+'</div>';
                    }
                    else{
                        return '<div style="color: green">'+'考勤员'+'</div>';
                    }
                }
            }

        ]]
    })
}

//编辑权限列表加载
function initEditAdminPopedomList(isUrl) {
    // 加载表格
    $("#tableTow").datagrid({
        title: "权限列表",
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
        columns:[[
            {
                checkbox:true,
                field:'no',
                width:100,
                align:'center'
            },
            {
                field:'popedomID',
                title:'编号',
                width:100,
                align:'center'
            },
            {
                field:'adminAccount',
                title:'账号',
                width:100,
                align:'center'
            },
            {
                field:'adminName',
                title:'用户名',
                width:100,
                align:'center'
            }, {
                field:'firstDName',
                title:'一级部门（编号）',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    if (row.firstDName != undefined) {
                        return '<div>' + row.firstDName + '(' + row.firstDID + ')' + '</div>';
                    }
                }
            },
            {
                field:'secondDName',
                title:'二级部门（编号）',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    if (row.secondDName != undefined) {
                        return '<div>'+ row.secondDName + '(' + row.secondDID + ')' +'</div>';
                    }
                }
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val,row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="delOne(\'' + row.popedomID + '\')">删除</a> ';
                    return e;
                }

            }
        ]]
    })
}

//编辑权限
function editSelect() {

    $("#addBox").dialog({
        title: "权限编辑",
        width: 650,
        height: 450,
        closed: false,
        modal: true,
        shadow: true
    });

    var rows =$("#table").datagrid("getSelected");
    $('#addForm').form('load',{
        adminID: rows.adminID,
        adminName: rows.adminName
    });
    initEditAdminPopedomList('/controller/admin/findOneAdmin?adminID=' + rows.adminID);
}

//删除权限
function delOne(popedomID) {
    $.messager.confirm('提示信息', '是否删除所选择员工',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/adminPopedom/removeAdminPopedom',
                    data: {
                        popedomID: popedomID
                    },
                    beforeSend: function () {
                        $("#tableTow").datagrid('loading');

                    },
                    success: function (data) {

                        if (data == "true") {
                            $("#tableTow").datagrid("loaded");
                            $("#tableTow").datagrid("load");
                            $("#tableTow").datagrid("unselectRow");
                            $.messager.show({
                                title: '提示信息',
                                msg: "权限删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "权限删除失败"
                            })
                        }
                    }
                })
            }
        }
    );

}

//查询
function findSomeAdmins() {
    var adminName = $("#adminName").val();

    initAdminPopedomList('/controller/admin/findSomeAdmins?' +
        'adminName=' + adminName
    );
}

//提交权限分配
function submitAdd() {
    $("#addForm").form('submit',{
        url:"/controller/adminPopedom/addAdminPopedom",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#tableTow").datagrid('reload');
                $("#table").datagrid('reload');
                $.messager.show({
                    title: '提示',
                    msg: '权限分配成功'
                });
            } else {
                $.messager.show({
                    title: '提示',
                    msg: '权限分配失败'
                });
            }
        }
    })

}