$(

    function () {
        //自动获取列表
        initNoteList('/controller/note/findAllNotes');

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

    }

);

//请假单列表加载
function initNoteList(isUrl) {
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
                field:'firstDName',
                title:'一级部门',
                width:100,
                align:'center'
            },
            {
                field:'secondDName',
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
                    var rowEdit = [row.noteID, row.employeeID, row.employeeName, row.secondDID, row.operatorID, row.operatorName, row.noteTypeID,
                        fillInTime, row.cause, row.startTimeAM, row.endTimeAM, row.startTimePM, row.endTimePM,
                        startDate, row.startTime, endDate, row.endTime, row.directorSign, row.administrationSign, row.presidentSign];

                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="removeOne(\'' + row.noteID+ '\')">删除</a> ';
                    return e+d;
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
    $("#startTime").combobox(
        {
            data:[
                {id :  rows[7],
                    text: '上午'},
                {id :  rows[9],
                    text: '下午'}
            ],
            valueField:'id',
            textField:'text'
        });

    $("#endTime").combobox(
        {
            valueField:'id',
            textField:'text',
            data:[
                {id :  rows[8] ,
                    text: '上午'},
                {id :  rows[10] ,
                    text: '下午'}
            ]
        }
    );
    $('#editForm').form('load',{
        noteID: rows[0],
        employeeID: rows[1],
        employeeName: rows[2],
        departmentID: rows[3],
        operatorID: rows[4],
        operatorName: rows[5],
        noteTypeID: rows[6],
        fillInTime: rows[7],
        cause: rows[8],
        startDate: rows[13],
        startTime: rows[14],
        endDate: rows[15],
        endTime: rows[16],
        directorSign: rows[17],
        administrationSign: rows[18],
        presidentSign: rows[19]
    });

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
    var noteTypeSearch = $("#noteTypeSearch").val();
    var deptSelect = $("#deptSelect").val();
    var empSearch = $("#empSearch").val();
    var dateSearch = $("#dateSearch").val();

    initNoteList('/controller/note/findAllNotes/findSomeNotes?' +
        '&noteTypeSearch=' + noteTypeSearch +
        '&deptSelect=' + deptSelect +
        '&empSearch=' + empSearch +
        '&dateSearch=' + dateSearch
    );
}

//修改:查看请假人信息
function employeeEditSee(employeeID, employeeName, firstDName, secondDName) {
    $("#editEmpSeeBox").dialog({
        title:"请假人信息查看",
        width: 400,
        height: 250,
        closed: false,
        modal:true,
        shadow:true
    })

    $('#editEmpSeeForm').form('load',{
        firstDName: firstDName,
        secondDName: secondDName,
        employeeID: employeeID,
        employeeName: employeeName

    });

    $("#editEmpSeeForm input[name='firstDName']").attr("readonly", "readonly");
    $("#editEmpSeeForm input[name='secondDName']").attr("readonly", "readonly");
    $("#editEmpSeeForm input[name='employeeID']").attr("readonly", "readonly");
    $("#editEmpSeeForm input[name='employeeName']").attr("readonly", "readonly");
}

//添加页面：查看请假人信息，部门下拉框
function departmentAddSee(isEmp) {
    // 加载部门下拉框
    $("#addEmpSeeDeptSelect").combotree({
        url: '/controller/department/departmentTree',
        height: 26,
        width: '167',
        onSelect: function () {
            var t = $("#addEmpSeeDeptSelect").combotree('tree');
            var n = t.tree('getSelected');
            var text = n.text;
            $("#addEmpSeeDeptSelect").combotree('setValue', text);
        },
        onBeforeSelect: function (node) {
            var tree = $(this).tree;
            var isLeaf = tree("isLeaf", node.target);
            if (!isLeaf) {
                $("#addEmpSeeDeptSelect").treegrid(unselect)
            }
        },
        onSelect: function (record) {
            addEmpSeeEmpSelect(record.id);
        }
    });

    $("#addEmpSeeBox").dialog({
        title:"请假人信息查看",
        width: 550,
        height: 350,
        closed: false,
        modal:true,
        shadow:true
    })

    $("#addEmpSeeBox input[name='isEmp']").val(isEmp);
}

//添加页面：查看请假人信息，员工姓名下拉框
function addEmpSeeEmpSelect(departmentID) {
    // 加载表格
    $("#tableEmpSee").datagrid({
        title: "员工列表",
        iconCls: "icon-left02",
        url: '/controller/employee/findEmpFromDept?departmentID=' + departmentID,
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
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val,row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="submitEmployeeName(\'' + row.employeeID + '\', \'' + row.employeeName + '\',  \'' + departmentID + '\')">选择</a> ';
                    return e;
                }

            }
        ]]
    })
}

function submitEmployeeName(employeeID, employeeName, departmentID) {
    var isEmp =  $("#addEmpSeeBox input[name='isEmp']").val();
    if (isEmp == "addEmp") {
        $("#addForm input[name='employeeName']").val(employeeName);
        $("#addForm input[name='employeeID']").val(employeeID);
        $("#addForm input[name='departmentID']").val(departmentID);
    }
    if (isEmp == "addOperator") {
        $("#addForm input[name='operatorName']").val(employeeName);
        $("#addForm input[name='operatorID']").val(employeeID);
    } else {
        $("#editForm input[name='operatorName']").val(employeeName);
        $("#editForm input[name='operatorID']").val(employeeID);
    }

    $("#addEmpSeeBox").dialog({
        closed: true
    });
}