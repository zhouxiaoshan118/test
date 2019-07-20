<#include "../../common/all.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>用戶字典</title>
    <link rel="stylesheet" href="${basePath}/plugins/bootstrap/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet" />
    <#--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" >
    <style type="text/css">
        body{padding:10px;border:2px solid #efefef;border-radius:5px;}
    </style>
</head>
<body>
<table id="dictTable" class="table table-striped table-bordered" cellspacing="0" width="98%" style="text-align:center">
    <thead>
    <tr>
        <th>id</th>
        <th>类型</th>
        <th>标签</th>
        <th>属性值</th>
        <th>备注</th>
        <th>权重</th>
        <th>操作</th>
    </tr>
    </thead>
</table>

<form id="add" class="layui-form layui-fluid layui-col-md12" action="" style="display:none;">
    <div style="width:440px;height:380px;display: block;margin: 7px auto;">
        <div id="dicid" class="layui-form-item layui-col-md10" value="">
            <label class="layui-form-label">类型</label>
            <div class="layui-input-block">
                <input type="text" name="type" maxlength="255" required lay-verify="required" placeholder="请输入类型" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">标签</label>
            <div class="layui-input-block">
                <input type="text" name="label" maxlength="255" required lay-verify="required" placeholder="请输入标签" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">属性值</label>
            <div class="layui-input-block">
                <input type="text" name="value" maxlength="255" required lay-verify="required" placeholder="请输入属性值" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="text" name="weight" maxlength="255" required lay-verify="required" placeholder="请输入权重值" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item layui-form-text layui-col-md10">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="mark" maxlength="255" placeholder="请输入备注信息，备注信息不得超过127个汉字" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-col-md10">
            <div class="layui-input-block">
                <button id="subBtn" class="layui-btn" lay-submit lay-filter="addSub">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

</body>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="${basePath}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" >


    var delItems = new Array();
    var isbtn = 0;
	
    $(document).ready(function(){
        dataTable = $('#dictTable').DataTable({
            "jQueryUI": true,
            "pagingType":   "full_numbers",

			"dom": '<"toolbar">rtilp',

            <#--打开数据加载时的等待效果-->
            "processing": true,
            <#--打开后台分页-->
            "serverSide": true,
            "bPaginate": true, //翻页功能
            "bLengthChange" : true, //改变每页显示数据数量
            "bFilter" : false, //过滤功能
            "ordering" : false,
            "bSort" : false, //排序功能
            //"bInfo" : true,//页脚信息
            //"bAutoWidth" : true,//自动宽度
            "stateSave" : true,
            "retrieve" : true,
            "ordering" : true,
            "bSort" : true, //排序功能
            "bInfo" : true,//页脚信息
            "bAutoWidth" : true,//自动宽度
            ajax : function(data, callback, settings) {
                data.columns = null;
                // data.length = 10;
                // console.log(data);
                data.label = $("#keyword").val();
                data.page_size = data.length;
                data.page_no = data.start/data.page_size+1;
                $.ajax({
                    type : "GET",
                    url : "${adminPath}/sys/dict/list",
                    cache : false, //禁用缓存
                    data : data, //传入已封装的参数
                    dataType : "json",
                    success : function(res) {
                        console.log(res);
                        setTimeout(
                                function() {
                                    var returnData = {};
                                    // returnData.draw = res.current;
                                    returnData.recordsFiltered = res.total;
                                    returnData.data = res.result;
                                    returnData.recordsTotal = res.total;
                                    returnData.length = res.size;
                                    callback(returnData);
                                },
                                200);
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        $.alert("查询失败");
                    }
                });
            },
            "columnDefs": [
                  {
                    "targets": [ 0 ],
                    "visible": false,
                    "searchable": false
                  },
                  
                  {
                  	"targets" : [6],
                  	"data" : null,
                  	"render" : function(data, type, row) {
									var id = '"' + row.id + '"';
									var html = "<button onclick='javascript:updateOp(this);' class='layui-btn  updateOpBtn layui-btn-sm' value="+id+">编辑</button>";
									html += "<button  onclick='javascript:deleteOp(this);' class='layui-btn layui-btn-danger layui-btn-sm' value="+id+">删除</button>&nbsp;&nbsp;";
									return html;
									}
                  }
                  
            ],
            <#--国际化-->
            "oLanguage": {
                    "sSearch": "搜索",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
      
                            "oPaginate":
                            {
                                "sFirst": "首页",
                                "sPrevious": "前一页",
                                "sNext": "后一页",
                                "sLast": "末页"
                            }
                },
           
           	<#--每列数据-->
            "aoColumns": [
                {data: 'id'},
                { data: 'type'},
                { data: 'label'},
                { data: 'value'},
                { data: 'mark'},
                { data: 'weight'}
            ],   
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量删除</button>" +
                "<div class='layui-input-inline' style='float: right; padding-right: 20px;'>" +
                " <label class='layui-form-label'>标签：</label>" +
                "    <div class='layui-input-inline'>" +
                "      <input id='keyword' type='text' name='keyword' placeholder='请输入关键字' class='layui-input layui-col-md6'>" +
                "    </div>" +
                "&nbsp;&nbsp;<button id ='dosearch' class='layui-btn layui-btn-normal' >搜 索</button>" +
                "</div>");

        $('#dictTable tbody').on( 'click', 'tr', function () {
            console.log($(this));
            if(isbtn == 0) {
                $(this).toggleClass('layui-bg-black');
                delItems = dataTable.rows('.layui-bg-black').data();
            }
            isbtn = 0

        } );

        $("#dosearch").click(function() {
            dataTable.ajax.reload();
        })

        $("#dictTable_info").css("float","left");
        $("#dictTable_length").css("float","left");
        $("#dictTable_length").css("padding","8px");
    });

    var getShow;

    var getUpdate;

    //   layUI 弹框修改
    layui.use(['form','layer'] ,function() {
        var form = layui.form
        ,layer = layui.layer;

        form.on('submit(addSub)', function (data) {

            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
                $.ajax({
                    type:"post",
                    url: "${adminPath}/sys/officer/",
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('插入成功', {icon: 1, time:1000});
                        dataTable.ajax.reload();
                    },
                    error: function() {
                        layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                    }
                })
            }, function(){
                layer.msg('已取消', {icon: 0, time:1000});
            });
            $("#add").css("display","none");
            return false;
        });

        form.on('submit(updateSub)', function (data) {
            layer.confirm('您是否确定修改数据？', {
                btn: ['确定','取消']
            }, function(){
                $.ajax({
                    type:"put",
                    url: "${adminPath}/sys/dict/"+$("#dicid").attr("value"),
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('修改成功', {icon: 1, time: 1000});
                        dataTable.ajax.reload();
                    },
                    error: function() {
                        layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                    }
                })
            }, function(){
                layer.msg('修改已经取消', {icon: 0, time: 1000});
            });
            return false;
        });

         getShow = function() {
            return layer.open({
                type: 1,
                title: '数据添加',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', "450px", '90%'],
                content: $("#add"),
                end: function(index, layero){
                    $("#add").css("display","none");
                }
            });
         }

         getUpdate = function() {
             return layer.open({
                 type: 1,
                 title: '数据修改',
                 shadeClose: true,
                 shade: 0.3,
                 scrollbar:false,
                 area: ['600px', "450px", '90%'],
                 content: $("#add"),
                 end: function(index, layero){
                     $("#add").css("display","none");
                 }
             });
         }


    })

    function showAdd() {
        $("#subBtn").attr("lay-filter","addSub");
        $("input[name='type']").attr("value","");
        $("input[name='label']").attr("value","");
        $("input[name='value']").attr("value","");
        $("input[name='weight']").attr("value","");
        $("textarea[name='mark']").text("");
        return getShow();
    };

    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        $.ajax({
            type:'get',
            url:'/admin/sys/dict/'+id,
            success:function(res) {
                if(res != null) {
                    $("#dicid").attr("value",res.id);
                    $("input[name='type']").attr("value",res.type);
                    $("input[name='label']").attr("value",res.label);
                    $("input[name='value']").attr("value",res.value);
                    $("input[name='weight']").attr("value",res.weight);
                    $("textarea[name='mark']").text(res.mark);
                    $("#subBtn").attr("lay-filter","updateSub");
                }
            }
        });



        return getUpdate();

    };


    function deleteOp(e){
        <#-- 删除操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        layer.confirm('您是否确定删除数据？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            $.ajax({
                type:"delete",
                url:"${adminPath}/sys/dict/"+id,
                success:function() {
                    dataTable.ajax.reload();
                },
                error: function() {
                    layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                }

            })
            layer.msg('删除成功', {icon: 1});
        }, function(){
            layer.msg('删除已取消', {icon: 1});
        });

    };

    function deleteItems() {
        layer.confirm('您是要删除选中行？', {
            btn: ['是的','取消']
        }, function(){
            // console.log(delItems);
            var ids = new Array();
            for(var i = 0; i < delItems.length; i++ ) {
                ids[i] = delItems[i].id;
            }

            $.ajax({
                type:"delete",
                url:"${adminPath}/sys/dict/",
                data:JSON.stringify(ids),
                contentType:"application/json",
                success:function() {
                    layer.msg('删除成功', {icon: 1});
                    dataTable.ajax.reload();
                },
                error: function() {
                    layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                }
            })


        }, function(){
            layer.msg('删除操作已取消', {icon: 1});
        });
    }

</script>
</html>
