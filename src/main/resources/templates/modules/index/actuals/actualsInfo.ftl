<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>项目详情</title>
    <meta name="description" content="">
    <meta name="keywords" content=""> 
    <link href="" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/css/proinfo.css">
    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_bmgv5kod196q1tt9.css">
    <script type="text/javascript" src="${basePath}/plugins/actuals/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/plugins/actuals/Loading.js"></script>
    <script src="${basePath}/plugins/paging/js/paging.js"></script>
    <style type="text/css">
		* {
            padding: 0;
            margin: 0;
        }
        /*
          * 外面盒子样式---自己定义
          */

        .page_div {
            margin-top: 20px;
            margin-bottom: 20px;
            font-size: 15px;
            font-family: "microsoft yahei";
            color: #666666;
            margin-right: 10px;
            padding-left: 20px;
            box-sizing: border-box;
        }
        /*
         * 页数按钮样式
         */

        .page_div a {
            min-width: 30px;
            height: 28px;
            border: 1px solid #dce0e0!important;
            text-align: center;
            margin: 0 4px;
            cursor: pointer;
            line-height: 28px;
            color: #666666;
            font-size: 13px;
            display: inline-block;
        }

        #firstPage,
        #lastPage {
            width: 50px;
            color: #0073A9;
            border: 1px solid #0073A9!important;
        }

        #prePage,
        #nextPage {
            width: 70px;
            color: #0073A9;
            border: 1px solid #0073A9!important;
        }

        .page_div .current {
            background-color: #0073A9;
            border-color: #0073A9;
            color: #FFFFFF;
        }

        .totalPages {
            margin: 0 10px;
        }

        .totalPages span,
        .totalSize span {
            color: #0073A9;
            margin: 0 5px;
        }
        .showRepeat{
        	display:none
        }
    </style>
</head>
<body>
<div class="container-bg">
    <div class="layui-main">
        <div class="layui-row">
            <div class="content">
                <div class="pro-title">
                	<input type="hidden" value="${actuals.id}" id="actualsId" />
                    <h2 class="clearfix">${actuals.name}</h2>
                    <div class="pro-title-btn" style="padding-top: 10px;">
                        <button class="layui-btn layui-btn-xs layui-btn-danger"><i class="larry-icon larry-shanchu"></i><span>${actuals.researStage}</span></button>
                        <button class="layui-btn layui-btn-xs"><i class="larry-icon larry-shenheguanli"></i><span>${(actuals.researStatus=='0')?string('开发中', '已完成')}</span></button>
                    </div>
                </div>
                <div class="pro-content">
                    <div class="pro-info">
                        <p>${actuals.describe}</p>
                        <#--<p>${actuals.actualTarget}</p>-->
                    </div>
                    <div class="pro-start">
                        <p class="pro-time"><span class="pro-icon"></span>剩余<span class="num">${actuals.workTimes}</span>天<span class="workday">(${actuals.workTimes}个工作日)</span></p>
                        <p class="pro-data">
                            <span class="pro-datas">${actuals.startTime?string("yyyy-MM-dd")} ~ ${actuals.endTime?string("yyyy-MM-dd")}</span>
                            <span class="pro-icons"></span >
                            <span class="pro-remindme">提醒我</span>
                        </p>
                    </div>
                </div>
                <div class="pro-active">
                    <ul class="pro-chose">
                        <li><a href="javascript:;"><p class="num" id="textNum">${actuals.trendsNum}</p><p class="text">动态</p></a></li>
                        <#--<li><a href="javascript:;"><p class="num">0</p><p class="text">任务</p></a></li>
                        <li><a href="javascript:;"><p class="num">0</p><p class="text">活动</p></a></li>-->
                        <li><a href="javascript:;"><p class="num">2</p><p class="text">文档</p></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="pro-main">
                <div class="pro-search">
                    <textarea name="DAContent" id="DAContent" cols="30" rows="10" class="pro-txtarea"></textarea>
                    <button class="layui-btn layui-btn-sm" id="submitDailyArticle" style="background-color:#b9afaf;cursor:not-allowed" >发布日报</button>
                </div>
                <div id="pro-article-content">
					       
                </div>
            </div>
            <div class="pro-sidebar">
                <div class="sidebar-title">
                    <i class="larry-icon larry-jiaoseguanli"></i>
                    <span>成员(${actuals.deveNum})</span>
                </div>
                <div class="sidebar-role">
                	<#if person??>
	                    <div class="role-info">
	                        <div class="role-img">
	                            <img src="${person.image}" width="50" height="50">
	                        </div>
	                        <div class="role-infos">
	                            <span>${person.name}(负责人)</span>
	                            <span>${person.telephone}</span>
	                        </div>
	                    </div>
                    <#else>
                    	 <div class="role-info">
                    	 	<div class="role-img">
	                            <img src="/plugins/officer/img/bg01.jpg" width="50" height="50">
	                        </div>
	                        <div class="role-infos">
	                            <span>${person.name}</span>
	                        </div>
	                    </div>
                    </#if>
                    <#list linkOfficers as linkOfficer>
	                    <div class="role-info">
	                        <div class="role-img">
	                            <img src="${linkOfficer.image}" width="50" height="50">
	                        </div>
	                        <div class="role-infos">
	                            <span>${linkOfficer.name}</span>
	                            <span>${linkOfficer.telephone}</span>
	                        </div>
	                    </div>
	                </#list>    
                </div>
            </div>
        </div>
        <div class="pagebox" style=" padding-right: 25%;">
            <div id="page" class="page_div"></div>
        </div>
    </div>
</div>
<script>
    $(function(){
    	$(".container-bg").css("min-height",$(document).height());
    	ajaxPage(1);
    	$("#submitDailyArticle").prop("disabled",true);
    	
    	var flag=true;
    	
    	$("#pro-article-content").on('click','.repeat',function(){
    			var pageBox=$(this).parents(".pro-daypage");
				var repeat_user=$(this).find(".repeat_user").val();
				var repeat_id=$(this).find(".repeat_id").val();
				console.log(repeat_id);
				var article_id=$(this).find(".article_id").val();
				pageBox.find(".commentUserId").val(repeat_id);
				console.log(pageBox.find(".commentUserId"));
				console.log(pageBox.find(".commentUserId").val());
				
				pageBox.find(".level").val(1);
				pageBox.find(".answer-textarea").show();
				pageBox.find(".answer-textarea textarea").attr("placeholder","回复"+repeat_user+":");	    		
				pageBox.find(".answer-textarea textarea").focus();
				pageBox.find(".noneRepeat").remove();
	    })
	    
	    $("#pro-article-content").on('blur','.answer-textarea textarea',function(){
	    	if($(this).val()==""){
	    		$(this).parents(".answer-textarea").hide(200)
	    	}
	    })
	    
	    $("#pro-article-content").on('click','.list-answer',function(){
	    	var pageBox=$(this).parents(".pro-daypage");
	    	var time=$(this).parents(".time");
	    	pageBox.find(".answer-textarea").show();
	    	var tx=pageBox.find(".answer-textarea textarea");
	    	var commentUserId=time.find(".list-answer-id").val();
	    	var commentUserName=time.find(".list-answer-name").val();
	    	pageBox.find(".commentUserId").val(commentUserId);
	    	console.log(pageBox.find(".showAll"));
	    	pageBox.find(".level").val(2);
	    	tx.focus();
	    	tx.attr("placeholder","回复"+commentUserName+":");
	    });
	    
	    
	    $("#pro-article-content").on('click','.commentSubmit',function(){
	    	var commentContent = $(this).parents(".zh-btn").siblings("textarea").val();
	    	var pageBox=$(this).parents(".pro-daypage");
	    	var article_id=pageBox.find(".article_id").val();
	    	var comment_id=pageBox.find(".commentUserId").val();
	    	var level=pageBox.find(".level").val();
	    	console.log(comment_id);
	    	$.ajax({
	    		url:"/winui/actual/repeat",
	    		type:"post",
	    		data:{
	    			commentId:comment_id,
	    			commentContent:commentContent,
	    			articleId:article_id,
	    			level:level
	    		},
	    		success:function(data){
	    			var repeatHtml="";
	    			repeatHtml+="<div class='l'>";
					repeatHtml+="<img src='"+data.repeatUser.image+"'>"; 
					repeatHtml+="</div>";
			 		repeatHtml+="<div class='r'>";
			 		if(data.level==1){
			 			repeatHtml+="<div class='tx'><span class='name'><span class='name-text'>"+data.repeatUser.name+"</span><span style='color:#a2a2a2;margin:0 3px;font-size: 12px;'>回复</span>:</span>"+data.commentContent+"</div>";
			 		}else{
			 			repeatHtml+="<div class='tx'><span class='name'><span class='name-text'>"+data.repeatUser.name+"</span><span style='color:#a2a2a2;margin:0 3px;font-size: 12px;'>回复</span><span>"+data.commentUser.name+"</span>&nbsp;:</span>"+data.commentContent+"</div>";
			 		}
					repeatHtml+="<div class='time'>"+handleTime(data.beforeCommentTime)+"<a href='javascript:;' class='list-answer'>回复</a><input type='hidden' value='"+data.repeatUser.id+"' class='list-answer-id'><input type='hidden' value='"+data.repeatUser.name+"' class='list-answer-name'></div>";
					repeatHtml+="</div>";
					
					pageBox.find(".answer-pane-clid").append(repeatHtml);
					var showAll = pageBox.find(".showAll");
					if(showAll.length!=0){
						var total=pageBox.find(".total").val()
						total=parseInt(total)+1;
						pageBox.find(".total").val(total);
					}
					
					pageBox.find(".answer-textarea textarea").val("");
	    		}
	    	})
	    })
	    
	    
	    $("#pro-article-content").on('click','.more',function(){
	    	var repeatbox=$(this).parents(".pro-daypage");
	    	
	    	if(flag){
	    		var moreRepeatId = $(this).siblings(".article_id").val();
	    		var repeatContent = repeatbox.find(".answer-pane-clid");
	    		repeatContent.empty();
	    		repeatContent.css("display","block");
	    		ajaxRepeat(1,5,moreRepeatId,repeatContent,1);
	    		$(this).text("收起")
	    		flag=false;
	    	}else{
	    		var repeatContent = repeatbox.find(".answer-pane-clid");
	    		repeatContent.slideUp();
	    		$(this).text("更多")
	    		flag=true;
	    	}
	    })
	    
	    $("#pro-article-content").on('click','.showAll',function(){
    		var pageBox=$(this).parents(".pro-daypage");
    		var article_id=pageBox.find(".article_id").val();
    		var size=pageBox.find(".total").val();
    		var repeatContent = pageBox.find(".answer-pane-clid");
    		ajaxRepeat(1,size,article_id,repeatContent,2)
    	});
    	$("#DAContent").keyup(function(){
    		if($.trim($("#DAContent").val())==""){
    			$("#submitDailyArticle").css({
    				"backgroundColor":"#b9afaf",
    				"color":"#ACA899",
    				"cursor":"not-allowed"
    			})
    			$("#submitDailyArticle").prop("disabled",true);
    		
    		}else{
    			$("#submitDailyArticle").css({
    				"backgroundColor":"#009688",
    				"color":"#ACA899",
    				"cursor":"pointer"
    			})
    			$("#submitDailyArticle").attr("disabled",false);
    			
    		}
    		
    		
    	
    	
    	});
    	
    	$("#submitDailyArticle").click(function(){
    		if(!($.trim($("#DAContent").val())=="")){
				push();    		
    		}
    	})
    	
    	
 });
    
    function showAll(start,size,moreRepeatId,content){
		ajaxRepeat(start,size,moreRepeatId,content,2);    		
    }
    
    function push(){
    
    		var DailyArticle = new Object();
	    	var content=DailyArticle.content=$("#DAContent").val();
	    	
	    	
	    	
		    	$.ajax({
					url:"/winui/actual/sumbitDA/${actuals.id}",    	
		    		data:DailyArticle,
		    		type:"post",
		    		success:function(data){
		    			if(data.rootMan){
							var html = "";
		    				html+="<div class='pro-daypage'>";
			            	html+="<div class='pro-page-icon'>";
			                html+="<img src='"+data.officer.image+"' width='50' height='50'>";
			            	html+="</div>";
			            	html+="<input type='hidden' class='commentUserId' value=''>";
					        html+="<input type='hidden' class='level' value=''>";
			            	html+="<div class='pro-page-content'>";
			                html+="<div class='pro-page-role'><span class='page-role'>"+data.officer.name+"</span>说：</div>";
			                html+="<div class='pro-page-contents'>"+data.content+"</div>";
			                html+="<div class='pro-page-time'>"+handleTime(data.beforePushDate)+"前</div>";
			            	html+="</div>";
			            	
			            	html+="<div class='answer-pane'>";
							<!-- 二级回复 -->
							html+="<div class='answer-pane-clid'>";
							
							
							
							
							
							html+="</div>";
							<!--  回复框 -->
							html+="<div class='answer-textarea'>";
							html+="<textarea></textarea>";
							html+="<div class='zh-btn'><a href='javascript:;' class='commentSubmit'>发表</a></div>";
							html+="</div>";
							html+="</div>";
			            	
			            	
			            	html+="<div class='pro-page-answer'>";
			                html+="<span><a href='javascript:;' class='more'>更多</a><input type='hidden' value='"+data.id+"' class='article_id'><a href='javascript:void(0);' class='repeat'>回复<input type='hidden' value='"+data.officer.name+"'  class='repeat_user'><input type='hidden' value='"+data.officer.id+"'  class='repeat_id'><input type='hidden' name='repeat_id' class='repeat_id'></a></span>";
			            	html+="</div>";
			            	html+="</div>"; 
							
							$("#pro-article-content").prepend(html);
							$("#DAContent").val("");
							$('.loadingbox:visible').fadeOut(100);
							$("#submitDailyArticle").css({
			    				"backgroundColor":"#b9afaf",
			    				"color":"#ACA899",
			    				"cursor":"not-allowed"
			    			})
	    					$("#submitDailyArticle").prop("disabled",true);
	    					$(".ul-list").css("display","none");
	    					
	    					var num=$("#textNum").text();
	    					$("#textNum").text(parseInt(num)+1)		    				
			    		}else{
			    			$("#DAContent").val("");
			    			alert("暂无权限");
			    		}
		    		}
		    	})
    
    }
    
    function ajaxPage(num){
    	var actualsId = $("#actualsId").val();
    	var data = new Object();
		data.page_size = 2;
		data.page_no = num;
    	data.articleRoot = 1;
    	data.actualsId = actualsId;
		 $.ajax({
	        url: "${basePath}/admin/sys/dailyArticle/list",
	        type: "get",
	        data: data,
	        dataType: "json",
	        success: function(data) {
	            var result = data.result;
	            // html代码拼接
				var html = handleHtmls(result);
	            $("#pro-article-content").empty();
	            $("#pro-article-content").append(html);
	            $("#page").paging({
	                pageNo: num,
	                totalPage: ((data.total/data.size)==1)?(data.total/data.size):(Math.ceil(data.total/data.size)),
	                totalSize: data.total,
	                callback: function(num) {
	                    ajaxPage(num);
	                }
	            })
	       	}
	    })
    }
   	
   	
   	function ajaxRepeat(num,size,moreRepeatId,repeatContent,isflag){
   		var repeatHtml="";
   		var data = new Object();
	   	data.articleId = moreRepeatId;
		data.page_size = size;
		data.page_no = num;
		 $.ajax({
	        url: "${basePath}/admin/sys/comment/list",
	        type: "get",
	        data: data,
	        dataType: "json",
	        
	        success: function(data) {
	            var result = data.result;
	            console.log(data);
	            // html代码拼接
	            if(result.length>0){
	            	if(isflag==2){
	            		repeatContent.empty();
	            	}
	            	for(var i=0;i<result.length;i++){
	            		repeatHtml+="<div class='l'>";
						repeatHtml+="<img src='"+result[i].repeatUser.image+"'>"; 
						repeatHtml+="</div>";
				 		repeatHtml+="<div class='r'>";
				 		if(result[i].level==1){
				 			repeatHtml+="<div class='tx'><span class='name'><span class='name-text'>"+result[i].repeatUser.name+"</span><span style='color:#a2a2a2;margin:0 3px;font-size: 12px;'>回复</span>:</span>"+result[i].commentContent+"</div>";
				 		}else{
				 			repeatHtml+="<div class='tx'><span class='name'><span class='name-text'>"+result[i].repeatUser.name+"</span><span style='color:#a2a2a2;margin:0 3px;font-size: 12px;'>回复</span><span>"+result[i].commentUser.name+"</span>&nbsp;:</span>"+result[i].commentContent+"</div>";
				 		}
						repeatHtml+="<div class='time'>"+handleTime(result[i].beforeCommentTime)+"前<a href='javascript:;' class='list-answer'>回复</a><input type='hidden' value='"+result[i].repeatUser.id+"' class='list-answer-id'><input type='hidden' value='"+result[i].repeatUser.name+"' class='list-answer-name'></div>";
						repeatHtml+="</div>";
	            	}
	            	
	            	
	            	if(data.total>result.length&&isflag==1){
	            		repeatHtml+="<div class='showAll' style='cursor:pointer'><input type='hidden' value='"+data.total+"' class='total'>显示全部</div>"
	            	}
	            	repeatContent.append(repeatHtml);
	            }else{
	            	repeatHtml+="<div class='noneRepeat'>暂无评论，快去添加你的回复吧</div>"
	            	repeatContent.append(repeatHtml);
	            }
	            
	            
	            
	       	}
	    })
    }
   	
   	function handleHtmls(res) {
        var html = "";
        var result = ""
		
		console.log(res);
		
		if(res.length > 0) {
		
			$("#page").css("display","block");
		//    里面有数据
			for(var i = 0; i < res.length; i++) {
				html+="<div class='pro-daypage'>";
            	html+="<div class='pro-page-icon'>";
                html+="<img src='"+res[i].officer.image+"' width='50' height='50'>";
            	html+="</div>";
            	html+="<input type='hidden' class='commentUserId' value=''>";
		        html+="<input type='hidden' class='level' value=''>";
            	html+="<div class='pro-page-content'>";
                html+="<div class='pro-page-role'><span class='page-role'>"+res[i].officer.name+"</span>说：</div>";
                html+="<div class='pro-page-contents'>"+res[i].content+"</div>";
                html+="<div class='pro-page-time'>"+handleTime(res[i].beforePushDate)+"前</div>";
            	html+="</div>";
            	
            	html+="<div class='answer-pane'>";
				<!-- 二级回复 -->
				html+="<div class='answer-pane-clid'>";
				
				
				
				
				
				html+="</div>";
				<!--  回复框 -->
				html+="<div class='answer-textarea'>";
				html+="<textarea></textarea>";
				html+="<div class='zh-btn'><a href='javascript:;' class='commentSubmit'>发表</a></div>";
				html+="</div>";
				html+="</div>";
            	
            	
            	html+="<div class='pro-page-answer'>";
                html+="<span><a href='javascript:;' class='more'>更多</a><input type='hidden' value='"+res[i].id+"' class='article_id'><a href='javascript:void(0);' class='repeat'>回复<input type='hidden' value='"+res[i].officer.name+"'  class='repeat_user'><input type='hidden' value='"+res[i].officer.id+"'  class='repeat_id'><input type='hidden' name='repeat_id' class='repeat_id'></a></span>";
            	html+="</div>";
            	html+="</div>";
			}
		} else {
		//    没有数据
			
			html = "<li class='ul-list'><h1 style='text-align: center;'>暂无数据</h1></li>";
			$("#page").css("display","none");
		}
		
		return html;
    }
    
    
    function handleTime(time){
    	
    	if(time<60*1000){
    		return parseInt(time/1000) + "秒";
    	}else if(time>=60*1000&&time<60*60*1000){
    		return parseInt(time/(60*1000)) + "分";
    	}else if(time>=60*60*1000&&time<60*60*1000*24){
    		return parseInt(time/(60*60*1000))+"小时";
    	}else if(time>=60*60*1000*24&&time<60*60*1000*24*365){
    		return parseInt(time/(60*60*1000*24))+"天";
    	}else{
    		return parseInt(time/(60*60*1000*24*365))+"年";
    	}
    
    
    }
    
    
   	 
</script>
</body>
</html>
