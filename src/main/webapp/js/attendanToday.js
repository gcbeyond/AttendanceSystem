$(
    //自动获取列表
    function () {
        initEmployeeList();
    }
);

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

//出勤列表加载
function initEmployeeList() {

    var div1 = $("#div1");

    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: 'json/attendanToday.json',
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
        sortName: 'attendanceID',
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
                field:'attendanceID',
                title:'考勤编号',
                width:100,
                align:'center'
            },
            {
                field:'employeeName',
                title:'员工名称',
                width:100,
                align:'center'
            },
            {
                field:'departmentName',
                title:'员工部门',
                width:100,
                align:'center'
            },
            {
                field:'attendanceDate',
                title:'出勤日期',
                width:100,
                align:'center'
            },
            {
                field:'attendanceType',
                title:'出勤状态',
                width:100,
                align:'center',
                formatter:function()
                {
                    return div1.html();
                }
            },
            {
                field:"opr",
                title:'&nbsp;&nbsp;关联表单',
                width:250,
                align:'left',
                formatter:function (val,row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="searchOne(\'' + row.attendanceID + '\')">查询</a> ';
                    return e;

                }

            }
        ]]
    })
}

//编辑出勤
function searchOne(rowEdit) {

}
