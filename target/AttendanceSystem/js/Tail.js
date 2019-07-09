/**
 * Created by Administrator on 2017/11/8.
 */

$("#tabs").tabs({
        fit:true
})
$("#dateBox").datebox({

        required:true
})

$("#nation").combobox({
        url:'json/nation.json',
        valueField:'id',
        textField:'text'

})
$("#nation01").combobox({
        url:'json/nation.json',
        valueField:'id',
        textField:'text'

})
$("#part").combotree({
        url:"json/userTree.json"
})

// 加载表格
$("#table").datagrid({
        title:"数据列表",
        iconCls:"icon-left02",
        url:'json/user.json',
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
        sortName:'id',
        checkOnSelect:false,
        sortOrder:'asc',
        toolbar: '#tabelBut',
        columns:[[
                {
                        checkbox:true,
                        field:'no',
                        width:100,
                        align:'center'
                },
                {
                        field:'id',
                        title:'编号',
                        width:100,
                        align:'center'



                },
                {
                        field:'title',
                        title:'用户名',
                        width:100,
                        align:'center'



                },
                {
                        field:'sex',
                        title:'姓别',
                        width:100,
                        align:'center',

                        formatter:function (val,row) {
                                if(val=='男'){
                                        return '<div style="color: green">'+val+'</div>';
                                }
                                else{
                                        return '<div style="color: red">'+val+'</div>';
                                }

                        }


                }
        ]]
})