$(
    //自动获取列表
    function () {
        initSelect();
    }
);

// 加载部门下拉框
$("#departmentID").combotree({
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


//加载时间select
function initSelect() {
    var year, month;
    year = [];
    month = [];

    for (var i = 2018; i <= 2030; i++) {
        year.push({ text: i, id: i });
    }
    for (var j = 1; j <= 12; j++) {
        month.push({ text: j, id: j });
    }

    $("#year").combobox("loadData", year);
    $("#month").combobox("loadData", month);

    $("#year").combobox('setValue', "全部");
    $("#month").combobox('setValue', "全部");
}

//出勤列表加载
function initAttendanceStatsDept(isURL) {
    // 加载表格
    $("#table").datagrid({
        title: "出勤列表",
        iconCls: "icon-left02",
        url: isURL,
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
                field:'cardNumber',
                title:'员工卡号',
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
                field:'status3',
                title:'迟到',
                width:100,
                align:'center'
            },
            {
                field:'status10',
                title:'病假',
                width:100,
                align:'center'
            },
            {
                field:'status9',
                title:'事假',
                width:100,
                align:'center'
            },
            {
                field:'status12',
                title:'丧家',
                width:100,
                align:'center'
            },
            {
                field:'status11',
                title:'婚嫁',
                width:100,
                align:'center'
            },
            {
                field:'status4',
                title:'旷工',
                width:100,
                align:'center'
            },
            {
                field:'status1',
                title:'出勤天数',
                width:100,
                align:'center'
            },
            {
                field:'attendanceDay',
                title:'应出勤天数',
                width:100,
                align:'center',
                formatter: function (val, row) {
                    var attDate = parseInt(row.status1) +
                        parseInt(row.status3) +
                        parseInt(row.status4) +
                        parseInt(row.status9) +
                        parseInt(row.status10) +
                        parseInt(row.status11) +
                        parseInt(row.status12) +
                        parseInt(row.status13);
                    return attDate;
                }
            }
        ]]
    })
}

//提交检索
function findAttendanceStatsDept() {
    var searchRadio = $("input:radio:checked").val();
    var year = $("#year").combobox('getValue');
    var month = $("#month").combobox('getValue');
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var departmentID = $("#departmentID").val();
    var params = "";
    if (searchRadio == 1) {
        params =  "searchRadio=1" + "&year=" + year + "&month=" + month;
    }else if (searchRadio == 2) {
        params = "searchRadio=2" + "&date1=" + date1 + "&date2=" + date2;
    } else {
        params = "searchRadio=3" + "&departmentID=" + departmentID;
    }
    initAttendanceStatsDept("/controller/attendanceStatsDept/findAttendanceStatsDept?"+params);
}