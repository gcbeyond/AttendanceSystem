$(
    //自动获取列表
    function () {
        initSelectAllNotes('/controller/note/findAllNotes');
        initSelect();
    }
);

// 加载部门下拉框
$("#deptSelect").combotree({
    url:'/controller/department/departmentTree',
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
function initSelectAllNotes(isUrl) {
    alert(isUrl);
    // 加载表格
    $("#table").datagrid({
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
                field:'oneDName',
                title:'一级部门',
                width:100,
                align:'center'
            },
            {
                field:'twoDName',
                title:'二级部门',
                width:100,
                align:'center'
            },
            {
                field:'fillInTime',
                title:'填写时间',
                width:100,
                align:'center',
                formatter:function(val) {
                    return formatDate(val);
                }
            },
            {
                field:'typeName',
                title:'请假类型',
                width:100,
                align:'center'
            },
            {
                field:'startDate',
                title:'起始日期',
                width:100,
                align:'center',
                formatter:function(val) {
                    return formatDate(val);
                }
            },
            {
                field:'startTime',
                title:'起始时间',
                width:100,
                align:'center'
            },
            {
                field:'endDate',
                title:'结束日期',
                width:100,
                align:'center',
                formatter:function(val) {
                    return formatDate(val);
                }
            },
            {
                field:'endTime',
                title:'结束时间',
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
                width:180,
                align:'center',
                formatter:function (val,row) {
                    var fillInTime = formatDate(row.fillInTime);
                    var startDate = formatDate(row.startDate);
                    var endDate = formatDate(row.endDate);
                    var rowEdit = [row.cardNumber, row.employeeName, row.operatorCardNum, row.operatorName,
                        row.noteTypeID, fillInTime, row.cause, startDate, row.startTime,
                        endDate, row.endTime, row.directorSign, row.administrationSign, row.presidentSign];

                    a = '<a  id="add" data-id="98" class=" operA"  onclick="findOne(\'' + rowEdit + '\')">查看</a> ';
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="removeOne(\'' + row.employeeID + '\')">删除</a> ';
                    return a+e+d;
                }
            }
        ]]
    })
}

//添加请假单
function addOne() {

    $("#addBox").dialog({
        title: "请假单添加",
        width: 650,
        height: 450,
        closed: false,
        modal: true,
        shadow: true
    })
}

//查看请假单
function findOne(rowEdit) {

    $("#selectBox").dialog({
        title:"请假单查看",
        width: 650,
        height: 450,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#selectForm').form('load',{
        employeeName: rows[1]+"("+ rows[0] +")",
        operatorName: rows[3]+"("+ rows[2] +")",
        noteTypeID: rows[4],
        fillInTime: rows[5],
        cause: rows[6],
        startDate: rows[7],
        startTime: rows[8],
        endDate: rows[9],
        endTime: rows[10],
        directorSign: rows[11],
        administrationSign: rows[12],
        presidentSign: rows[13]
    });
    //将input值置空，待思考：不能直接比较null应该等于""
    if (rows[2] == "") {
        $("#selectForm input[name='operatorName']").val("");
    }
    $("#selectForm textarea").attr("disabled", "true");
    $("#selectForm input").attr("disabled", "true");
}

//编辑请假单
function editOne(rowEdit) {

    $("#editBox").dialog({
        title:"请假单编辑",
        width: 650,
        height: 450,
        closed: false,
        modal:true,
        shadow:true
    })
    var rows = rowEdit.split(",");
    $('#editForm').form('load',{
        employeeName: rows[1]+"("+ rows[0] +")",
        operatorName: rows[3]+"("+ rows[2] +")",
        noteTypeID: rows[4],
        fillInTime: rows[5],
        cause: rows[6],
        startDate: rows[7],
        startTime: rows[8],
        endDate: rows[9],
        endTime: rows[10],
        directorSign: rows[11],
        administrationSign: rows[12],
        presidentSign: rows[13]
    });
    //将input值置空，待思考：不能直接比较null应该等于""
    if (rows[2] == "") {
        $("#editForm input[name='operatorName']").val("");
    }

    $("#editForm input[name='employeeName']").attr("readonly", "readonly");
}

//删除请假单
function removeOne(noteID) {
    $.messager.confirm('提示信息', '是否删除所选择请假单',
        function (flg) {
            if (flg) {
                $.ajax({
                    type: 'post',
                    url: '/controller/note/removeOneNote',
                    data: {
                        noteID: noteID
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
                                msg: "信息删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "信息删除失败"
                            })
                        }
                    }
                })
            }
        }
    );

}

//时间格式化
function formatDate(val) {
    var date = new Date(val);
    return date.getFullYear() + "-" +
        (date.getMonth() + 1) + "-" +
        date.getDate();
}

//提交添加
function submitAdd() {
    $("#addForm").form('submit',{
        url:"/controller/note/addOneNote",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#addBox").dialog({
                    closed: true
                });

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
        url:"/controller/note/editOneNote",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            if (data == "true") {
                $("#editBox").dialog({
                    closed: true
                });

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
function findSomeNotes() {
    var d = $("#findSomeForm").form('submit',{
        url:"/controller/note/findSomeNotes",
        onSubmit:function () {
            return $(this).form('validate')
        },
        success:function (data) {
            var json = eval('(' + data + ')');
            alert(typeof json);

            initSelectAllNotes(json);
         }
    })

    alert(d.noteTypeSearch);
}
