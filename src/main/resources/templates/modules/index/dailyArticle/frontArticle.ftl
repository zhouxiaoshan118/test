<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>列表</title>
	<link rel="stylesheet" href="${basePath}/plugins/dailyarticle/css/animate.min.css">
	<link rel="stylesheet" href="${basePath}/plugins/dailyarticle/css/jquery.gDialog.css">
	<link rel="stylesheet" href="${basePath}/plugins/dailyarticle/iconfont/css/iconfont.css">
	<link rel="stylesheet" href="${basePath}/plugins/dailyarticle/css/index.css">
	<script src="${basePath}/plugins/dailyarticle/js/jquery-1.11.3.min.js"></script>
	<script src="${basePath}/plugins/paging/js/paging.js"></script>
	<script src="${basePath}/js/wangEditor.min.js"></script>
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
            background:#fff;
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
        .clearfix::after{
        	content:"";
        	display:block;
        	clear:both;
        }
    </style>
</head>
<body>
	<div class="container container-bg">
		<div class="content-list clearfix">
			<div class="list-left">
				<div class="left-title">
					<h3>日常报告</h3>
				</div>
				<ul id="articleContent">
				</ul>
				<div class="pagebox" >
				 	<div id="page" class="page_div"></div>
				 </div>
			</div>
			<div class="list-right">
				<div class="left-title">
					<h3></h3>
				</div>
				<div class="right-top-box">
					<div class="right-erimg">
                        <img src="${basePath}/plugins/successproject/img/qrcode.png" alt="二维码" class="erimg">
                        <a href="javascript:;" class="fb-article">发布日报</a>
                    </div>
                    <div class="erimg-info">
                        <ul>
                            <li><i class="iconfont iconfont-qq1"></i></li>
                            <li><i class="iconfont iconfont-wechat1"></i></li>
                            <li><i class="iconfont iconfont-email1"></i></li>
                            <li><i class="iconfont iconfont-phone1"></i></li>
                        </ul>
                    </div>
				</div>
				
				 
				
			</div>
		</div>
	</div>
	<script src="${basePath}/plugins/dailyarticle/js/jquery.gDialog.js"></script>
	<script>
		window.editor;
		$(function(){
			$(".container").css("min-height",$(window).height());
			
			ajaxPage(1);
		})
		
		function ajaxPage(num) {
		            var data = new Object();
		            data.page_size = 5;
		            data.page_no = num;
		            $.ajax({
		                url: "${basePath}/admin/sys/dailyArticle/list",
		                type: "get",
		                data: data,
		                dataType: "json",
		                success: function(data) {
		                    console.log(data);
		                    var result = data.result;
		                    // html代码拼接
							var html = handleHtmls(result);
		                    $("#articleContent").empty();
		                    $("#articleContent").append(html);
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
								
							html += "<li class='ul-list' style='position:relative'>";
							html += "<div class='imgbox' style='border:1px solid #ccc;border-radius: 5px;'>";
							html += "<img src=${basePath}/plugins/dailyArticle/img/xq"+res[i].week +".png alt='' width='250px' height='150px'>";
							html += "</div>";
							if(res[i].isAudit==1){
								html += "<img src='${basePath}/plugins/dailyArticle/img/pass.png' style='position:absolute;top:100px;right:20px' width='100px' height='50px'>";							
							}
							html += "<div class='font-box'>";
							html += "<div class='font-title'>"+res[i].title+"</div>";
							html += "<div class='font-info'>";
							html += handleDis(res[i].description);
							html += "<a href='javascript:;' class='demo-1' value='"+res[i].content+"'>[阅读全文]</a>";
							html += "</div>";
							html += "<div class='font-time'>";
							html += "<div class='fb-user'><img src='"+res[i].officer.image+"' width='30px' height='30px' /><span><span class='lightfont'>"+res[i].officer.name+"</span>发布于:</span><span>"+res[i].stringPushDate+"</span></div>";
							html += "</div>";
							html += "</div>";
							html += "</li>";
								
							}
						} else {
						//    没有数据
							html = "<li class='ul-list'><h1 style='text-align: center;'>暂无数据</h1></li>";
						}
						return html;
			        }
			        
			        $('#articleContent').on('click','.demo-1',function(){
						//console.log("日报内容-->"+$(this).attr("value"));
						  	$.gDialog.alert("<div>"+$(this).attr("value")+"</div>", {
						    title: "日报内容",
						    required: true,
						 	animateIn : "rollIn",
						    animateOut: "rollOut",
						    isColse:true
			  			});
					});
					$('.fb-article').click(function(){
						  $.gDialog.confirm(
						  	`<form class="zh-form">
					<div class="section">
						<label>日报名称:</label>	
						<div class="input">
							<input type="text" id="articleTitle"  placeholder="请输入日报名称">
						</div>
					</div>
					<div class="section">
						<label>日报简介:</label>	
						<div class="input">
							<input type="text" id="description" placeholder="请输入日报简介">
						</div>
					</div>
					<div class="section edit">
						<p>日报详情:</p>
						<div id="editor" style="width:460px;overflow:hidden"></div>
					</div>
					<div class="section">
						<p class="info" style="color:red;font-size:14px;height:20px;width:100px"></p>
					</div>
		       	</form>`, 
						  	{
						    	title: "日报发布",
							 	animateIn : "rollIn",
							    animateOut: "rollOut",
							    required: false,
							    okbtn: "发布日报",
							    cencelbtn: "取消发布",
						 	});
						 	
					       	var E = window.wangEditor;
					        editor = new E('#editor');
					        editor.create();
					});
					
					function handleDis(discription){
						return discription==null?"暂无简介":discription;
					}
	</script>
</body>
</html>