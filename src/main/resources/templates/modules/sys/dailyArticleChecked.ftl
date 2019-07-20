<#include "../../common/all.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>日常报告</title>
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
        <th>标题</th>
        <th>创建者</th>
       	<th>创建时间</th>
       	<th>权重</th>
        <th>摘要</th>
        <th>日报内容</th>
        <th>是否审核</th>
        <th>操作</th>
    </tr>
    </thead>
</table>

<form id="add" class="layui-form layui-fluid layui-col-md12" action="" style="display:none;">
    <div style="width:700px;height:380px;display: block;margin: 9px auto;">
        <div id="dicid" class="layui-form-item layui-col-md10" value="">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" maxlength="255" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md10">
        
            <label class="layui-form-label">创建者</label>
            <div class="layui-input-block">
                <input type="text" name="pushPerson" maxlength="255" required lay-verify="required" placeholder="请输入创建者" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
  		<div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-block">
                <input type="text" id="createDate" name="pushDate" maxlength="255" readOnly="true" required lay-verify="required" placeholder="请输入创建时间" autocomplete="off" class="layui-input">
            </div>
        </div>
		<div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="text" name="weight" maxlength="255" required lay-verify="required" placeholder="请输入摘要" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <!-- 日报图片上传  -->
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-block">
				<button type="button" class="layui-btn" id="uploadImage">上传图片</button>
				<input type="hidden" name="image" value="" id="image_url" />
				<div class="layui-upload-list">
				<img class="layui-upload-img" id="demo1">
				<p id="demoText"></p>
  				</div>
        	</div>
        </div>
        <div class="layui-form-item layui-col-md10">
            <label class="layui-form-label">摘要</label>
            <div class="layui-input-block">
                <input type="text" name="description" maxlength="255" required lay-verify="required" placeholder="请输入摘要" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">日报内容</label>
            <div class="layui-input-block">
                <textarea name="content" maxlength="255" placeholder="请输入日报内容，日报内容信息不得超过255个汉字" class="layui-textarea" id="content"></textarea>
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
	var content = null;
	var layedit = null;
	
    $(document).ready(function(){
    
    	layui.use("layedit",function(){
                		layedit=layui.layedit;
                		content=layedit.build("content",{
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
                data.title = $("#keyword").val();
                data.page_size = data.length;
                data.page_no = data.start/data.page_size+1;
                $.ajax({
                    type : "GET",
                    url : "${adminPath}/sys/dailyArticle/list",
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
                       	alert("查询失败");
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
									var html = "<button onclick='javascript:updateOp(this);' class='layui-btn  updateOpBtn layui-btn-sm' value="+id+">编辑</button>";
									html += "<button  onclick='javascript:deleteOp(this);' class='layui-btn layui-btn-danger layui-btn-sm' value="+id+">删除</button>&nbsp;";
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
                { data: function(res){
                		return cutString(res.title);
                	}	
                },
                { data: 'pushPerson'},
                { data: function(res) {
                     return getMyDate(res.pushDate);
                    }},
                { data: function(res){
                	return transWeight(res.weight);
                }},
                { data: function(res){
                		return cutString(res.description);
                	}	
                },
                { data: function(res){
                	return cutString(res.content);
                	}	
                },
                { data: function(res){
                	return isAudit(res.isAudit);
                }}
            ],   
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量删除</button>" +
                "<div class='layui-input-inline' style='float: right; padding-right: 20px;'>" +
                " <label class='layui-form-label'>标题：</label>" +
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
        ,layer = layui.layer
        ,laydate = layui.laydate;

        laydate.render({
            "elem":'#createDate'
        });

        form.on('submit(addSub)', function (data) {

			data.field.content = layedit.getContent(content);
			
            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
                $.ajax({
                    type:"post",
                    url: "${adminPath}/sys/dailyArticleChecked",
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('插入成功', {icon: 1, time:1000}, function(){
                        	layer.close(addLayer);
                        	dataTable.ajax.reload();
                        });
                        
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
        
        	data.field.content = layedit.getContent(content);
        	
            layer.confirm('您是否确定修改数据？', {
                btn: ['确定','取消']
            }, function(){
                $.ajax({
                    type:"put",
                    url: "${adminPath}/sys/dailyArticleChecked/"+$("#dicid").attr("value"),
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('修改成功', {icon: 1, time: 1000},function(){
                        	layer.close(updateLayer);
                        	dataTable.ajax.reload();
                        });
                        
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
            return addLayer = layer.open({
                type: 1,
                title: '数据添加',
                shadeClose: true,
                shade: 0.3,
                maxmin:true,
                anim:1,
                area: ['800px', "450px", '90%'],
                content: $("#add"),
                end: function(index, layero){
                    $("#add").css("display","none");
                }
            });
         }

         getUpdate = function() {
             return updateLayer = layer.open({
                 type: 1,
                 title: '数据修改',
                 shadeClose: true,
                 shade: 0.3,
                 maxmin:true,
                 anim:3,
                 scrollbar:false,
                 area: ['800px', "450px", '90%'],
                 content: $("#add"),
                 end: function(index, layero){
                     $("#add").css("display","none");
                 }
             });
         }


    })
    
    //截取字符串
    function cutString(str) {
    	
    	if(str==null){
    		return "暂无内容";
    	}
    
        if(str.length > 10) {
            return str.substring(0, 10)+"...";
        } else {
            return str;
        }
    }
    

    function showAdd() {
        $("#subBtn").attr("lay-filter","addSub");
        $("input[name='title']").attr("value","");
        $("input[name='pushPerson']").attr("value","");
        $("input[name='pushDate']").attr("value","");
        $("input[name='weight']").attr("value","");
        
        $("input[name='image']").attr("value","");
        $("#demo1").attr({
        	"src":""	
        })
        $("#demo1").css({
        	"display":"none"
        })
        
        $("input[name='description']").attr("value","");
        //$("textarea[name='content']").text("");
        layedit.setContent(content,"");
        return getShow();
    };

    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        $.ajax({
            type:'get',
            url:'/admin/sys/dailyArticleChecked/'+id,
            success:function(res) {
                if(res != null) {
                    $("#dicid").attr("value",res.id);
                    $("input[name='title']").attr("value",res.title);
                    $("input[name='pushPerson']").attr("value",res.pushPerson);
                    $("input[name='pushDate']").attr("value",getMyDate(res.pushDate));
                    $("input[name='weight']").attr("value",res.weight);
                    $("input[name='image']").attr("value",res.image);
                    $("#demo1").attr({
                    	"width":"120px",
                    	"height":"160px",
                    	"src":res.image
                    })
                    $("#demo1").css({
                    	"display":"block",
                    })
                    $("input[name='description']").attr("value",res.description);
                    //$("textarea[name='content']").text(res.content);
                    layedit.setContent(content,res.content);
                    
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
                url:"${adminPath}/sys/dailyArticleChecked/"+id,
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
                url:"${adminPath}/sys/dailyArticleChecked/",
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



    function getMyDate(time){
        var oDate = new Date(time),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth()+1,
                oDay = oDate.getDate(),

                oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间
        return oTime;
    };
    
    function isAudit(audit){
		if(audit == '0'){
			return "未审核";
		} else {
			return "已审核";
		}
	};
	
	function transWeight(weight){
		if(weight==null){
			return 0;
		}else{
			return weight;
		}
	}

    //补0操作,当时间数据小于10的时候，给该数据前面加一个0
    function getzf(num){
        if(parseInt(num) < 10){
            num = '0'+num;
        }
        return num;
    }
    
 		layui.use('upload', function(){
		var $=layui.jquery;
  		var upload = layui.upload;
	  	//执行实例
		  var uploadInst = upload.render({
		    elem: '#uploadImage' //绑定元素
		    ,url: '${adminPath}/sys/dailyArticleChecked/upload' //上传接口
		    ,auto:true
		    ,accept:'images'
		    ,method:'post'
		    ,before: function(obj){
		      //预读本地文件示例，不支持ie8
		      obj.preview(function(index, file, result){
		      	$('#demo1').css({
		      		display:'block',
		      		width:'120px',
		      		height:'160px'
		      	})
		        $('#demo1').attr('src', result); //图片链接（base64）
		      });
		    }
		    ,done: function(res){
		    	if(res.code==200){
		    		$("#image_url").val(res.imgSrcs[0]);
		    	}	
		      //上传完毕回调
		    }
		    ,error: function(){
		      //请求异常回调
		    }
	  	});
});   

</script>
</html>
