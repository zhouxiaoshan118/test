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
        .xtree_contianer {
        	display:none;
        	position:absolute;
        	top:20px;
        	left:100px;
            width: 500px;
            border: 1px solid #9C9C9C;
            overflow: auto;
            margin-bottom: 30px;
            background-color: #fff;
            padding: 10px 0 25px 5px;
            height:350px;
        }
        #close{
        	display:none;
        	position:absolute;
        	top:30px;
        	left:550px;
        	z-index:99999999999;
        	cursor:pointer
        }
    </style>
</head>
<body>
<table id="dictTable" class="table table-striped table-bordered" cellspacing="0" width="98%" style="text-align:center">
    <thead>
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>所能查看的资源(1:PC前台;2:PC后台;3:微信端;4:手机App)</th>
        <th>操作</th>
    </tr>
    </thead>
</table>

<form id="add" class="layui-form layui-fluid layui-col-md12" action=""	enctype="multipart/form-data" style="display:none;">
    <div style="width:640px;height:380px;display: block;margin: 9px auto;">
        <div id="dicid" class="layui-form-item layui-col-md8" value="">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="previewUserName" maxlength="255" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div  class="layui-form-item layui-col-md8" value="">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="previewPassword" maxlength="255" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">选择权限</label>
            <div class="layui-input-block">
				<button type="button" class="layui-btn" id="addRoot">选择</button>
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">开始时间</label>
            <div class="layui-input-block">
                <input type="text" name="startDate" id="startDate"  class="layui-input" lay-verify="required" placeholder="请输入开始时间">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">结束时间</label>
            <div class="layui-input-block">
                <input type="text" name="endDate" id="endDate"  class="layui-input" lay-verify="required" placeholder="请输入结束时间">
            </div>
        </div>
        <div class="layui-form-item layui-col-md10">
            <div class="layui-input-block">
                <button id="subBtn" class="layui-btn" lay-submit lay-filter="addSub">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
    <div id="xtree1" class="xtree_contianer"></div>
    <div id="close"><img src="${basePath}/plugins/preview/img/close.png" width="20px" height="20px"></div> 
</form>
</body>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="${basePath}/plugins/layui/layui.all.js"></script>
<script src="${basePath}/js/layui-xtree/layui-xtree.js"></script>
<script type="text/javascript" th:inline="javascript">

	


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
                data.previewUserName = $("#keyword").val();
                data.page_size = data.length;
                data.page_no = data.start/data.page_size+1;
                $.ajax({
                    type : "GET",
                    url : "/front/sys/previewUser/list?time="+new Date().getTime(),
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
                        layer.msg("查询失败");
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
									html += "<button  onclick='javascript:deleteOp(this);' class='layui-btn layui-btn-danger layui-btn-sm' value="+id+">"+pdStrict(row.delState)+"</button>&nbsp;&nbsp;";
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
                { data: 'previewUserName',width:50},
                { data: 'previewPassword',width:50},
                { data: 'startDate',width:80},
                { data: 'endDate',width:80},
                { data: 'viewResources',width:400},
            ],   
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量禁用</button>" +
                "<div class='layui-input-inline' style='float: right; padding-right: 20px;'>" +
                " <label class='layui-form-label'>姓名：</label>" +
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
    
    var previewUserName = $("input[name='previewUserName']");
    previewUserName.change(function(){
    	var length = previewUserName.val().length;
    	if(length>10){
    		previewUserName.val("");
    		layer.msg("用户名请不要超过10个字符");
    		return false;
    	}
    	if(checkNameFalg){
    		$.ajax({
	    		url:"/front/sys/previewUser/checkPreviewUserName?time="+new Date().getTime(),
	    		data:{
	    			"previewUserName":previewUserName.val()
	    		},
	    		type:"post",
	    		success:function(data){
	    			if(data.isExist == 1){
	    				layer.msg("该用户名已经存在了");
	    				previewUserName.val("");
	    			}
	    		}
    		})
    	}else{
    		if( $.trim(previewUserName.val()) == checkNameValue){
    			return false;
    		}
    		$.ajax({
	    		url:"/front/sys/previewUser/checkPreviewUserName?time="+new Date().getTime(),
	    		data:{
	    			"previewUserName":previewUserName.val()
	    		},
	    		type:"post",
	    		success:function(data){
	    			if(data.isExist == 1){
	    				layer.msg("该用户名已经存在了");
	    				previewUserName.val("");
	    			}
	    		}
    		})
    	}
    })

    var getShow;

    var getUpdate;

	var checkNameFalg;
	
	var checkNameValue;

    //   layUI 弹框修改
    layui.use(['form','layer','laydate'] ,function() {
    	
    	
    	var $=layui.jquery;
        form = layui.form;
        layer = layui.layer;
        var laydate=layui.laydate;
        
        $("#addRoot").click(function(){
    		$(".xtree_contianer").show();
    		$("#close").show();
    	})
    	$("#close").click(function(){
    		$(".xtree_contianer").hide();
    		$("#close").hide();
    	})
        
       
        
        laydate.render({
        	elem:'#startDate',
        	type:'datetime',
        	format: 'yyyy-MM-dd HH:mm:ss', 
    		istime: true,   
   			istoday: true,
   			choose: function(datas){ 
		        end.min = datas; 
		        end.start = datas 
   			}
        })
        laydate.render({
        	elem:'#endDate',
        	type:'datetime',
        	format: 'yyyy-MM-dd HH:mm:ss', 
    		istime: true,   
   			istoday: true,
   			choose: function(datas){ 
		        end.min = datas; 
		        end.start = datas 
   			}
        })
		<!-- 添加数据 -->
        form.on('submit(addSub)', function (data) {
        	var index = 0;
        	var parentIDArr = new Array();
        	var rootStr="";
        	var oCks = xtree1.GetChecked();
        	var len = oCks.length;
        	for(var i=0;i<len;i++){
        		var parentID=xtree1.GetParent(oCks[i].value).value;
        		if($.inArray(parentID,parentIDArr)==-1){
        			parentIDArr[index] = parentID;
        			index++;
        		}
        	}
        	for(var k=0;k<parentIDArr.length;k++){
        		var parentIDStr = parentIDArr[k];
	        	for(var j=0 ;j<len;j++){
	        		var ocksVal=oCks[j].value;
	        		if(ocksVal.indexOf(parentIDStr)!=-1){
	        			 var resourceArr = ocksVal.split("_");
	        			 parentIDArr[k] = parentIDArr[k] + "_" + resourceArr[1];
	        		}
	        	}
        	}
        	
        	data.field.resources = parentIDArr;
        	
        	var startDate = data.field.startDate.toString();
        	var endDate = data.field.endDate.toString();
        	
        	if(startDate >= endDate){
        		layer.msg("结束时间要大于开始时间");
        		$("input[name='endDate']").attr("value","");
        		return false;
        	}
        	
            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
                $.ajax({
                    type:"post",
                    url: "/front/sys/previewUser",
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('插入成功', {icon: 1, time:1000});
                        dataTable.ajax.reload();
                        layer.closeAll();
                    },
                    error: function() {
                        layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                    }
                })
            }, function(){
                layer.msg('已取消', {icon: 0, time:1000});
                //layer.closeAll();
            });
            //$("#add").css("display","none");
            return false;
        });
	
		<!--修改数据-->
        form.on('submit(updateSub)', function (data) {
        	
        	checkNameFalg = false;
        	
        	var index = 0;
        	var parentIDArr = new Array();
        	var rootStr="";
        	var oCks = updateXtree.GetChecked();
        	var len = oCks.length;
        	for(var i=0;i<len;i++){
        		var parentID=updateXtree.GetParent(oCks[i].value).value;
        		if($.inArray(parentID,parentIDArr)==-1){
        			parentIDArr[index] = parentID;
        			index++;
        		}
        	}
        	for(var k=0;k<parentIDArr.length;k++){
        		var parentIDStr = parentIDArr[k];
	        	for(var j=0 ;j<len;j++){
	        		var ocksVal=oCks[j].value;
	        		if(ocksVal.indexOf(parentIDStr)!=-1){
	        			 var resourceArr = ocksVal.split("_");
	        			 parentIDArr[k] = parentIDArr[k] + "_" + resourceArr[1];
	        		}
	        	}
        	}
        	
        	data.field.resources = parentIDArr;
        	
        
            layer.confirm('您是否确定修改数据？', {
                btn: ['确定','取消']
            }, function(){
                $.ajax({
                    type:"put",
                    url: "/front/sys/previewUser/"+$("#dicid").attr("value")+"?time="+new Date().getTime(),
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('修改成功', {icon: 1, time: 1000});
                        dataTable.ajax.reload();
                        layer.closeAll();
                        
                    },
                    error: function() {
                        layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                    }
                })
            }, function(){
                layer.msg('修改已经取消', {icon: 0, time: 1000});
                //layer.closeAll();
            });
            return false;
        });

         getShow = function() {
            return layer.open({
                type: 1,
                title: '数据添加',
                anim:1,
                maxmin:true,
                shadeClose: true,
                shade: 0.3,
                area: ['800px', "450px", '90%'],
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
                 anim:3,
                 maxmin:true,
                 shade: 0.8,
                 scrollbar:false,
                 area: ['800px', "450px", '90%'],
                 content: $("#add"),
                 end: function(index, layero){
                     $("#add").css("display","none");
                 }
             });
         }


    })
    
    function pdStrict(delState){
    	if(delState == 1){
    		return "禁用";
    	}else{
    		return "启用";
    	}
    }

    function showAdd() {
    				
    				checkNameFalg = true
    				
    				xtree1 = new layuiXtree({
				            elem: 'xtree1'   //(必填) 放置xtree的容器，样式参照 .xtree_contianer
				            , form: form     //(必填) layui 的 from
				            , data: "/front/sys/previewUser/xtree"     //(必填) json数据
				     });
    
    				$("#subBtn").attr("lay-filter","addSub");
                    $("input[name='previewUserName']").attr("value","");
                    $("input[name='previewPassword']").attr("value","");
                    $("input[name='startDate']").attr("value","");
                    $("input[name='endDate']").attr("value","");
                    form.render();
                    
       				 return getShow();
    };

	var updateXtree;
	
    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        
        console.log(id);
        
       updateXtree = new layuiXtree({
            	elem: 'xtree1'   //(必填) 放置xtree的容器，样式参照 .xtree_contianer
            	, form: form     //(必填) layui 的 from
            	, data: "/front/sys/previewUser/updateXtree/"+id+"?time="+new Date().getTime()     //(必填) json数据
        	});
        
        $.ajax({
            type:'get',
            url:'/front/sys/previewUser/'+id+"?time="+new Date().getTime(),
            success:function(res) {
            	console.log(res);
                if(res != null) {
                    $("#dicid").attr("value",res.id);
                    $("input[name='previewUserName']").attr("value",res.previewUserName);
                    $("input[name='previewPassword']").attr("value",res.previewPassword);
                   
                   	checkNameValue = res.previewUserName;
                   
                   
                    $("input[name='startDate']").attr("value",res.startDate);
                    $("input[name='endDate']").attr("value",res.endDate);
                    
                    
                   	form.render();
                    
                    $("#subBtn").attr("lay-filter","updateSub");
                }
            }
        });

        return getUpdate();

    };
    
   


    function deleteOp(e){
        <#-- 禁用操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
       	if($(e).html()=="禁用"){
       		layer.confirm('您是否确定禁用数据？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            $.ajax({
                type:"delete",
                url:"/front/sys/previewUser/"+id,
                success:function() {
                    dataTable.ajax.reload();
                },
                error: function() {
                    layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                }

            })
            layer.msg('禁用成功', {icon: 1});
        }, function(){
            layer.msg('禁用已取消', {icon: 1});
        });
       	}else{
       		layer.confirm('您是否确定启用数据？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            $.ajax({
                type:"get",
                url:"/front/sys/previewUser/qiyong/"+id,
                success:function() {
                    dataTable.ajax.reload();
                },
                error: function() {
                    layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                }

            })
            layer.msg('启用成功', {icon: 1});
        }, function(){
            layer.msg('启用已取消', {icon: 1});
        });
       	}
        

    };

    function deleteItems() {
    	if(delItems.length == 0){
    		layer.msg("请选择要禁用的用户");
    		return false;
    	}
    
        layer.confirm('您是要禁用选中行？', {
            btn: ['是的','取消']
        }, function(){
            // console.log(delItems);
            var ids = new Array();
            for(var i = 0; i < delItems.length; i++ ) {
                ids[i] = delItems[i].id;
            }

            $.ajax({
                type:"delete",
                url:"/front/sys/previewUser/",
                data:JSON.stringify(ids),
                contentType:"application/json",
                success:function() {
                    layer.msg('禁用成功', {icon: 1});
                    dataTable.ajax.reload();
                },
                error: function() {
                    layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                }
            })


        }, function(){
            layer.msg('禁用操作已取消', {icon: 1});
        });
    }


</script>
</html>
