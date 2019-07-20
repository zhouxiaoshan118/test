<#include "../../common/all.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>实战项目</title>
    <link rel="stylesheet" href="${basePath}/plugins/bootstrap/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet" />
    <#--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/plugins/actuals/layui/css/layui.css" media="all"  >
    <link rel="stylesheet" href="${basePath}/plugins/actuals/css/public.css"  media="all"  >
    <style type="text/css">
        body{padding:10px;border:2px solid #efefef;border-radius:5px;}
    </style>
</head>
<body>
<table id="dictTable" class="table table-striped table-bordered" cellspacing="0" width="98%" style="text-align:center">
    <thead>
    <tr>
        <th>id</th>
        <th>项目名称</th>
        <th>负责人</th>
        <th>开始时间</th>
        <th>截止时间</th>
        <th>权重</th>
        <th>是否完成</th>
        <th>项目阶段</th>
        <th>操作</th>
    </tr>
    </thead>
</table>
<form id="add" class="layui-form layui-fluid layui-col-md12 layui-row" action="" style="display:none;">
    <div style="width:700px;height:380px;display: block;margin: 9px auto;">
        <div id="dicid" class="layui-form-item layui-col-md10" value="">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" maxlength="255" required lay-verify="required" placeholder="请输入项目名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">负责人</label>
            <div class="layui-input-block">
               <!-- <input type="text" name="person" maxlength="255" required lay-verify="required" placeholder="请输入负责人姓名" autocomplete="off" class="layui-input"> -->
               <select lay-filter="person"   name="person" lay-verify="required" id="person">
               		<option value="">请选择负责人</option>
               </select>
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
		<div class="layui-form-item layui-col-md10">
			<label class="layui-form-label">开发人员</label>
			<div class="layui-input-block">
				<select lay-filter="developers"  multiple="multiple" name="developers" lay-verify="required" id="developers">
					<option value="">请选择开发人员</option>
				</select>
			</div>
		</div>
       	<div class="layui-form-item layui-col-md10">
       		<label class="layui-form-label">开发人员</label>
       		<div class="layui-input-block">
	       		<button type="button" class="layui-btn" id="uploadFiles">
	  				<i class="layui-icon">&#xe67c;</i>开发文档
				</button>
				<input type="hidden" value="" name="filesSrc"> 
			</div>
       	</div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
        		<input type="text" name="startTime" maxlength="255" readOnly="true" required lay-verify="required" placeholder="请输项目开始时间" autocomplete="off" class="layui-input" id="startTime" placeholder="yyyy-MM-dd">
            </div>
        </div>
       
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">截止时间</label>
            <div class="layui-input-block">
        		<input type="text" name="endTime" maxlength="255" readOnly="true" required lay-verify="required" placeholder="请输入项目截止时间" autocomplete="off" class="layui-input" id="endTime" placeholder="yyyy-MM-dd">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="text" name="weight" maxlength="255" required lay-verify="required\number" placeholder="请输入权重值" autocomplete="off" class="layui-input">
            </div>

        </div>
		 <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">研发状态</label>
            <div class="layui-input-block">
                <input id="researStatus_0" type="radio" name="researStatus" value="0" title="开发中"  checked >
                <input id="researStatus_1" type="radio" name="researStatus" value="1" title="已完成" >             
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">项目阶段</label>
            <div class="layui-input-block">
                <input type="text" name="researStage" maxlength="255" required lay-verify="required" placeholder="请输入项目所处阶段" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">项目描述</label>
            <div class="layui-input-block">
                <textarea name="describe" maxlength="255" placeholder="请输入项目信息，备注信息不得超过127个汉字" class="layui-textarea" id="describe"></textarea>
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
<script src="${basePath}/plugins/actuals/layui/layui.all.js"></script>
<script src="${basePath}/plugins/actuals/layui/layui.js"></script>
<script src="${basePath}/js/layui-mz-min.js"></script>
<script type="text/javascript" >
	
    var delItems = new Array();
    var isbtn = 0;
    var describe=null;
    var layedit = null;
	layui.use('laydate', function(){
  		var laydate = layui.laydate;
  		//常规用法
  		laydate.render({
    		elem: '#startTime'
  			});
  		laydate.render({
    		elem: '#endTime'
  			});
 		 });
    $(document).ready(function(){
    
    	layui.use("layedit",function(){
                		layedit=layui.layedit;
                		describe=layedit.build("describe",{
                			height: 180
                		});
              })
    
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
                data.name = $("#keyword").val();
                data.page_size = data.length;
                data.page_no = data.start/data.page_size+1;
                $.ajax({
                    type : "GET",
                    url : "${adminPath}/sys/actuals/list",
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
                  	"targets" : [8],
                  	"data" : null,
                  	"render" : function(data, type, row) {
									var id = '"' + row.id + '"';
									var html = "<button onclick='updateOp(this);' class='layui-btn  updateOpBtn layui-btn-sm' value="+id+">编辑</button>";
									html += "<button  onclick='deleteOp(this);' class='layui-btn layui-btn-danger layui-btn-sm' value="+id+">删除</button>&nbsp;&nbsp;";
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
                { data: 'name'},
                { data: 'person'},
                { data: function(res) {
                    return getMyDate(res.startTime);
                    }},
                { data: function(res) {
                    return getMyDate(res.endTime);
                    }},
                { data: 'weight'},
                { data: function(res) {
                	return transStatus(res.researStatus);
                	}
                },
                { data: 'researStage'},
            ],   
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量删除</button>" +
                "<div class='layui-input-inline' style='float: right; padding-right: 20px;'>" +
                " <label class='layui-form-label'>项目名称：</label>" +
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
            isbtn = 0;
            
            

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

	var fileURL;
	var fileIndex=0;
	var fileArray=new Array();
    layui.use(['form','layer','jquery','upload'],function() {
                    form = layui.form
                    ,layer = layui.layer
        			,$= layui.jquery
        			,upload=layui.upload;
        
        upload.render({
        	elem:'#uploadFiles',
        	url:'${adminPath}/sys/actuals/uploadFiles',
        	accept:'file',
        	multiple:true,
        	done:function(res,index,upload){
        		fileArray[fileIndex]=res.filesSrc[0];
        		fileIndex++;
        	},
        	allDone:function(obj){
        		layer.msg('上传成功', {icon: 1, time:1000});
        		alert(fileArray.join(","));
        		$("input[name='filesSrc']").val(fileArray.join(","));
        	}
        })

        form.on('submit(addSub)', function (data) {
            
            var developers=data.field.developers;
            
            
            var developersString="";
            for(var i=0;i<developers.length;i++){
                if(i==developers.length-1){
                    developersString +=  developers[developers.length-1];
                }else{
                    developersString += (developers[i]+",");
                }
            
            }
            data.field.developersString = developersString;
            if($.trim(data.field.person)!=""){
                data.field.deveNum = data.field.developers.length+1;
            }else{
                data.field.deveNum = data.field.developers.length
            }
            data.field.describe=layedit.getContent(describe);
            
            console.log(data);
            
            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
            
             
                $.ajax({
                    type:"post",
                    url: "${adminPath}/sys/actuals/",
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
            });
            $("#add").css("display","none");
            return false;
        });

        form.on('submit(updateSub)', function (data) {
            if($.trim(data.field.person)!=""){
                data.field.deveNum = data.field.developers.length+1;
            }else{
                data.field.deveNum = data.field.developers.length
            }
            data.field.describe=layedit.getContent(describe);
            
            var developers=data.field.developers;
            var developersString="";
            for(var i=0;i<developers.length;i++){
                if(i==developers.length-1){
                    developersString +=  developers[developers.length-1];
                }else{
                    developersString += (developers[i]+",");
                }
            
            }
            data.field.developersString = developersString;
            
            
            console.log(data);
            
            layer.confirm('您是否确定修改数据？', {
                btn: ['确定','取消']
            }, function(){
                $.ajax({
                    type:"put",
                    url: "${adminPath}/sys/actuals/"+$("#dicid").attr("value"),
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
            });
            return false;
        });

         getShow = function() {
            return layer.open({
                type: 1,
                title: '数据添加',
                shadeClose: true,
                shade: 0.8,
                anim:3,
                maxmin:true,
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
                 shade: 0.8,
                 anim:3,
                 maxmin:true,
                 scrollbar:false,
                 area: ['800px', "450px", '90%'],
                 content: $("#add"),
                 end: function(index, layero){
                     $("#add").css("display","none");
                 }
             });
         }


    })



	$.ajax({
        	url:"${adminPath}/sys/actuals/getOfficers",
        	type:"get",
        	success:function(data){
        		var html="";
				for(var i=0;i<data.length;i++){
					html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("#developers").append(html);
				$("#person").append(html);
				//form.render();
				

                
        	}
        });




    //   layUI 弹框修改
    
    function showAdd() {
        $("#subBtn").attr("lay-filter","addSub");
        $("input[name='name']").attr("value","");
        $("input[name='person']").attr("value","");
        $("input[name='startTime']").attr("value","");
        $("input[name='endTime']").attr("value","");
        $("input[name='weight']").attr("value","");
        $("#researStatus_0").attr("checked",true);
        $("input[name='researStage']").attr("value","");
        $("input[name='filesSrc']").attr("value","");
        $("select[name='developers']").val(false);
        $("select[name='person']").val(false);
       	layedit.setContent(describe,"");
       	form.render();
        return getShow();
    };
	
    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        $.ajax({
            type:'get',
            url:'/admin/sys/actuals/'+id,
            success:function(res) {
                if(res != null) {
                    $("#dicid").attr("value",res.id);
                    
                    console.log(res);
                    
                    $("input[name='name']").attr("value",res.name);
			        $("input[name='person']").attr("value",res.person);
			        $("input[name='startTime']").attr("value",getMyDate(res.startTime));
			        $("input[name='endTime']").attr("value",getMyDate(res.endTime));
			        $("input[name='weight']").attr("value",res.weight);
					
					
					
					$("#researStatus_"+res.researStatus).attr("checked",true);
			        $("input[name='researStage']").attr("value",res.researStage);
			        layedit.setContent(describe,res.describe);
			        
			        var idArr=stringToArray(res.developersString);
					console.log(idArr);					
					$("select[name='developers']").val(idArr);
			        //$("select[name='developers']").val(["1"]);
			        $("select[name='person']").val(res.person);
			        form.render();
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
                url:"${adminPath}/sys/actuals/"+id,
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

	//获取时间
	function getMyDate(time){
        var oDate = new Date(time),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth()+1,
                oDay = oDate.getDate(),

                oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间
        return oTime;
    };
    
    //补0操作,当时间数据小于10的时候，给该数据前面加一个0
    function getzf(num){
        if(parseInt(num) < 10){
            num = '0'+num;
        }
        return num;
    }

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
                url:"${adminPath}/sys/actuals/",
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
    
    function transStatus(status) {
		if(status == 1) {
			return "已完成";
		} else {
			return "开发中";
		}
	}
	
	function stringToArray(str){
		if(str==null){
			return false;
		}
		return str.split(",");
	}
	
	function arrayToString(arr){
		var str = "";
		for(var i=0;i<arr.length;i++){
			if(i==arr.length-1){
				str += arr[arr.length-1];
			}else{
				str += (arr[i] + ",");
			}
		}
	}

</script>
</html>