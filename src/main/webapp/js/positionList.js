$(
    //自动加载职务列表
    function () {
        initPostionList();
    }
);
//职务列表加载
function initPostionList() {
    // 加载表格
    $("#table").datagrid({
        title: "职务列表",
        iconCls: "icon-left02",
        url: 'json/positionList.json',
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
                title:'职务名称',
                width:100,
                align:'center'
            },
            {
                field:"opr",
                title:'操作',
                width:100,
                align:'center',
                formatter:function (val, row, index) {
                    var rowEdit = [row.positionID, row.positionName];
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="editOne(\'' + rowEdit + '\')">编辑</a> ';
                    d = '<a  id="add" data-id="98" class=" operA01"  onclick="delOne(\'' + index + '\')">删除</a> ';
                    return e+d;
                }

            }
        ]]
    })
}

//添加职务
function addOne() {
    $('#addForm').form('clear');
    $("#addBox").dialog({
        title:"职务编辑",
        width: 500,
        height: 200,
        closed: false,
        modal:true,
        shadow:true
    })
}

//编辑职务
function editOne(rowEdit) {
    $("#addBox").dialog({
        title:"职务编辑",
        width: 500,
        height: 200,
        closed: false,
        modal:true,
        shadow:true
    })
    var rows = rowEdit.split(",");
    $('#addForm').form('load',{
        positionID: rows[0],
        positionName: rows[1]
    });
    $("input[name='positionID']").attr("readonly", "readonly");
}

//删除职务
function delOne(index) {

    $.messager.confirm("提示", "是否要删除该职务",
        function (flag) {
            if (flag == false) {
                return;
            }
        })
    $("#table").datagrid("deleteRow", index);
    $.messager.show({
        title: "提示",
        msg: "删除职务成功"
    });

}