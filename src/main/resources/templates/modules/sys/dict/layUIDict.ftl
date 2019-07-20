<#include "../../../common/all.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LayUi数据表</title>
    <link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" media="all">
    <style type="text/css">
        #dictTable {
            width:100%;
        }
    </style>
</head>
<body>
    <button class="layui-btn">
        <i class="layui-icon">&#xe608;</i> 添加数据
    </button>
    <table id="dictTable" lay-filter="test"></table>
</body>
<script src="${basePath}/plugins/layui/layui.js"></script>
 
<script>
   layui.use('table', function(){
       var table = layui.table;


        table.render({
            elem:'#dictTable',
            height: 350,
            //width:'100%',
            page:true,
            url:'/admin/sys/dict/list',
            request:{
                pageName: 'page_no', //页码的参数名称，默认：page
                limitName: 'page_size' //每页数据量的参数名，默认：limit
            },
            response:{
                statusName: 'status', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'result'
            },
//            data:[
//                    {id:'12312313',type:'daiwei_sex',label:'男',value:'1',mark:'测试信息',weight:'20'},
//                    {id:'1231432434',type:'daiwei_sex',label:'女',value:'0',mark:'测试信息',weight:'30'}
//                 ],
            cols:[[//表头
                {field:'id', minWidth: '0%', width: '0%', type: 'space', style: 'display: none'},
                {field:'type',title:'类型', width:'16%', align:'center', sort:true, unresize:true},
                {field:'label',title:'标签', width:'16%', align:'center', sort:true, unresize:true},
                {field:'value',title:'属性值', width:'16%', align:'center', sort:true, unresize:true},
                {field:'mark',title:'备注', width:'16%', align:'center', sort:true, unresize:true},
                {field:'weight',title:'权重', width:'14%', align:'center', sort:true, unresize:true},
                {fixed: 'right',title:'操作', width:'20%', align:'center', unresize:true, toolbar: '#dictTablebar'}
            ]]
        })

        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var id = tr[0].childNodes[0].childNodes[0].innerHTML;

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                console.log("删除 --> "+id+"号数据信息")
                //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){ //编辑
                //do something
                console.log("弹出修改框 --> "+id+"号数据信息")
                //同步更新缓存对应的值
                obj.update({
                username: '123'
                ,title: 'xxx'
                });
            }
        });
        
    })
</script>
<script type="text/html" id="dictTablebar">
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script> 
</html>