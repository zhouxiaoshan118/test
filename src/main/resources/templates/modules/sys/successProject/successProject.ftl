<#include "../../../common/all.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>成功项目案例</title>
    <link rel="stylesheet" href="${basePath}/plugins/bootstrap/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet" />
<#--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">-->
<#--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">-->
	<link rel="stylesheet" href="${basePath}/plugins/dataTables/jquery.dataTables.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" >
    <style type="text/css">
        body{padding:10px;border:2px solid #efefef;border-radius:5px;}
        div.dataTables_wrapper {
            width: 98%;
            margin: 0 auto;
        }
        .dataTables_paginate {text-align:right}
        .dataTables_info{padding:20px 0}
        .dataTables_length{padding:20px 5px}
    </style>
</head>
<body>
<table id="dictTable" class="table table-striped table-bordered display nowrap" cellspacing="0" width="98%" style="text-align:center">
    <thead>
    <tr>
        <th>id</th>
        <th>项目名称</th>
        <th>项目负责人</th>
        <th>应用领域</th>
        <th>技术选型</th>
        <th>操作</th>
    </tr>
    </thead>
</table>

<form id="add" class="layui-form layui-fluid" action="" style="display:none;">
    <div style="width:700px;height:380px;display: block;margin: 7px auto;">
        <div id="spid" class="layui-form-item layui-col-md8" value="">
            <label class="layui-form-label" style="width: 100px;">项目名称</label>
            <div class="layui-input-block ">
                <input type="text" name="name" maxlength="255" required lay-verify="required" placeholder="请输入项目名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md8 ">
            <label class="layui-form-label" style="width: 100px;">项目负责人</label>
            <div class="layui-input-block">
                <input type="text" name="chargeMan" maxlength="255" required lay-verify="required" placeholder="请输入项目负责人" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item layui-col-md8" >
            <label class="layui-form-label" style="width: 100px;">应用领域</label>
            <div class="layui-input-block">
                <select name="applicationField">
                    <option value="office" selected="false">办公</option>
                    <option value="govern" >政务</option>
                    <option value="travel">旅游</option>
                    <option value="ebusiness">电商</option>
                    <option value="city">城市</option>
                    <option value="edu">教育</option>
                </select>
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">PC前台</label>
            <div class="layui-input-block">
                <input id="isPCReception_1" type="radio" name="isPCReception" value="1" title="有" lay-filter="isPCReception">
                <input id="isPCReception_0" type="radio" name="isPCReception" value="0" title="无" lay-filter="isPCReception" checked>
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8" id="PCReceptionAddr" style="display:none">
            <label class="layui-form-label" style="width: 100px;">前台路径</label>
            <div class="layui-input-block">
                <input type="text" name="PCReceptionAddr" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">PC后台</label>
            <div class="layui-input-block">
                <input id="isPCBackstage_1" type="radio" name="isPCBackstage" value="1" title="有" lay-filter="isPCBackstage">
                <input id="isPCBackstage_0" type="radio" name="isPCBackstage" value="0" title="无" lay-filter="isPCBackstage" checked>
            </div>
        </div>
        
        
        
        <div class="layui-form-item layui-col-md8" id="addr" style="display:none">
            <label class="layui-form-label" style="width: 100px;">后台路径</label>
            <div class="layui-input-block">
                <input type="text" name="addr" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md10" id="roles" style="display:none" >
            <label class="layui-form-label" style="width: 100px;">后台角色</label>
            <div class="layui-input-block">
               <input type="checkbox" name="roles" value="generalUser" title="普通用户" lay-filter="roles" id="generalUserCB">
			   <input type="checkbox" name="roles" value="middleUser" title="中层用户" lay-filter="roles" id="middleUserCB">
			  <input type="checkbox" name="roles" value="rootUser" title="高层用户" lay-filter="roles" id="rootUserCB">
            </div>
        </div>
        
        
        <div class="layui-form-item layui-col-md8" id="generalUser" style="display:none">
            <label class="layui-form-label" style="width: 100px;">普通用户</label>
            <div class="layui-input-block">
            	用户名:
                <input type="text" name="generalUserName" class="layui-input">
              	 密码:
                <input type="password" name="generalUserPwd" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8" id="middleUser" style="display:none">
            <label class="layui-form-label" style="width: 100px;">中层用户</label>
            <div class="layui-input-block">
            	用户名:
                <input type="text" name="middleUserName" class="layui-input">
              	 密码:
                <input type="password" name="middleUserPwd" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8" id="rootUser" style="display:none">
            <label class="layui-form-label" style="width: 100px;">高层用户</label>
            <div class="layui-input-block">
            	用户名:
                <input type="text" name="rootUserName" class="layui-input">
              	 密码:
                <input type="password" name="rootUserPwd" class="layui-input">
                
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">微信端</label>
            <div class="layui-input-block">
                <input id="isWeixin_1" type="radio" name="isWeixin" value="1" title="有" lay-filter="isWeixin">
                <input id="isWeixin_0" type="radio" name="isWeixin" value="0" title="无" lay-filter="isWeixin" checked>
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8" style="display:none" id="uploadWXCodesBox">
            <label class="layui-form-label" style="width: 100px;">微信图片集</label>
            <div class="layui-input-block">
                <div class="layui-input-inline">
                    <button type="button"  class="layui-btn " id="uploadWXCodes">
                        <i class="layui-icon">&#xe67c;</i><span id="WXbtnName">上传图片</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-col-md12" style="display: none" id="WXCodes_view">
            <label class="layui-form-label"></label>
            <div class="layui-input-block">
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    预览图：
                    <div class="layui-upload-list" id="WXCodesView"></div>
                </blockquote>
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">手机端</label>
            <div class="layui-input-block">
                <input id="isPhone_1" type="radio" name="isPhone" value="1" title="有" lay-filter="isPhone">
                <input id="isPhone_0" type="radio" name="isPhone" value="0" title="无" checked lay-filter="isPhone">
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8" style="display:none" id="uploadAppCodesBox">
            <label class="layui-form-label" style="width: 100px;">APP图片集</label>
            <div class="layui-input-block">
                <div class="layui-input-inline">
                    <button type="button"  class="layui-btn " id="uploadAppCodes">
                        <i class="layui-icon">&#xe67c;</i><span id="AppbtnName">上传图片</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-col-md12" style="display: none" id="AppCodes_view">
            <label class="layui-form-label" style="width: 100px;"></label>
            <div class="layui-input-block">
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    预览图：
                    <div class="layui-upload-list" id="AppCodesView"></div>
                </blockquote>
            </div>
        </div>
        
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">项目价值</label>
            <div class="layui-input-block">
                <input type="text" name="price" maxlength="255" required lay-verify="required" placeholder="请输入项目价值（单位元）" autocomplete="off" class="layui-input">
            </div>

        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">项目大小</label>
            <div class="layui-input-block">
                <input type="text" name="appSize" maxlength="255" required lay-verify="required" placeholder="请输入项目大小" autocomplete="off" class="layui-input">
            </div>

        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">开始日期</label>
            <div class="layui-input-block">
                <input type="text" id="startDate" name="startDate"  required lay-verify="required" placeholder="开始日期" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">结束日期</label>
            <div class="layui-input-block">
                <input type="text" id="endDate" name="endDate" required lay-verify="required" placeholder="结束日期" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">总天数</label>
            <div class="layui-input-block">
                <input type="text" name="devDays" required lay-verify="required|number" placeholder="项目开发天数" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">技术选型</label>
            <div class="layui-input-block">
                <input type="text" name="techUsed" maxlength="255" required lay-verify="required" placeholder="请输入技术选型" autocomplete="off" class="layui-input">
            </div>

        </div>

       <#-- <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">线上地址</label>
            <div class="layui-input-block">
                <input type="text" name="addr" maxlength="255"  placeholder="请输入线上地址" autocomplete="off" class="layui-input">
            </div>

        </div> -->

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">推荐项目</label>
            <div class="layui-input-block">
                <input id="rdo1" type="radio" name="isRecommend" value="1" title="是" checked>
                <input id="rdo0" type="radio" name="isRecommend" value="0" title="否">
            </div>
        </div>

        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">列表图片</label>
            <div class="layui-input-block">
                <button type="button"  class="layui-btn" id="topicUpload">
                    <i class="layui-icon">&#xe67c;</i><span name="btnName">上传图片</span>
                </button>
            </div>
        </div>

        <div class="layui-form-item layui-col-md8" style="display: none" id="imgDiv">
            <label class="layui-form-label"></label>
            <div class="layui-input-block">
                <img src="" height="120px" width="160px" id="uploadTopicImg"/>
            </div>
        </div>
        <div class="layui-form-item layui-col-md8">
            <label class="layui-form-label" style="width: 100px;">轮播图片集</label>
            <div class="layui-input-block">
                <div class="layui-input-inline">
                    <button type="button" name="" class="layui-btn " id="uploadImgs">
                        <i class="layui-icon">&#xe67c;</i><span name="btnName">上传图片</span>
                    </button>

                </div>
                <div class="layui-form-mid layui-word-aux">最多上传5张图</div>
            </div>

        </div>
        <div class="layui-form-item layui-col-md12" style="display: none" id="images_view">
            <label class="layui-form-label"></label>
            <div class="layui-input-block">
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    预览图：
                    <div class="layui-upload-list" id="overview"></div>
                </blockquote>
            </div>
        </div>




        <div class="layui-form-item layui-form-text layui-col-md8">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="marks" maxlength="255" placeholder="请输入备注信息，备注信息不得超过127个汉字" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text ">
            <label class="layui-form-label" style="width: 100px;">项目简介</label>
            <div class="layui-input-block ">
                <div id="editor"></div>
                <#--<textarea name="info" placeholder="请输入简介" class="layui-textarea"></textarea>-->
            </div>
        </div>

        <div class="layui-form-item layui-col-md10">
            <div class="layui-input-block">
                <button id="subBtn" class="layui-btn" lay-submit lay-filter="addSub">立即提交</button>
                <button type="reset" id="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

</body>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="${basePath}/plugins/layui/layui.all.js"></script>
<script src="${basePath}/plugins/wangeditor/wangEditor.min.js"></script>
<script type="text/javascript" >


    var delItems = new Array();
    var isbtn = 0;
    var imagePaths = "";
    var flag = 1;
    var WXCodesViewPath = "";
    var AppCodesViewPath = "";


    $(document).ready(function(){

        // $("#uploadImgs").click(function() {
        //
        //
        // })
        
       	console.log($("input[name='isPCReception']"));
        
        
      
        

        $('#reset').click(function() {
            $("div[class='w-e-text']").text("");
        })

        dataTable = $('#dictTable').DataTable({
            "jQueryUI": true,
            "pagingType":"full_numbers",

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
            "scrollX": true,
            "ordering" : true,
            "bSort" : true, //排序功能
            "bInfo" : true,//页脚信息
            // "bAutoWidth" : true,//自动宽度
            ajax : function(data, callback, settings) {
                data.columns = null;
                // data.length = 10;
                // console.log(data);
                data.name = $("#keyword").val();
                data.page_size = data.length;
                data.page_no = data.start/data.page_size+1;
                $.ajax({
                    type : "GET",
                    url : "/front/sys/sp/list?time="+new Date().getTime(),
                    cache : false, //禁用缓存
                    data : data, //传入已封装的参数
                    dataType : "json",
                    success : function(res) {
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
                    "targets" : [5],
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
                { data: 'chargeMan'},
                { data: function(res) {
                        return getAppFeildName(res.applicationField);
                    }},
                { data: 'techUsed'}
            ],
        });
        $("div.toolbar").html("<button class='layui-btn' onclick='javascript:showAdd();'>添加数据</button>&nbsp;&nbsp;" +
                "<button class='layui-btn layui-btn-danger' onclick='javascript:deleteItems();' >批量删除</button>" +
                "<div class='layui-input-inline' style='float: right; padding-right: 20px;'>" +
                " <label class='layui-form-label'>项目名:</label>" +
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
    layui.use(['form','layer','upload'] ,function() {
                form = layui.form
                ,layer = layui.layer
                ,laydate = layui.laydate
                ,upload = layui.upload;
                var $=layui.jquery;

				form.on('radio(isPCReception)', function(data){
					if(data.value == "1"){
		        		$("#PCReceptionAddr").css("display","block");
		        	}else{
		        		$("#PCReceptionAddr").css("display","none");
		        	}
				}); 	

				form.on('radio(isPCBackstage)', function(data){
					if(data.value == "1"){
		        		$("#addr").css("display","block");
		        		$("#roles").css("display","block");
		        	}else{
		        		$("#addr").css("display","none");
		        		$("#roles").css("display","none");
		        	}
				}); 	
		
				form.on('checkbox(roles)', function(data){
				
					if((data.elem.checked)){
						if(data.value == "generalUser"){
							$("#generalUser").css("display","block");
						}else if(data.value == "middleUser"){
							$("#middleUser").css("display","block");
						}else if(data.value == "rootUser"){
							$("#rootUser").css("display","block");
						}
					}else{
						if(data.value == "generalUser"){
							$("#generalUser").css("display","none");
							$("input[name='generalUserName']").val("");
							$("input[name='generalUserPwd']").val("");
						}else if(data.value == "middleUser"){
							$("#middleUser").css("display","none");
							$("input[name='middleUserName']").val("");
							$("input[name='middleUserPwd']").val("");
						}else if(data.value == "rootUser"){
							$("#rootUser").css("display","none");
							$("input[name='rootUserName']").val("");
							$("input[name='rootUserPwd']").val("");
						}
					}
				
				});
				 
				
				form.on('radio(isWeixin)', function(data){
					if(data.value == "1"){
		        		$("#uploadWXCodesBox").css("display","block");
		        	}else{
		        		$("#uploadWXCodesBox").css("display","none");
		        	}
				}); 
				
				
				form.on('radio(isPhone)', function(data){
					if(data.value == "1"){
		        		$("#uploadAppCodesBox").css("display","block");
		        	}else{
		        		$("#uploadAppCodesBox").css("display","none");
		        	}
				}); 
					        
				        	

        //上传组件 upload 初始化
        var uploadTopic = upload.render({
            elem: '#topicUpload' //绑定元素
            ,url: '/front/sys/sp/imgupload' //上传接口
            ,accept:'images'
            ,done: function(res){
                if(res.code == 200) {
                    $("#uploadTopicImg").attr("src",res.imgSrcs[0]);
                    $("#imgDiv").fadeIn(2000);
                }
            }
            ,error: function(){
                layer.msg("上传错误");
            }
        });



        numofImgs = 0;
        var uploadImags = upload.render({
            elem: '#uploadImgs' //绑定元素
            ,url: '/front/sys/sp/imgupload' //上传接口
            ,accept:'images'
            ,multiple:true
            ,before: function(obj){
                if(flag==0) {
                    $('#overview').empty();
                    imagePaths = "";
                    flag = 1;
                }
                $("#images_view").fadeIn(2000);
                obj.preview(function(index, file, result){
                    $('#overview').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                });
            }
            ,done: function(res){

                if(numofImgs < 5) {
                    $("#uploadImgs").text("继续上传");
                    if(res.code == 200) {
                        if(imagePaths=="") {
                            imagePaths = res.imgSrcs[0];
                        } else {
                            imagePaths += ","+res.imgSrcs[0];
                        }
                    }
                }
                if(numofImgs == 4) {
                    $("#uploadImgs").toggleClass("layui-btn-disabled");
                }
                numofImgs += 1;

            }
            ,error: function(){
                layer.msg("上传错误");
            }
        });

		
		//上传微信二维码
		var uploadWXCodes = upload.render({
            elem: '#uploadWXCodes' //绑定元素
            ,url: '/front/sys/sp/imgupload' //上传接口
            ,accept:'images'
            ,multiple:true
            ,method:'post'
            ,before: function(obj){
            	if( $("#WXbtnName").text() == "重新上传"){
            		$('#WXCodesView').empty();
            		WXCodesViewPath = "";
            	}
                $("#WXCodes_view").fadeIn(2000);
                obj.preview(function(index, file, result){
                    $('#WXCodesView').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                });
            }
            ,done: function(res){
                    $("#WXbtnName").text("继续上传");
                    if(res.code == 200) {
                        if(WXCodesViewPath=="") {
                            WXCodesViewPath = res.imgSrcs[0];
                        } else {
                            WXCodesViewPath += ","+res.imgSrcs[0];
                        }
                    }
                }
            ,error: function(){
                layer.msg("上传错误");
            }
        });
        
        
		//上传App二维码
		var uploadAppCodes = upload.render({
            elem: '#uploadAppCodes' //绑定元素
            ,url: '/front/sys/sp/imgupload' //上传接口
            ,accept:'images'
            ,multiple:true
            ,method:'post'
            ,before: function(obj){
            	if( $("#AppbtnName").text() == "重新上传"){
            		$('#AppCodesView').empty();
            		AppCodesViewPath = "";
            	}
                $("#AppCodes_view").fadeIn(2000);
                obj.preview(function(index, file, result){
                    $('#AppCodesView').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                });
            }
            ,done: function(res){
                    $("#AppbtnName").text("继续上传");
                    if(res.code == 200) {
                        if(AppCodesViewPath=="") {
                            AppCodesViewPath = res.imgSrcs[0];
                        } else {
                            AppCodesViewPath += ","+res.imgSrcs[0];
                        }
                    }
                }
            ,error: function(){
                layer.msg("上传错误");
            }
        });
		

        laydate.render({
            "elem":'#startDate'
        });

        laydate.render({
           "elem":'#endDate'
        });

        form.on('submit(addSub)', function (data) {


            layer.confirm('您是否确认添加数据？', {
                btn: ['是','否'], //按钮
            }, function(){
                data.field.info = editor.txt.html();
                data.field.topicImage = $("#uploadTopicImg").attr("src");
                data.field.imagesPath = imagePaths;
                data.field.WXCodesViewPath = WXCodesViewPath;
                data.field.AppCodesViewPath = AppCodesViewPath;
                $.ajax({
                    type:"post",
                    url: "/front/sys/sp/addSuccessProject",
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('插入成功', {icon: 1, time:1000}, function () {
                            dataTable.ajax.reload();
                            layer.close(addlayer);
                        });

                    },
                    error: function() {
                        layer.close(addlayer);
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
                data.field.info = editor.txt.html();
                data.field.topicImage = $("#uploadTopicImg").attr("src");
                data.field.imagesPath = imagePaths;
                data.field.WXCodesViewPath = WXCodesViewPath;
                data.field.AppCodesViewPath = AppCodesViewPath;
                $.ajax({
                    type:"put",
                    url: "/front/sys/sp/"+$("#spid").attr("value")+"?time="+new Date().getTime(),
                    data: JSON.stringify(data.field),
                    contentType:"application/json",
                    success:function(res) {
                        layer.msg('修改成功', {icon: 1, time: 1000}, function() {
                            layer.close(updatelayer);
                            dataTable.ajax.reload();
                        });
                    },
                    error: function() {
                        layer.msg('内部错误，操作失败', {icon: 2, time:1000});
                        layer.close(updatelayer);
                    }
                })
            }, function(){
                layer.msg('修改已经取消', {icon: 0, time: 1000});
            });
            return false;
        });

        getShow = function() {
            return addlayer = layer.open({
                type: 1,
                title: '数据添加',
                shadeClose: true,
                anim: 1,
                shade: 0.3,
                maxmin:true,
                area: ['750px', "450px", '90%'],
                content: $("#add"),
                end: function(index, layero){
                    imagePaths="";
                    WXCodesViewPath="";
                    AppCodesViewPath="";
                    $("#add").css("display","none");
                    $("#AppCodes_view").css("display","none");
                    $("#AppCodesView").empty();
                    $("#WXCodes_view").css("display","none");
                    $("#WXCodesView").empty();
                    $("input[name='generalUserName']").attr("value","");
	                $("input[name='generalUserPwd']").attr("value","");
                    $("input[name='middleUserName']").attr("value","");
	                $("input[name='middleUserPwd']").attr("value","");
	                $("input[name='rootUserName']").attr("value","");
	                $("input[name='rootUserPwd']").attr("value","");
                    initRender();
                }
                
            });
        }

        getUpdate = function() {
            return updatelayer = layer.open({
                type: 1,
                title: '数据修改',
                shadeClose: true,
                anim:3,
                shade: 0.3,
                maxmin:true,
                area: ['650px', "450px", '90%'],
                content: $("#add"),
                end: function(index, layero){
                    imagePaths="";
                    WXCodesViewPath="";
                    AppCodesViewPath="";
                    $("#add").css("display","none");
                    $("input[name='roles']").removeAttr('checked');
                    $("#AppCodes_view").css("display","none");
                    $("#AppCodesView").empty();
                    $("#WXCodes_view").css("display","none");
                    $("#WXCodesView").empty();
                    $("input[name='generalUserName']").attr("value","");
	                $("input[name='generalUserPwd']").attr("value","");
                    $("input[name='middleUserName']").attr("value","");
	                $("input[name='middleUserPwd']").attr("value","");
	                $("input[name='rootUserName']").attr("value","");
	                $("input[name='rootUserPwd']").attr("value","");
                    initRender();
                }
            });
        }


    })

    function showAdd() {
        numofImgs = 0;
        initRender();
        $("span[name='btnName']").text("上传图片");
        $("#subBtn").attr("lay-filter","addSub");
        $("input[name='name']").attr("value","");
        $("input[name='chargeMan']").attr("value","");
        $("select[name='applicationFeild']").text("");
        $("input[name='price']").attr("value","");
        $("input[name='appSize']").attr("value","");
        $("input[name='startDate']").attr("value","");
        $("input[name='endDate']").attr("value","");
        $("input[name='devDays']").attr("value","");
        $("input[name='techUsed']").attr("value","");
        $("input[name='PCReceptionAddr']").attr("value","");
        $("input[name='generalUserName']").attr("value","");
        $("input[name='generalUserPwd']").attr("value","");
        $("input[name='middleUserName']").attr("value","");
        $("input[name='middleUserPwd']").attr("value","");
        $("input[name='rootUserName']").attr("value","");
        $("input[name='rootUserPwd']").attr("value","");
        WXCodesViewPath = "";
        $("#AppCodesView").empty();
        AppCodesViewPath = "";
        $("#imgDiv").fadeOut();
        $("input[name='addr']").attr("value","");
        $("div[class='w-e-text']").text("");
        $("textarea[name='marks']").text("");
        
        $("#isPCReception_0").attr("checked","checked");
        $("#isPCBackstage_0").attr("checked","checked");
        $("#isPhone_0").attr("checked","checked");
       	$("#isWeixin_0").attr("checked","checked");
       	
       	
       	
       	form.render();
       	
        return getShow();
    };

    function updateOp(e){
    <#-- 更新操作 -->
        isbtn = 1;
        var id = $(e).attr("value");
        $.ajax({
            type:'get',
            url:'/front/sys/sp/'+id+"?time="+new Date().getTime(),
            success:function(res) {
            	console.log(res);
                if(res != null) {
                    numofImgs = 0;
                    $("#imgDiv").fadeOut();
                    $("#images_view").fadeOut();
                    $("#overview").empty();
                    $("span[name='btnName']").text("重新上传");
                    $("#spid").attr("value",res.id);
                    $("input[name='name']").attr("value",res.name);
                    $("input[name='chargeMan']").attr("value",res.chargeMan);
                    $("input[name='price']").attr("value",res.price);
                    $("select[name='applicationField']").val(res.applicationField);
                    form.render('select');
                    $("input[name='appSize']").attr("value",res.appSize);
                    $("input[name='startDate']").attr("value",getMyDate(res.startDate));
                    $("input[name='endDate']").attr("value",getMyDate(res.endDate));
                    $("input[name='devDays']").attr("value",res.devDays);
                    if(res.topicImage != null) {
                        $("#uploadTopicImg").attr("src",res.topicImage);
                        $("#imgDiv").fadeIn();
                    }
                    
                    
                    
                    if(res.imagesPath != null) {
                        var images = splitImagePaths(res.imagesPath);
                        $("#images_view").fadeIn();
                        for(var i = 0; i < images.length; i++) {
                            $('#overview').append('<img src="'+ images[i] +'"  class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                        }
                        imagePaths = res.imagesPath;
                    }
                    $("input[name='techUsed']").attr("value",res.techUsed);
                    $("textarea[name='marks']").text(res.marks);
                    //这里拿到框中的东西
                    $("div[class='w-e-text']").empty();
                    $("div[class='w-e-text']").append(res.info);
                    // console.log(res.info);
                    $("#rdo"+res.isRecommend).attr("checked",true);
                    $("#isPCReception_"+res.isPCReception).attr("checked","checked");
                    if(res.isPCReception == 1){
                    	$("#PCReceptionAddr").css("display","block");
                    	$("input[name='PCReceptionAddr']").attr("value",res.PCReceptionAddr);
                    }else{
                    	$("input[name='PCReceptionAddr']").attr("value","");
                    }
                    
                    $("#isPCBackstage_"+res.isPCBackstage).attr("checked","checked");
                    
                    if(res.isPCBackstage == 1){
                    	$("#addr").css("display","block");
		        		$("#roles").css("display","block");
		        		$("input[name='addr']").attr("value",res.addr);
		        		if(res.generalUserID != null){
	                    	$("#generalUserCB").attr("checked","checked");
	                    	$("#generalUser").css("display","block");
	                    	$("input[name='generalUserName']").attr("value",res.generalUserName);
	                    	$("input[name='generalUserPwd']").attr("value",res.generalUserPwd);
	                    }
                    
	                    if(res.middleUserID != null){
	                    	$("#middleUserCB").attr("checked","checked");
	                    	$("#middleUser").css("display","block");
	                    	$("input[name='middleUserName']").attr("value",res.middleUserName);
	                    	$("input[name='middleUserPwd']").attr("value",res.middleUserPwd);
	                    }
                    
	                    if(res.rootUserID  != null){
	                    	$("#rootUserCB").attr("checked","checked");
	                    	$("#rootUser").css("display","block");
	                    	$("input[name='rootUserName']").attr("value",res.rootUserName);
	                    	$("input[name='rootUserPwd']").attr("value",res.rootUserPwd);
	                    }
                    }else{
                    	$("input[name='addr']").attr("value","");
                    }
                    
                    $("#isPhone_"+res.isPhone).attr("checked","checked");
                    
                     if(res.isPhone == 1){
                    	$("#uploadAppCodesBox").css("display","block");
                    	if( res.AppCodesViewPath == "" || res.AppCodesViewPath == null){
                    		AppCodesViewPath = "";
                    	}else{
                    		$("#AppCodes_view").css("display","block");
                    		AppCodesViewPath = res.AppCodesViewPath;
                    		$("#AppbtnName").text("重新上传");
                    		var paths = res.AppCodesViewPath.split(",");
                    		for(var i=0;i<paths.length;i++){
                    			$('#AppCodesView').append('<img src="'+ paths[i] +'"  class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                    		}
                    	}
                    }
                    
                    $("#isWeixin_"+res.isWeixin).attr("checked","checked");
                    
                    if(res.isWeixin == 1){
                    	$("#uploadWXCodesBox").css("display","block");
                    	if( res.WXCodesViewPath == "" || res.WXCodesViewPath == null){
                    		WXCodesViewPath = "";
                    	}else{
                    		WXCodesViewPath = res.WXCodesViewPath;
                    		$("#WXCodes_view").css("display","block");
                    		$("#WXbtnName").text("重新上传");
                    		var paths = res.WXCodesViewPath.split(",");
                    		for(var i=0;i<paths.length;i++){
                    			$('#WXCodesView').append('<img src="'+ paths[i] +'"  class="layui-upload-img" height="120px" width="160px" style="margin:0px 5px 5px 0px;">');
                    		}
                    	}
                    }
                    
                    form.render();
                    $("#subBtn").attr("lay-filter","updateSub");
                    flag = 0;

                }
            }
        });

        return getUpdate();

    };




    function splitImagePaths(images) {
        return images.split(",");
    }

    <#-- 删除操作 -->
    function deleteOp(e){
        isbtn = 1;
        var id = $(e).attr("value");
        layer.confirm('您是否确定删除数据？', {
            btn: ['是的','取消'] //按钮
        }, function(){
            $.ajax({
                type:"delete",
                url:"/front/sys/sp/"+id,
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
                url:"/front/sys/sp/",
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

    //补0操作,当时间数据小于10的时候，给该数据前面加一个0
    function getzf(num){
        if(parseInt(num) < 10){
            num = '0'+num;
        }
        return num;
    }

	//初始化渲染
	function initRender(){
		$("#PCReceptionAddr").css("display","none");
		$("#addr").css("display","none");
		$("#roles").css("display","none");
		$("#generalUser").css("display","none");
		$("#middleUser").css("display","none");
		$("#rootUser").css("display","none");
		$("#uploadWXCodesBox").css("display","none");
		$("#WXCodes_view").css("display","none");
		$("#uploadAppCodesBox").css("display","none");
		$("#AppCodes_view").css("display","none");
	}
	

    //字段过长自动截取
    function cutString(str) {
        if(str.length > 6) {
            return str.substring(0, 6)+"...";
        } else {
            return str;
        }
    }

    function getAppFeildName(name) {
        if(name == "ebusiness") {
            return "电商";
        } else if(name == "office") {
            return "办公";
        } else if(name == "govern") {
            return "政务";
        } else if(name == "edu") {
            return "教育";
        } else if(name == "travel") {
            return "旅游";
        } else if(name == "city") {
            return "城市";
        } 
    }
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'table',  // 表格
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor.create();



    // document.getElementById("test").innerHTML = "xxx";
</script>
</html>
