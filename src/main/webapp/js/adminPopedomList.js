$(
    //自动获取列表
    function () {
        initAdminPopedomList();
    }
);

//权限列表加载
function initAdminPopedomList() {
    // 加载表格
    $("#table").datagrid({
        title: "权限列表",
        iconCls: "icon-left02",
        url: 'json/adminPopedomList.json',
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
        sortName: 'popedomID',
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
            // {
            //     field:'popedomID',
            //     title:'编号',
            //     width:100,
            //     align:'center'
            // },
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
                field:'departmentOne',
                title:'一级部门（编号）',
                width:100,
                align:'center'
            },
            {
                field:'departmentTow',
                title:'二级部门（编号）',
                width:100,
                align:'center'
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
function initEditAdminPopedomList() {
    // 加载表格
    $("#tableTow").datagrid({
        title: "权限列表",
        iconCls: "icon-left02",
        url: 'json/adminPopedomList.json',
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
                field:'popedomID',
                title:'编号',
                width:100,
                align:'center'
            },
            {
                field:'adminAccount',
                title:'账号',
                width:100,
                align:'center',
            },
            {
                field:'adminName',
                title:'用户名',
                width:100,
                align:'center'
            },
            {
                field:'departmentOne',
                title:'一级部门（编号）',
                width:100,
                align:'center'
            },
            {
                field:'departmentTow',
                title:'二级部门（编号）',
                width:100,
                align:'center'
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
        height: 300,
        closed: false,
        modal: true,
        shadow: true
    });

    var rows =$("#table").datagrid("getSelected");
    $('#addForm').form('load',{
        adminAccount: rows.adminAccount,
        adminName: rows.adminName
    });
    initEditAdminPopedomList();
}

//删除权限
function delOne(index) {

    $.messager.confirm("提示", "是否要删除该权限",
        function (flag) {
            if (flag == false) {
                return;
            }
        })
    $("#table").datagrid("deleteRow", index);
    $.messager.show({
        title: "提示",
        msg: "删除权限成功"
    });

}
