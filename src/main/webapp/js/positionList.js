$(
    //自动加载职位列表
    function () {
        initPositionList();
    }
);
//职位列表加载
function initPositionList() {
    // 加载表格
    $("#table").datagrid({
        title: "职位列表",
        iconCls: "icon-left02",
        url: '/controller/position/findAllPositions',
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
        sortName: 'positionID',
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
                field:'positionID',
                title:'编号',
                width:100,
                align:'center'
            },
            {
                field:'positionName',
                title:'职位名称',
                width:100,
                align:'center'
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val, row) {
                    var rowEdit = [row.positionID, row.positionName];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="removeOne(\'' + row.positionID + '\')">删除</a> ';
                    return e+d;
                }

            }
        ]]
    })
}

//添加职位
function addOne() {
    $("#addBox").dialog({
        title:"职位编辑",
        width: 500,
        height: 200,
        closed: false,
        modal:true,
        shadow:true
    })
}

//编辑职位
function editOne(rowEdit) {
    $("#editBox").dialog({
        title:"职位编辑",
        width: 500,
        height: 200,
        closed: false,
        modal:true,
        shadow:true
    })
    var rows = rowEdit.split(",");
    $('#editForm').form('load',{
        positionID: rows[0],
        positionName: rows[1]
    });
    $("input[name='positionID']").attr("readonly", "readonly");
}

//删除职位
function removeOne(positionID) {
    $.messager.confirm('提示信息', '是否删除所选择职位',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/position/removeOnePosition',
                    data: {
                        positionID: positionID
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
                                msg: "职位删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "职位删除失败"
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
        url:"/controller/position/addOnePosition",
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
        url:"/controller/position/editOnePosition",
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