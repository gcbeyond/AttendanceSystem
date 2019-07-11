$(
    //自动一级部门
    function () {
        initOneDepartmentList();
    }
);
//一级部门列表加载
function initOneDepartmentList() {
    // 加载表格
    $("#table").datagrid({
        title: "一级部门列表",
        iconCls: "icon-left02",
        url: 'json/departmentList.json',
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
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delOne(\'' + index + '\')">删除</a> ';
                    return a + e + d;
                }

            }
        ]]
    })
}

//二级部门列表加载
function initTowDepartmentList() {
    $("#tableTow").datagrid({
        title:"二级部门列表",
        iconCls:"icon-left02",
        url:'json/departmentList.json',
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
                formatter:function (val, row, index) {
                    var rowEdit = [row.departmentID, row.departmentName, row.startTimeAM, row.endTimeAM, row.startTimePM, row.endTimePM];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editTow(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delTow(\'' + index + '\')">删除</a> ';
                    return e+d;
                }
            }
        ]]
    });
}

//点击管理二级子部门，打开子部门界面
function manageChild(row) {

    $("#manageChild").dialog({
        title:"二级部门维护",
        width: 800,
        height: 400,
        closed: false,
        modal:true,
        shadow:true
    })

    $('#addTowForm').form('load',{
        departmentNameOne: row
    });

    initTowDepartmentList();
}

//编辑一级部门
function editOne(rowEdit) {

    $("#editOneBox").dialog({
        title:"一级部门编辑",
        width: 650,
        height: 350,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#editOneForm').form('load',{
        departmentID: rows[0],
        departmentName: rows[1],
        startTimeAM: rows[2],
        endTimeAM: rows[3],
        startTimePM: rows[4],
        endTimePM: rows[5]
    });

}

//编辑二级部门
function editTow(rowEdit) {

    $("#editTowBox").dialog({
        title:"二级部门编辑",
        width: 650,
        height: 350,
        closed: false,
        modal:true,
        shadow:true
    })

    var rows = rowEdit.split(",");
    $('#editTowForm').form('load',{
        departmentID: rows[0],
        departmentName: rows[1],
        startTimeAM: rows[2],
        endTimeAM: rows[3],
        startTimePM: rows[4],
        endTimePM: rows[5]
    });

}

//删除一级部门
function delOne(index) {

    $.messager.confirm('确定删除','你确定要删除你的选择记录吗',
        function (flag) {
            if (flag == false) {
                return;
            }
        })
    $("#table").datagrid("deleteRow", index);
    $.messager.show({
        title: "提示",
        msg: "删除一级级部门成功"
    });

    // $.messager.confirm('确定删除','你确定要删除你选择的记录吗？',
    //     function(flag)
    //     {
    //         if(flag == true) //如果点击确定（flag==true），则表示要进行删除
    //         {
    //             $.ajax(
    //                 {
    //                     type:"POST",
    //                     url:"json/DepartmentList.json",
    //                     dataType:"json",
    //                     beforeSend:function()
    //                     {
    //                         $("#tableOne").datagrid('loading');
    //                     },
    //                     success:function( data )
    //                     {
    //                         $("#tableOne").datagrid('loaded');
    //                         $("#tableOne").datagrid('load');
    //
    //                         if( data == null )
    //                         {
    //                             $.messager.show({
    //                                 title:'提示',
    //                                 msg:'一级部门删除失败！'
    //                             });
    //                         }else
    //                         {
    //                             $.messager.show({
    //                                 title:'提示',
    //                                 msg:'一级部门删除成功！'
    //                             });
    //                         }
    //                     },
    //                     error:function()
    //                     {
    //                         $("#tableOne").datagrid('loaded');
    //                         $("#tableOne").datagrid('load');
    //                         $.messager.show({
    //                             title:'提示',
    //                             msg:'一级部门删除失败！'
    //                         });
    //                     }
    //                 }
    //             );
    //         }
    //     });
}

//删除二级部门
function delTow(index) {
    $.messager.confirm("提示", "是否要删除该二级部门",
        function (flag) {
            if (flag == false) {
                return;
            }
        })
    $("#tableTow").datagrid("deleteRow", index);
    $.messager.show({
        title: "提示",
        msg: "删除二级部门成功"
    });

}
