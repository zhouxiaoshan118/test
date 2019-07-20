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
        <th>姓名</th>
        <th>职位</th>
        <th>所属小组</th>
        <th>手机号</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    </thead>
</table>

<form id="add" class="layui-form layui-fluid layui-col-md12" action=""	enctype="multipart/form-data" style="display:none;">
    <div style="width:700px;height:380px;display: block;margin: 9px auto;">
        <div id="dicid" class="layui-form-item layui-col-md8" value="">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" maxlength="255" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-block">
                <!--<button type="button" class="layui-btn" id="uploadImage">
  					<i class="layui-icon">&#xe67c;</i>上传图片
				</button>-->
				<button type="button" class="layui-btn" id="uploadImage">上传图片</button>
				<input type="hidden" name="image" value="" id="image_url" />
				<div class="layui-upload-list">
				<img class="layui-upload-img" id="demo1">
				<p id="demoText"></p>
  				</div>
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">在职状态</label>
            <div class="layui-input-block">
                <#--<input type="text" name="value" maxlength="255" required lay-verify="required" placeholder="请输入属性值" autocomplete="off" class="layui-input">-->
                
                <input type="radio" name="jobState" value="0" title="离职" id="jobState_0">
                <input type="radio" name="jobState" value="1" title="试用" checked id="jobState_1">
                <input type="radio" name="jobState" value="2" title="正式" id="jobState_2">
                
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <#--<input type="text" name="value" maxlength="255" required lay-verify="required" placeholder="请输入属性值" autocomplete="off" class="layui-input">-->
                <input type="radio" name="sex" value="0" title="女" id="sex_0">
                <input type="radio" name="sex" value="1" title="男" checked id="sex_1">                
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">职位</label>
            <div class="layui-input-block">
                <#--<input type="text" name="value" maxlength="255" required lay-verify="required" placeholder="请输入属性值" autocomplete="off" class="layui-input">-->
                <input type="text" name="job" maxlength="255" required lay-verify="required" placeholder="请输入职位" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">所属小组</label>
            <div class="layui-input-block">
                <#--<input type="text" name="value" maxlength="255" required lay-verify="required" placeholder="请输入属性值" autocomplete="off" class="layui-input">-->
                <input type="text" name="department" maxlength="255" required lay-verify="required" placeholder="请输入小组名" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">权重</label>
            <div class="layui-input-block">
                <input type="text" name="weight" maxlength="255" required lay-verify="required|number" placeholder="请输入权重值" autocomplete="off" class="layui-input">
            </div>
        </div>
		<div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="telephone" maxlength="255" required lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" maxlength="255" required lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">技能</label>
            <div class="layui-input-block">
                <input type="text" name="skill" maxlength="255" required lay-verify="required" placeholder="请输入技能" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">住址</label>
            <div class="layui-input-block">
                <input type="text" name="address" maxlength="255" required lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">爱好</label>
            <div class="layui-input-block">
                <input type="text" name="hobby" maxlength="255" required lay-verify="required" placeholder="请输入爱好" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <!--<div class="layui-form-item layui-col-md8">
            <label class="layui-form-label">删除</label>
            <div class="layui-input-block">
                
                <input type="radio" name="delState" value="0" title="是">
                <input type="radio" name="delState" value="1" title="否" checked>
            </div>
        </div> -->
        
        <!-- <div class="layui-form-item layui-form-text layui-col-md8">
            <label class="layui-form-label">个人简介</label>
            <div class="layui-input-block">
                <textarea name="discription"  placeholder="请输入个人简介" class="layui-textarea"></textarea>
            </div>
        </div> -->
        
         <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">教育背景</label>
            <div class="layui-input-block">
                <textarea name="educational"  placeholder="请输入教育背景" class="layui-textarea" id="layui-textarea_educational"
                 ></textarea>
                <script>
                	
                </script>
            </div>
        </div>
        
         <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">工作经历</label>
            <div class="layui-input-block">
                <textarea name="work"  placeholder="请输入工作经历" class="layui-textarea" id="layui-textarea_work"></textarea>
            </div>
            <script>
                	
              </script>
        </div>
        
        <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">工作经验</label>
            <div class="layui-input-block">
                <textarea name="experience"  placeholder="请输入工作经验" class="layui-textarea" id="layui-textarea_experience"></textarea>
            </div>
        </div>
        
        <div class="layui-form-item layui-form-text layui-col-md12">
            <label class="layui-form-label">自我评价</label>
            <div class="layui-input-block">
                <textarea name="evaluate"  placeholder="请输入自我评价" class="layui-textarea" id="layui-textarea_evaluate"></textarea>
            </div>
        </div>
        
        
        
        <div class="layui-form-item layui-col-md8">
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
<script type="text/javascript" th:inline="javascript">


    var delItems = new Array();
    var isbtn = 0;
	var educational=null;
	var layedit=null;
	var work=null;
	var experience=null;
	var evaluate=null;
    $(document).ready(function(){
    	
    	
    	layui.use("layedit",function(){
                		layedit=layui.layedit;
                		educational=layedit.build("layui-textarea_educational",{
                			height: 180
                		});
                		work=layedit.build("layui-textarea_work",{
                			height: 180
                		});
                		experience=layedit.build("layui-textarea_experience",{
                			height: 180
                		});
                		evaluate=layedit.build("layui-textarea_evaluate",{
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
                    url : "${adminPath}/sys/officer/list",
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
                { data: 'name'},
                { data: 'job'},
                { data: 'department'},
                { data: 'telephone'},
                { data: 'email'}
            ],   
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量删除</button>" +
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

    var getShow;

    var getUpdate;

    //   layUI 弹框修改
    layui.use(['form','layer'] ,function() {
    	var $=layui.jquery;
        form = layui.form
        ,layer = layui.layer;
		<!-- 添加数据 -->
        form.on('submit(addSub)', function (data) {
        	data.field.educational=layedit.getContent(educational);
        	data.field.work=layedit.getContent(work);
        	data.field.experience=layedit.getContent(experience);
        	data.field.evaluate=layedit.getContent(evaluate);
        	
        
            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
                $.ajax({
                    type:"post",
                    url: "${adminPath}/sys/officer",
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
        	
        	data.field.educational=layedit.getContent(educational);
        	data.field.work=layedit.getContent(work);
        	data.field.experience=layedit.getContent(experience);
        	data.field.evaluate=layedit.getContent(evaluate);
        
            layer.confirm('您是否确定修改数据？', {
                btn: ['确定','取消']
            }, function(){
                $.ajax({
                    type:"put",
                    url: "${adminPath}/sys/officer/"+$("#dicid").attr("value"),
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

    function showAdd() {
    				$("#subBtn").attr("lay-filter","addSub");
                    $("input[name='name']").attr("value","");
                    $("input[name='image']").attr("value","");
                    $("input[name='job']").attr("value","");
                    
                    $("#jobState_1").attr("checked",true);
                    $("#sex_1").attr("checked",true);
                    $("#demo1").attr({
                    	"src":""
                    });
                    $("#demo1").css({
                    	"display":"none"
                    });
                    $("input[name='department']").attr("value","");
                    $("input[name='weight']").attr("value","");
                    $("input[name='telephone']").attr("value","");
                    $("input[name='email']").attr("value","");
                    $("input[name='skill']").attr("value","");
                    $("input[name='hobby']").attr("value","");
                    $("input[name='address']").attr("value","");
                    //$("textarea[name='discription']").text("");
                    
                   	
                   	
                    
                    layedit.setContent(educational,"");
                    layedit.setContent(work,"");
                    layedit.setContent(experience,"");
                    layedit.setContent(evaluate,"");
                    
                    form.render();
                    
       				 return getShow();
    };

    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        $.ajax({
            type:'get',
            url:'/admin/sys/officer/'+id,
            success:function(res) {
            	console.log(res);
                if(res != null) {
                    $("#dicid").attr("value",res.id);
                    $("input[name='name']").attr("value",res.name);
                    $("input[name='image']").attr("value",res.image);
                  	$("#demo1").attr({
                  		"src":res.image,
                  		"width":"120px",
                  		"height":"160px"
                  	});
                    $("#demo1").css({
                    	"dispaly":"block",
                    })
                   // $("input[name='jobState']").attr("value",res.jobState);
                    $("#jobState_"+res.jobState).attr("checked",true);
                   // $("input[name='sex']").attr("value",res.sex);
                   $("#sex_"+res.sex).attr("checked",true);
                   
                    $("input[name='job']").attr("value",res.job);
                    $("input[name='department']").attr("value",res.department);
                    $("input[name='weight']").attr("value",res.weight);
                    $("input[name='telephone']").attr("value",res.telephone);
                    $("input[name='email']").attr("value",res.email);
                    $("input[name='skill']").attr("value",res.skill);
                    $("input[name='hobby']").attr("value",res.hobby);
                    $("input[name='address']").attr("value",res.address);
                    $("input[name='delState']").attr("value",res.delState);
                    //$("textarea[name='discription']").text(res.discription);
                    
                    layedit.setContent(educational,res.educational);
                    layedit.setContent(work,res.work);
                    layedit.setContent(experience,res.experience);
                    layedit.setContent(evaluate,res.evaluate);
                    
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
                url:"${adminPath}/sys/officer/"+id,
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
                url:"${adminPath}/sys/officer/",
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
    
		layui.use('upload', function(){
		var $=layui.jquery;
  		var upload = layui.upload;
	  	//执行实例
		  var uploadInst = upload.render({
		    elem: '#uploadImage' //绑定元素
		    ,url: '${adminPath}/sys/officer/upload' //上传接口
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
