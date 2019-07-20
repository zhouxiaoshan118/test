<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>项目列表</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/actuals/css/prolist.css">
    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_bmgv5kod196q1tt9.css">
    <script type="text/javascript" src="${basePath}/plugins/actuals/js/jquery-2.1.4.min.js"></script>
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
        .pro-content p{
        	display:inline-block;
        }
        .clearfix::after{
        	
        }
    </style>	
</head>
<body>
<div class="container-bg">
    <div class="layui-main">
        <div class="layui-container">
            <div class="layui-row">
                <div class="search">
                    项目名称：<input class="layui-text layui-radius" type="text" name="keyWord" id="keyform">
                    <button class="layui-btn layui-btn-sm" id="searchBtn"><i class="larry-icon larry-chaxun"></i><span>搜索</span></button>
                </div>
            </div>
             <div class="layui-row"  id="actualsContent">
           
           </div>
          <div class="pagebox">
			<div id="page" class="page_div"></div>          
          </div>
          
          <script>
				$(function(){
					ajaxPage(1,null);
					$("#searchBtn").click(function(){
                		var keyWord=$("#keyform").val();
                		ajaxPage(1,keyWord);
                	})
				})     
				function ajaxPage(num,name) {
		            var data = new Object();
		            data.page_size = 5;
		            data.page_no = num;
		            data.name=name;
		            $.ajax({
		                url: "${basePath}/admin/sys/actuals/list",
		                type: "get",
		                data: data,
		                dataType: "json",
		                success: function(data) {
		                    console.log(data);
		                    var result = data.result;
		                    // html代码拼接
							var html = handleHtmls(result);
		                    $("#actualsContent").empty();
		                    $("#actualsContent").append(html);
		                    $("#page").paging({
		                        pageNo: num,
		                        totalPage: ((data.total/data.size)==1)?(data.total/data.size):(Math.ceil(data.total/data.size)),
		                        totalSize: data.total,
		                        callback: function(num) {
		                            console.log(num);
		                            ajaxPage(num);
		                        }
		                    })
		                }
		            })
		        }
		        
		         function handleHtmls(res) {
			            var html = "";
			            var result = ""
						if(res.length > 0) {
						
							
						//    里面有数据
							for(var i = 0; i < res.length; i++) {
							
								var state = getState(res[i].researStatus);
								var restDays=getRestDay(res[i].workTimes);
							
							   html += "<div class='layui-row' style='margin-bottom:20px'>";
					           html += "<div class='content'>";
					           html +=  "<div class='pro-title clearfix'>";
					           html +=  "<img src='${basePath}/plugins/actuals/img/bg01.jpg' height='50' width='50'>";
					           html += "<h2 style='font-size: 18px'>"+res[i].name+"</h2>";
					           html += "<div class='pro-title-btn'>";
					           html += "<button class='layui-btn layui-btn-xs layui-btn-danger'><i class='larry-icon larry-shanchu'></i><span>"+res[i].researStage+"</span></button>";
					           html += "<button class='layui-btn layui-btn-xs'><i class='larry-icon larry-shenheguanli'></i><span>"+state+"</span></button>";
					           html += "</div>";
					           html += "</div>";
					           html += "<div class='pro-content' style='cursor:pointer' onclick="+"window.open('${basePath}/winui/actualsInfo/"+res[i].id+"')"+">";
					           html += "<p class='pro-description'>项目简介："+res[i].describe+"</p>";
					                        <#--<p>项目目标：${at.actualTarget}</p>-->
					            html += "</div>";
					            html += "<div class='pro-start'>";
					            html += "<span class='pro-icon'></span>";
					            html += "<p class='pro-time'>剩余<span>"+restDays+"</span>天</p>";
					            html += "<ul class='pro-info'>";
					            html += "<li><a href='javascript:;'><p class='pro-icons'></p><p class='text'>提醒我</p></a></li>";
					            html += "<li><a href='javascript:;'><p class='num'>"+res[i].trendsNum+"</p><p class='text'>动态</p></a></li>";
					            html += "<li><a href='javascript:;'><p class='num'>"+res[i].deveNum+"</p><p class='text'>成员</p></a></li>";
					            html += "</ul>";
					            html +="</div>";
					            html +="</div>";
					            html +="</div>";
							}
						} else {
						//    没有数据
							html = "<li class='ul-list'><h1 style='text-align: center;'>暂无数据</h1></li>";
						}
						return html;
			        }
			      
			     function getState(state){
			     
			     		return state=='0'?"开发中":"已完成";
			     
			     }     
			     
			     function getRestDay(workTime){
			     
			     	return ((workTime)/(1000*60*60*24)).toFixed(2);
			     
			     }   
          </script>
            
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/plugins/actuals/layui/layui.js"></script>
</html>
