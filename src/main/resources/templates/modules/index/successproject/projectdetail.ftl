<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>中兴软件统一开发平台项目详情</title>
	<link rel="stylesheet" href="${basePath}/plugins/successproject/css/index.css">
	<link rel="stylesheet" href="${basePath}/plugins/successproject/css/ft-carousel.css">
	<link rel="stylesheet" href="${basePath}/plugins/successproject/iconfont/css/iconfont.css">
	<link rel="stylesheet" href="${basePath}/css/login/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath}/css/login/index.css">
	<style>
		.backdrop{background:#000;position: fixed;width: 100%;height: 100%;top:0;opacity: .5;filter:alpha(opacity:50);display:none}
		.successMsg {
			top:20%;
			left:30%;
		    display: none;  
		    color: green;  
		    text-align: center;  
		    position: fixed;  
		    background-color: white;  
		    font-size: 14px;  
		    z-index: 999999;  
		    height: 100px;  
		    width: 200px;  
		    line-height: 100px;  
		}  
		.bdshare-button-style0-32 div a{
			float: right;
		    width: auto;
		    line-height: inherit;
		    height: auto;
		    background-image: none;
		    background-repeat: no-repeat;
		    cursor: pointer;
		    margin: 0;
		    text-indent: 0;
		    overflow: hidden;
		    color: #fff;	
		}
		.bdshare-button-style0-32 div a:hover{color:#fff}
		.fixed-section .list img{height:40px}
		.container{    padding-right: 0px;padding-left: 0px;    width: 100%;}
		body{margin: 0;font-family: "微软雅黑";background-color: #1F1F1F;}
		.example {position:relative;width: 750px;height: 336px;font-size: 40px;text-align: center;margin: 20px auto;background-color: #464576;}
		.carousel-item{color: #fff;font-family:  Arial Black}
		.z-filter{position:absolute;background:rgba(0,0,0,.5);width:100%;height:50px;bottom:0;left:0;z-index:300;}
		.ft-carousel .carousel-indicators{z-index:301;bottom:17px;text-align:right;padding-right:30px;box-sizing:border-box;}
		.carousel-indicators{margin-left:0;}
		#bdshare_weixin_qrcode_dialog{
			    left: 5.5%!important;
    			top: 375px!important;
		}
		#bdSharePopup_selectshare1517535684083bg,.bdselect_share_box,.bdselect_share_head{
			width:120px!important;
		}
		
	</style>
	<script src="${basePath}/plugins/successproject/js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<div class="header-bar clearfix">
        <img src="${basePath}/plugins/successproject/img/logo.png" alt="" class="z-logo">
            <i class="search" style="visibility:hidden">
                <div class="search-input clearfix ">
                    <input type="text"> 
                    <div class="sh-btn">搜索</div>
                </div>
                 
            </i>
            <div class="status clearfix">
                <div>
                	<a href="javascript:void(0);" id="setHome"><img src="${basePath}/plugins/successproject/img/home.png" alt=""> 设为首页</a>
                </div>
                <div>
                	<a href="javascript:void(0);" id="addFavorite"><img src="${basePath}/plugins/successproject/img/collect.png" alt=""> 加入收藏</a>
                </div>
                <div id="loginDiv">
                	<#if previewUser??>
                    	<a href="javascript:void(0);" id="logout"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> ${previewUser.previewUserName},退出</a>
                    <#else>
                    	<a href="javascript:void(0);" onclick="login()" id="login"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> 系统登录</a>
                    </#if>	
                </div>
            </div>
    </div>
	<div class="container container-lsbg">
		<div class="content-list">
			<div class="list-left">
				<div class="left-title">
                    <h3 style="height:30px">您现在的位置：<a href="/winui/successproject">首页</a> > <a class="place" href="/winui/successproject/${successProject.applicationField}"></a> > <a class="place2" href="#">项目详情</a></h3>
				</div>
				<div class="main-info-list">
					<#--<p class="top-nav">您现在的位置：<a href="#">首页</a> > <a href="#">教学研究</a></p>-->
					<div class="main-text">
						<p class="text-title">${successProject.name}</p>
						<p class="text-time">发布时间：${successProject.createDate?string("yyyy-MM-dd")}</p>
						<div class="text-cnt" style="text-align: left;font-size:14px">
							<p class="text-p" style="text-align:left;">${successProject.info}</p>
						</div>
					</div>
					
					<div class="example">
							<div class="ft-carousel" id="carousel_1">
							<div class="z-filter"></div>
				                    <ul class="carousel-inner" style="position: relative;width:  680px;height: auto;">
									
									<#list images as img>
										<li class="carousel-item">
                                            <div class="img-box">
                                                <div class="img-680-425">
                                                    <a target="_blank">
                                                        <img src="${img}" alt="">
                                                    </a>
                                                </div>
                                                <p></p>
                                            </div>
                                        </li>
									</#list>
			                    </ul>
			               </div>
					</div>
					<div class="d-bottom">
						<div class="bdsharebuttonbox"><div class="a-pane" style="cursor:pointer;display:block">
						<a class="query-xq" id="query-xq" onclick="checkLogin();"  target="_blank"><span id="sp">演示系统<span></a>
						</div><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_qzone"  title="分享到QQ空间" onclick = "qqZone()"></a></div>
                    </div>
				</div>
			</div>
			
			
			
	<div class="modal fade in" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
	<div style="display:table; width:100%; height:100%;">
	<div style="vertical-align:middle; display:table-cell;">
	<div class="modal-dialog modal-sm" style="width:540px;">
        <div class="modal-content" style="border: none;">
            <div class="col-left"></div>
            <div class="col-right"> 
                <div class="modal-header">
                    <button type="button" id="login_close" class="close" data-dismiss="modal"><span aria-hidden="true" id="close">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="loginModalLabel" style="font-size: 18px;">登录</h4>
                </div>
                <div class="modal-body">
                    <section class="box-login v5-input-txt" id="box-login">
                        <form id="login_form" action="" method="post" autocomplete="off">
                            
                            <ul>
                                <li class="form-group"><input class="form-control" id="id_account_l" maxlength="50" name="account_l" placeholder="请输入用户名" type="text"></li>
                                <li class="form-group"><input class="form-control" id="id_password_l" name="password_l" placeholder="请输入密码" type="password"></li>
                            </ul>
                        </form>
                        <p class="good-tips marginB10"><a id="btnForgetpsw" class="fr"><a href="javascript:;" target="_blank" id="btnRegister">还没有账号？请联系管理员</a></p>
                        <div class="login-box marginB10">
                            <button id="login_btn" type="button" class="btn btn-micv5 btn-block globalLogin">登录</button>
                            <div id="login-form-tips" class="tips-error bg-danger" style="display: none;">错误提示</div>
                        </div>

                        
                        <div class="threeLogin" style="color:red"></div>
                        
                    </section>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>
    </div>
		
			
			<div class="list-right">
				<div class="left-title">
				</div>
				<div class="right-bot-box">
					<div class="top-tit">
						<p >项目排行</p>
					</div>
					<div class="btm-waiter">
						<ul>
							<#list recomendSPs as sp>
                                <li>
                                	<span class="l">[&nbsp;推荐&nbsp;]</span>
                               		<span class="waiter-body"><a href="/winui/successproject/spdetail/${sp.id}" target="_blank">${sp.name}<i></i></a></span>
                                </li>
                            </#list>
						</ul>
					</div>
				</div>
				<div class="right-bot-box" style="height: 350px;position: relative;">
					<div class="top-tit" style="margin-bottom: 0">
						<p>合作伙伴</p>
					</div>
					<div id="maps" style="height:330px"></div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div id="successMsg" class="successMsg">  
    </div>  
	
	<div class="backdrop"></div>
	<div class="fixed-section">
        <div class="list">
            <img src="${basePath}/plugins/successproject/img/phone.jpg" alt="">
            <!-- <span>电话</span> -->
            <div class="txt">电话：0791-86178661</div>
        </div>
        <div class="list">
            <i class="iconfont iconfont-qq1" style="font-size:28px;margin: 0 7px"></i>
            <!-- <span>地址</span> -->
            <div class="txt">QQ：1340419697</div>
        </div>
    </div>
    <div class="z-footer">Copyright © 2005-2016中兴软件技术(南昌)有限公司版权所有. ICP备案:赣ICP备14001378号-1</div>
	<script src="${basePath}/plugins/successproject/js/echarts.js"></script>
	<script src="${basePath}/plugins/successproject/js/china.js"></script>
	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{},"image":{"viewList":["tsina","qzone","tqq"],"viewText":"分享到：","viewSize":"12"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tsina","qzone","tqq"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
	<script>
	
        var filed = "${successProject.applicationField}";
        $(".place").text(getAppFeildName(filed)+"领域");
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
            } else if(name == "society") {
                return "社会";
            }else if(name == "city") {
                return "城市";
            }
        }
        
        
       
	     $("#bdshare_weixin_qrcode_dialog").css({
 			"left": "80px",
	    	"top": "375px",
	    	"width": "240px",
	    	"height": "270px",
	   		"display": "block"
 		})
       
		
		$(".container").css("min-height",$(window).height());
		$(".container").css("height",$(document).height());

		

      
		

	</script>
	  
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>  
	<script src="${basePath}/plugins/successproject/js/ft-carousel.min.js"></script>
	<script>  
  var url = "http://show.zte-s.com.cn/";  
    var desc_ = "中兴软件统一开发平台";        
    function tencentWeiBo(){  
         var _url = "http://show.zte-s.com.cn/";     
         var _showcount = 0;  
         var _summary = "";  
         var _title = desc_;  
         var _site = "http://show.zte-s.com.cn/";    
         var _width = "600px";  
         var _height = "800px";  
         var _pic = "http://www.junlenet.com/uploads/allimg/150510/1-150510104044.jpg";  
         var _shareUrl = 'http://share.v.t.qq.com/index.php?c=share&a=index';  
         _shareUrl += '&title=' + encodeURIComponent(_title||document.title);    //分享的标题  
         _shareUrl += '&url=' + encodeURIComponent(_url||location.href);    //分享的链接  
         _shareUrl += '&appkey=5bd32d6f1dff4725ba40338b233ff155';    //在腾迅微博平台创建应用获取微博AppKey  
         //_shareUrl += '&site=' + encodeURIComponent(_site||'');   //分享来源  
         _shareUrl += '&pic=' + encodeURIComponent(_pic||'');    //分享的图片，如果是多张图片，则定义var _pic='图片url1|图片url2|图片url3....'  
         window.open(_shareUrl,'width='+_width+',height='+_height+',left='+(screen.width-_width)/2+',top='+(screen.height-_height)/2+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');  
    }      
    var top = window.screen.height / 2 - 250;    
    var left = window.screen.width / 2 - 300;    
    var height = window.screen.height;  
    var width =  window.screen.width;   
    /*title是标题，rLink链接，summary内容，site分享来源，pic分享图片路径,分享到新浪微博*/    
    function shareTSina() {    
        var title = desc_;  
        var   rLink = "http://show.zte-s.com.cn/";  
        var backUrl = "http://wx.hengfu100.com/member/c_friend";  
        var site = desc_;  
        var pic = "http://www.junlenet.com/uploads/allimg/150510/1-150510104044.jpg";  
        window.open("http://service.weibo.com/share/share.php?pic=" +encodeURIComponent(pic) +"&title=" +     
        encodeURIComponent(title.replace(/ /g, " ").replace(/<br \/>/g, " "))+ "&url=" + encodeURIComponent(rLink),    
        "分享至新浪微博",    
        "height=500,width=600,top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");    
    }    
    
     function qqFriend() {  
            var p = {  
                url : 'http://show.zte-s.com.cn/', /*获取URL，可加上来自分享到QQ标识，方便统计*/  
                desc:'',  
                //title : '新玩法，再不来你就out了！', /*分享标题(可选)*/  
                title:desc_,  
                summary : '', /*分享摘要(可选)*/  
                pics : 'http://www.junlenet.com/uploads/allimg/150510/1-150510104044.jpg', /*分享图片(可选)*/  
                flash : '', /*视频地址(可选)*/  
                site : 'http://show.zte-s.com.cn/', /*分享来源(可选) 如：QQ分享*/  
                style : '201',  
                width : 32,  
                height : 32  
            };  
            var s = [];  
            for ( var i in p) {  
                s.push(i + '=' + encodeURIComponent(p[i] || ''));  
            }  
            var url = "http://connect.qq.com/widget/shareqq/index.html?"+s.join('&');  
            return url;  
            //window.location.href = url;  
            //document.write(['<a class="qcShareQQDiv" href="http://connect.qq.com/widget/shareqq/index.html?',s.join('&'), '" >分享给QQ好友</a>' ].join(''));  
        }  
      
    function qqZone(){  
         var _url = "http://show.zte-s.com.cn/";     
         var _showcount = 0;  
         var _desc = desc_;  
         var _summary = "";  
         var _title = "中兴软件统一开发平台";  
         var _site = "";  
         var _width = "600px";  
         var _height = "800px";  
         var _summary = "";  
         var _pic = "http://www.junlenet.com/uploads/allimg/150510/1-150510104044.jpg";  
         var _shareUrl = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?';  
         _shareUrl += 'url=' + encodeURIComponent(_url||document.location);   //参数url设置分享的内容链接|默认当前页location  
         _shareUrl += '&showcount=' + _showcount||0;      //参数showcount是否显示分享总数,显示：'1'，不显示：'0'，默认不显示  
         _shareUrl += '&desc=' + encodeURIComponent(_desc||'分享的描述');    //参数desc设置分享的描述，可选参数  
         //_shareUrl += '&summary=' + encodeURIComponent(_summary||'分享摘要');    //参数summary设置分享摘要，可选参数  
         _shareUrl += '&title=' + encodeURIComponent(_title||document.title);    //参数title设置分享标题，可选参数  
         //_shareUrl += '&site=' + encodeURIComponent(_site||'');   //参数site设置分享来源，可选参数  
         _shareUrl += '&pics=' + encodeURIComponent(_pic||'');   //参数pics设置分享图片的路径，多张图片以＂|＂隔开，可选参数  
        window.open(_shareUrl,'width='+_width+',height='+_height+',top='+(screen.height-_height)/2+',left='+(screen.width-_width)/2+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');   
    }  
      
    
    
    function setLoginHtml(flag)
    {
    	$("#loginDiv").html();
    	var lgoinhtml = '<a href="javascript:void(0);" onclick="login();" id="login"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> 系统登录</a>';
    	
    	$("#loginDiv").html(lgoinhtml);     
    }
</script> 
	<script>
		$(function(){
			
			$("#login_close").click(function(){
				$(".backdrop").hide();
				$("#loginModal").hide();
			});
			
			$("#setHome").click(function(){
		    	SetHome(this,window.location);
		    });
		    
		    $("#addFavorite").click(function(){
		    	AddFavorite("成功案例",window.location);
		    });
		    
		
			$("#logout").click(function(){
				$.ajax({
					type:"get",
					url:"${basePath}/winui/previewUserLogout?times="+ new Date().getTime(),
					success:function(data){
						if(data)
						{
							window.location.reload();
						}
					}
				});
			});
			
			
			$("#login_btn").click(function(){
				var id_account_l =$.trim($("#id_account_l").val()); 
				var id_password_l =$.trim($("#id_password_l").val()); 
				if(id_account_l == "" || id_password_l == ""){
					$(".threeLogin").html("请完善登录信息");
				}else{
					var previewUser = new Object();
					previewUser.previewUserName = id_account_l;
					previewUser.previewPassword = id_password_l;
					$.ajax({
						type:"post",
						url:"/winui/previewUserLogin",
						data:previewUser,
						success:function(data){
							if(data.isValid == 0){
								$(".threeLogin").html("用户名或密码错误");
							}else if(data.isValid == 2){
								$(".threeLogin").html("您的账号已禁用！");
							}else{
								$(".backdrop").hide();
								$("#loginModal").hide();
								window.location.reload();
							}
						}
					})
				}
			});
		
			 $("#carousel_1").FtCarousel();
			//$(".a-pane").click(
				
			//});
			
			
			
			
			
			
			$("#close").click(function(){
				$("#loginModal").hide();
				$(".backdrop").hide();
			})
		});
		
		
		
		//设为首页
        function SetHome(obj,vrl){
	        try
	        {
	                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
	                
	        }
	        catch(e){
	                if(window.netscape) {
	                        try {
	                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
	                        } 
	                        catch (e) { 
	                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
	                        }
	                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
	                        prefs.setCharPref('browser.startup.homepage',vrl);
	                        
	                 }
	        }
	    }	
	    //加入收藏
		function AddFavorite(title, url) {
			 try {
			   window.external.addFavorite(url, title);
			 }
			catch (e) {
			   try {
			    window.sidebar.addPanel(title, url, "");
			  }
			   catch (e) {
			     alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请进入新网站后使用Ctrl+D进行添加");
			   }
			 }
		}
		
		function handleHtmlTag(htmls) {
			var info = "";
            if(htmls != null) {
			   info = htmls.replace(/<style(([\s\S])*?)<\/style>/g, "");
               info = info.replace(/[\r\n]/g,"");
               info = info.replace(/<!--[\w\W\r\n]*?-->/g,"");
			   info = info.replace(/<[^>]+>/g,"");
			}
			return info;
        }
        
		
		
		var ani = false;
        $(".search").click(function(e){
            e.stopPropagation()
            if(!ani){
                $(".search-input").css('display','block').addClass('z-fadeIn');
                ani = true;
            }else{
                 $(".search-input").css('display','none').removeClass('z-fadeIn') 
                 ani = false;
            }
        });
       $(".search-input").click(function(e){
        e.stopPropagation()
       })
       $(document).click(function(){
         $(".search-input").css('display','none').removeClass('z-fadeIn') 
             ani = false;
       })

     var w = 0;
        $(".fixed-section .list").hover(function(){
            // $(this).css('background','#3A3A3A').find('img').attr('src','img/phone_hover.jpg')
            w= $(this).find('.txt').outerWidth();
            $(this).find('.txt').stop(true).animate({
                'margin-left':-w+'px'
            })
        },function(){
            $(this).find('.txt').stop(true).animate({
                'margin-left':0
            })
        })
		$(".container").css("min-height",$(window).height());
		$(".container").css("height",$(document).height());
		
		
		 function login()
		 {
			 $(".backdrop").show();
			$("#loginModal").show();
		 }
		
		
		
		  function checkLogin(){
			$.ajax({
				type:"get",
				async:false, 
				url:"/winui/previewUserIsInSession/${successProject.id}?times="+new Date().getTime(),
				success:function(data){
					if(data.isValid==0){
						setLoginHtml(false);
						$("#successMsg").html("请先登录").show(200).delay(1000).fadeOut(900);
						return;
					}
					if(data.isValid==1){
						$("#query-xq").prop("href","/winui/successPreview/${successProject.id}");
						return;
					}
					if(data.isValid==2){
						$("#successMsg").html("您的账号已过期").show(200).delay(1000).fadeOut(900);
						return;
					}
					if(data.isValid==3){
						$("#successMsg").html("你暂无查看该系统权限").show(200).delay(1000).fadeOut(900);
						return;
					}
					return;
				}
			});
        }
        
	</script>
	 <script type="text/javascript">
var dom = document.getElementById("maps");
var myChart = echarts.init(dom);
var app = {};
option = null;
var geoCoordMap = {
    '上海': [121.4648,31.2891],
    '东莞': [113.8953,22.901],
    '东营': [118.7073,37.5513],
    '中山': [113.4229,22.478],
    '临汾': [111.4783,36.1615],
    '临沂': [118.3118,35.2936],
    '丹东': [124.541,40.4242],
    '丽水': [119.5642,28.1854],
    '乌鲁木齐': [87.9236,43.5883],
    '佛山': [112.8955,23.1097],
    '保定': [115.0488,39.0948],
    '兰州': [103.5901,36.3043],
    '包头': [110.3467,41.4899],
    '北京': [116.4551,40.2539],
    '北海': [109.314,21.6211],
    '南京': [118.8062,31.9208],
    '南宁': [108.479,23.1152],
    '南昌': [116.0046,28.6633],
    '南通': [121.1023,32.1625],
    '厦门': [118.1689,24.6478],
    '台州': [121.1353,28.6688],
    '合肥': [117.29,32.0581],
    '呼和浩特': [111.4124,40.4901],
    '咸阳': [108.4131,34.8706],
    '哈尔滨': [127.9688,45.368],
    '唐山': [118.4766,39.6826],
    '嘉兴': [120.9155,30.6354],
    '大同': [113.7854,39.8035],
    '大连': [122.2229,39.4409],
    '天津': [117.4219,39.4189],
    '太原': [112.3352,37.9413],
    '威海': [121.9482,37.1393],
    '宁波': [121.5967,29.6466],
    '宝鸡': [107.1826,34.3433],
    '宿迁': [118.5535,33.7775],
    '常州': [119.4543,31.5582],
    '广州': [113.5107,23.2196],
    '廊坊': [116.521,39.0509],
    '延安': [109.1052,36.4252],
    '张家口': [115.1477,40.8527],
    '徐州': [117.5208,34.3268],
    '德州': [116.6858,37.2107],
    '惠州': [114.6204,23.1647],
    '成都': [103.9526,30.7617],
    '扬州': [119.4653,32.8162],
    '承德': [117.5757,41.4075],
    '拉萨': [91.1865,30.1465],
    '无锡': [120.3442,31.5527],
    '日照': [119.2786,35.5023],
    '昆明': [102.9199,25.4663],
    '杭州': [119.5313,29.8773],
    '枣庄': [117.323,34.8926],
    '柳州': [109.3799,24.9774],
    '株洲': [113.5327,27.0319],
    '武汉': [114.3896,30.6628],
    '汕头': [117.1692,23.3405],
    '江门': [112.6318,22.1484],
    '沈阳': [123.1238,42.1216],
    '沧州': [116.8286,38.2104],
    '河源': [114.917,23.9722],
    '泉州': [118.3228,25.1147],
    '泰安': [117.0264,36.0516],
    '泰州': [120.0586,32.5525],
    '济南': [117.1582,36.8701],
    '济宁': [116.8286,35.3375],
    '海口': [110.3893,19.8516],
    '淄博': [118.0371,36.6064],
    '淮安': [118.927,33.4039],
    '深圳': [114.5435,22.5439],
    '清远': [112.9175,24.3292],
    '温州': [120.498,27.8119],
    '渭南': [109.7864,35.0299],
    '湖州': [119.8608,30.7782],
    '湘潭': [112.5439,27.7075],
    '滨州': [117.8174,37.4963],
    '潍坊': [119.0918,36.524],
    '烟台': [120.7397,37.5128],
    '玉溪': [101.9312,23.8898],
    '珠海': [113.7305,22.1155],
    '盐城': [120.2234,33.5577],
    '盘锦': [121.9482,41.0449],
    '石家庄': [114.4995,38.1006],
    '福州': [119.4543,25.9222],
    '秦皇岛': [119.2126,40.0232],
    '绍兴': [120.564,29.7565],
    '聊城': [115.9167,36.4032],
    '肇庆': [112.1265,23.5822],
    '舟山': [122.2559,30.2234],
    '苏州': [120.6519,31.3989],
    '莱芜': [117.6526,36.2714],
    '菏泽': [115.6201,35.2057],
    '营口': [122.4316,40.4297],
    '葫芦岛': [120.1575,40.578],
    '衡水': [115.8838,37.7161],
    '衢州': [118.6853,28.8666],
    '西宁': [101.4038,36.8207],
    '西安': [109.1162,34.2004],
    '贵阳': [106.6992,26.7682],
    '连云港': [119.1248,34.552],
    '邢台': [114.8071,37.2821],
    '邯郸': [114.4775,36.535],
    '郑州': [113.4668,34.6234],
    '鄂尔多斯': [108.9734,39.2487],
    '重庆': [107.7539,30.1904],
    '金华': [120.0037,29.1028],
    '铜川': [109.0393,35.1947],
    '银川': [106.3586,38.1775],
    '镇江': [119.4763,31.9702],
    '长春': [125.8154,44.2584],
    '长沙': [113.0823,28.2568],
    '长治': [112.8625,36.4746],
    '阳泉': [113.4778,38.0951],
    '青岛': [120.4651,36.3373],
    '韶关': [113.7964,24.7028]
};

var BJData = [
    [{name:'南昌'}, {name:'上海',value:95}],
    [{name:'南昌'}, {name:'广州',value:90}],
    [{name:'南昌'}, {name:'大连',value:80}],
    [{name:'南昌'}, {name:'南宁',value:70}],
    [{name:'南昌'}, {name:'北京',value:60}],
    [{name:'南昌'}, {name:'拉萨',value:50}],
    [{name:'南昌'}, {name:'长春',value:40}],
    [{name:'南昌'}, {name:'包头',value:30}],
    [{name:'南昌'}, {name:'重庆',value:20}],
    [{name:'南昌'}, {name:'常州',value:10}]
];

var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];
        var fromCoord = geoCoordMap[dataItem[0].name];
        var toCoord = geoCoordMap[dataItem[1].name];
        if (fromCoord && toCoord) {
            res.push({
                fromName: dataItem[0].name,
                toName: dataItem[1].name,
                coords: [fromCoord, toCoord]
            });
        }
    }
    return res;
};

var color = ['#f18117'];
var series = [];
[['北京', BJData]].forEach(function (item, i) {
    series.push({
        name: item[0] + ' Top10',
        type: 'lines',
        zlevel: 1,
        effect: {
            show: true,
            period: 6,
            trailLength: 0.7,
            color: '#fff',
            symbolSize: 3
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 0,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    },
    {
        name: item[0] + ' Top10',
        type: 'lines',
        zlevel: 2,
        symbol: ['none', 'arrow'],
        symbolSize: 10,
        effect: {
            show: true,
            period: 6,
            trailLength: 0,
            symbol: planePath,
            symbolSize: 15
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    },
    {
        name: item[0] + ' Top10',
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 2,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
            }
        },
        symbolSize: function (val) {
            return val[2] / 8;
        },
        itemStyle: {
            normal: {
                color: color[i]
            }
        },
        data: item[1].map(function (dataItem) {
            return {
                name: dataItem[1].name,
                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
            };
        })
    });
});

option = {
    backgroundColor: '#fff',
    tooltip : {
        trigger: 'item'
    },
    geo: {
        map: 'china',
        label: {
            emphasis: {
                show: false
            }
        },
        roam: true,
        itemStyle: {
            normal: {
                areaColor: '#5ea7d4',
                borderColor: '#5dd1f6'
            },
            emphasis: {
                areaColor: '#4c8bc1'
            }
        }
    },
    series: series

};
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>
 
</body>
</html>