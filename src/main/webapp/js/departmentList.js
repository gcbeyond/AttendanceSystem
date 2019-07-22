$(
    //自动一级部门
    function () {
        initOneDepartmentList();
    }
);

// 加载部门下拉框
function deptSelect() {
    $("#deptSelect").combotree({
        url:'/controller/department/departmentFirstTree',
        height:26,
        width:'167',
    })

}

//一级部门列表加载
function initOneDepartmentList() {
    // 加载表格
    $("#tableFirst").datagrid({
        title: "一级部门列表",
        iconCls: "icon-left02",
        url: '/controller/department/findAllFirstDepartment',
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
        sortName: 'departmentID',
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
                field: 'departmentID',
                title: '一级部门编号',
                width: 100,
                align: 'center'
            },
            {
                field: 'departmentName',
                title: '一级部门名称',
                width: 100,
                align: 'center'
            },
            {
                field: 'startTimeAM',
                title: '上午上班时间',
                width: 100,
                align: 'center'
            },
            {
                field: 'endTimeAM',
                title: '上午下班时间',
                width: 100,
                align: 'center'
            },
            {
                field: 'startTimePM',
                title: '下午上班时间',
                width: 100,
                align: 'center'
            },
            {
                field: 'endTimePM',
                title: '下午下班时间',
                width: 100,
                align: 'center'
            },
            {
                field: "opr",
                title: '操作',
                width: 120,
                align: 'center',
                formatter: function (val, row, index) {
                    var rowEdit = [row.departmentID, row.departmentName, row.startTimeAM, row.endTimeAM, row.startTimePM, row.endTimePM];
                    a = '<a  id="add" data-id="98" class=" operA"   onclick="manageChild(\'' + row.departmentID + '\')">管理子部门</a> ';
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editFirstOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delFirstOne(\'' + row.departmentID + '\')">删除</a> ';
                    return a + e + d;
                }

            }
        ]]
    })
}

//二级部门列表加载
function initTowDepartmentList(parentID) {
    deptSelect();
    $("#tableSecond").datagrid({
        title:"二级部门列表",
        iconCls:"icon-left02",
        url:'/controller/department/findAllSecondFromOneDepartment?parentID=' + parentID,
        fitColumns:true,
        striped:true,
        pagination:true,
        pageSize:10,
        width:'100%',
        rownumbers:true,
        pageList:[10,20],
        pageNumber:1,
        nowrap:true,
        height:'auto',
        sortName:'departmentID',
        checkOnSelect:false,
        sortOrder:'asc',
        // toolbar: '#tabelBut',列表的加号
        columns:[[
            {
                checkbox:true,
                field:'no',
                width:100,
                align:'center'
            },
            {
                field:'departmentID',
                title:'二级部门编号',
                width:100,
                align:'center'
            },
            {
                field:'departmentName',
                title:'二级部门名称',
                width:100,
                align:'center'
            },
            {
                field:'startTimeAM',
                title:'上午上班时间',
                width:100,
                align:'center'
            },
            {
                field:'endTimeAM',
                title:'上午下班时间',
                width:100,
                align:'center'
            },
            {
                field:'startTimePM',
                title:'下午上班时间',
                width:100,
                align:'center'
            },
            {
                field:'endTimePM',
                title:'下午下班时间',
                width:100,
                align:'center'
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    var rowEdit = [row.departmentID, row.departmentName, row.startTimeAM, row.endTimeAM, row.startTimePM, row.endTimePM];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editSecondOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delSecondOne(\'' + row.departmentID + '\')">删除</a> ';
                    return e+d;
                }
            }
        ]]
    });
}

//点击管理二级子部门，打开子部门界面
function manageChild(departmentID) {

    $("#manageChild").dialog({
        title:"二级部门维护",
        width: 800,
        height: 400,
        closed: false,
        modal:true,
        shadow:true
    })

    initTowDepartmentList(departmentID);
}

//编辑一级部门
function editFirstOne(rowEdit) {

    $("#editFirstBox").dialog({
        title:"一级部门编辑",
        width: 650,
        height: 350,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#editFirstForm').form('load',{
        departmentID: rows[0],
        departmentName: rows[1],
        startTimeAM: rows[2],
        endTimeAM: rows[3],
        startTimePM: rows[4],
        endTimePM: rows[5]
    });

}

//编辑二级部门
function editSecondOne(rowEdit) {

    $("#editSecondBox").dialog({
        title:"二级部门编辑",
        width: 650,
        height: 350,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#editSecondForm').form('load',{
        departmentID: rows[0],
        departmentName: rows[1],
        startTimeAM: rows[2],
        endTimeAM: rows[3],
        startTimePM: rows[4],
        endTimePM: rows[5]
    });

}

//删除部门
function delFirstOne(departmentID) {
    $.messager.confirm('提示信息', '是否删除所选择部门',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/department/removeOneDepartment',
                    data: {
                        departmentID: departmentID
                    },
                    beforeSend: function () {
                        $("#tableFirst").datagrid('loading');

                    },
                    success: function (data) {

                        if (data == "true") {
                            $("#tableFirst").datagrid("loaded");
                            $("#tableFirst").datagrid("load");
                            $("#tableFirst").datagrid("unselectRow");
                            $.messager.show({
                                title: '提示信息',
                                msg: "部门删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "部门删除失败"
                            })
                        }
                    }
                })
            }
        }
    );
}

//删除部门
function delSecondOne(departmentID) {
    $.messager.confirm('提示信息', '是否删除所选择部门',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/department/removeOneDepartment',
                    data: {
                        departmentID: departmentID
                    },
                    beforeSend: function () {
                        $("#tableSecond").datagrid('loading');

                    },
                    success: function (data) {

                        if (data == "true") {
                            $("#tableSecond").datagrid("loaded");
                            $("#tableSecond").datagrid("load");
                            $("#tableSecond").datagrid("unselectRow");
                            $.messager.show({
                                title: '提示信息',
                                msg: "部门删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "部门删除失败"
                            })
                        }
                    }
                })
            }
        }
    );
}

//提交添加一级部门
function submitFirstAdd() {
    $("#addFirstForm").form('submit',{
        url:"/controller/department/addOneDepartment",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#tableFirst").datagrid('reload');
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

//提交添加二级部门
function submitSecondAdd() {
    $("#addSecondForm").form('submit',{
        url:"/controller/department/addOneDepartment",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#tableSecond").datagrid('reload');
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

//提交修改一级部门
function submitFirstEdit() {
    $("#editFirstForm").form('submit',{
        url:"/controller/department/editOneDepartment",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#editFirstBox").dialog({
                    closed: true
                });
                $("#tableFirst").datagrid('reload');
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

//提交修改二级部门
function submitSecondEdit() {
    $("#editSecondForm").form('submit',{
        url:"/controller/department/editOneDepartment",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#editSecondBox").dialog({
                    closed: true
                });
                $("#tableSecond").datagrid('reload');
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
