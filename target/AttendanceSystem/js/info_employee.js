
// 加载部门下拉框
$("#deptName").combotree({
        url:'json/user.json',
        height:26,
        width:'200',
        onSelect:function () {
                var t=$("#deptName").combotree('tree');
                var n=t.tree('getSelected');
                var text=n.text;
                $("#deptName").combotree('setValue',text);

        }
})
// 加载职务下拉框
$("#posiName").combotree({
        url:'json/user.json',
        height:26,
        width:'200',
        onSelect:function () {
                var t=$("#posiName").combotree('tree');
                var n=t.tree('getSelected');
                var text=n.text;
                $("#posiName").combotree('setValue',text);

        }
})
obj={
        // 查询
        find:function () {
                $("#table").datagrid('load',{
                        user:$("#user").val(),
                        date:$.trim($("#dd").val())
                })
        },
        // 添加
        addBox:function () {
                $("#addBox").dialog({
                        closed: false
                });
                $("#name").val('');
                $("#pass").val('');
                $("#part").combotree('setValue','');
                var date=new Date();
                var y=date.getFullYear();
                var m=date.getMonth()+1;
                var d=date.getDate();
                var str=y+'-'+m+'-'+d
                $("#time").datebox('setValue',str);
        },
        // 编辑
        edit:function (id) {
                var ID;
                $("#addBox").dialog({
                        closed:false
                })
               $.ajax({
                       url:'json/table.json',
                       type:'post',
                       dataType:'json',
                       success:function (res) {
                               if(res){
                                       var data=res.rows;
                                       $.each(data,function (index) {
                                               ID=data[index].id;
                                               if(empId==ID){
                                                       $('#addForm').form('load',{
                                                               empId:empId,
                                                               empName:data[index].empName,
                                                               empGender:data[index].empGender,
                                                               posiName:data[index].posiName,
                                                               deptName:data[index].deptName,
                                                               cardNumber:data[index].cardNumber

                                                       })
                                                       $("#part").combotree('setValue',data[index].part);
                                               }

                                       })
                               }
                       },
                       error:function () {
                               $.messager.show({
                                       title:'提示',
                                       msg:'更新失败'

                               })

                       }
               })

        },
        sum:function () {
                $("#addForm").form('submit',{
                        url:"",
                        onSubmit:function () {
                                return $(this).form('validate')

                        },
                        success:function (data) {
                              var name=$("#name").val();
                                var pass=$("#pass").val();
                                var time=$("#time").datebox('getValue');
                                var part=$("#part").combotree('getValue');
                                var id=parseInt(Math.random()*100000);
                                $("#table").datagrid('insertRow',{
                                        index:1,
                                        row: {
                                                id:id,
                                                name: name,
                                                pass: pass,
                                                time:time,
                                                part:part
                                        }


                                })
                                $("#addBox").dialog({
                                        closed: true

                                })
                                $.messager.show({
                                        title:'提示',
                                        msg:'信息保存成功'
                                })



                        }
                })

        },
        // 删除多个
        del:function () {
                var rows=$("#table").datagrid("getSelections");
               if(rows.length>0){
                       $.messager.confirm('确定删除','你确定要删除你选择的记录吗？',function (flg) {
                               if(flg){
                                       var ids=[];
                                       for(i=0;i<rows.length;i++){
                                               ids.push(rows[i].id);

                                       }
                                       var num=ids.length;
                                      $.ajax({
                                              type:'post',
                                              url:"",
                                              data:{
                                                      ids:ids.join(',')
                                              },
                                              beforesend:function () {
                                                      $("#table").datagrid('loading');
                                                      
                                              },
                                              success:function (data) {
                                                      if(data){

                                                              $("#table").datagrid('loaded');
                                                              $("#table").datagrid('load');
                                                              $("#table").datagrid('unselectAll');
                                                              $.messager.show({
                                                                      title:'提示',
                                                                      msg:num+'个用户被删除'
                                                              })

                                                      }
                                                      else{
                                                              $.messager.show({
                                                                      title:'警示信息',
                                                                      msg:"信息删除失败"
                                                              })

                                                      }

                                              }
                                      })
                               }

                       })

               }
               else{
                       $.messager.alert('提示','请选择要删除的记录','info');
               }

        },

        //删除一个
        delOne:function (id) {
                id=$("#table").datagrid('getSelected').id;
                $.messager.confirm('提示信息','是否删除所选择记录',function (flg) {
                        if(flg){
                                $.ajax({
                                        type:'post',
                                        url:'',
                                        data:{
                                                ID:id
                                        },
                                        beforesend:function () {
                                                $("#table").datagrid('loading');

                                        },
                                        success:function (data) {
                                                if(data){
                                                        $("#table").datagrid("loaded");
                                                        $("#table").datagrid("load");
                                                        $("#table").datagrid("unselectRow");
                                                        $.messager.show({
                                                                title:'提示信息',
                                                                msg:"信息删除成功"
                                                        })
                                                }
                                                else{
                                                        $.messager.show({
                                                                title:'警示信息',
                                                                msg:"信息删除失败"
                                                        })

                                                }

                                        }
                                })

                        }

                })


        }
}

// 弹出框加载
$("#addBox").dialog({
        title:"信息内容",
        width:700,
        height:300,
        closed: true,
        modal:true,
        shadow:true
})
// 加载表格
$("#table").datagrid({
        title:"数据列表",
        iconCls:"icon-left02",
        url:'json/info_employee.json',
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
                        field:'empId',
                        title:'编号',
                        width:100,
                        align:'center'
                },
                {
                        field:'empName',
                        title:'姓名',
                        width:100,
                        align:'center'
                },
                {
                        field:'empGender',
                        title:'姓别',
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
                        field:'posiName',
                        title:'职位',
                        width:100,
                        align:'center'

                }, {
                        field:'deptName',
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
                                e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                                d = '<a  id="add" data-id="98" class=" operA01"  onclick="obj.delOne(\'' + row.id + '\')">删除</a> ';
                                return e+d;

                        }

                }
        ]]
})