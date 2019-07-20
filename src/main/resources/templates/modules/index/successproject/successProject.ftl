<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>中兴软件成功案例</title>
    <link rel="stylesheet" href="${basePath}/plugins/successproject/css/index2.css">
    <link rel="stylesheet" href="${basePath}/plugins/successproject/js/swiper/swiper.min.css">
    <link rel="stylesheet" href="${basePath}/plugins/successproject/js/swiper/animate.min.css">
    <link rel="stylesheet" href="${basePath}/plugins/successproject/iconfont/css/iconfont.css">
    <link rel="stylesheet" href="${basePath}/css/login/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/login/index.css">
    <script type="text/javascript" src="${basePath}/plugins/successproject/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${basePath}/plugins/successproject/js/jquery.flip.min.js"></script>
</head>
 
<body>
    <style>
    .swiper-container {
        height: 100%;
    }
    
    .swiper-slide {
        position: relative;
    }
    
    .swiper-slide .animate-pane {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;
        top: 0;
        z-index: 500;
        box-sizing: border-box;
        padding-left: 24%;
    }
    
    .swiper-container-vertical>.swiper-pagination-bullets .swiper-pagination-bullet {
        margin: 8px 0;
        opacity: 0.4;
    }
    
    .swiper-slide p {
        font-size: 30px;
        font-weight: bold;
        color: #fff;
    }
    
    .swiper-pagination-bullet-active {
        background: #368ce9;
    }
    
    .swiper-pagination-bullet {
        opacity: .4
    }
    .prev-pane{position:absolute;height:100%;left:0;top:0;width:50px;z-index: 9999;}
    .next-pane{position:absolute;height: 100%;right: 0;top:0;width: 50px;z-index: 9999;}
    .swiper-button-prev{display:none;width:45px;height:45px;background-image: url(${basePath}/plugins/successproject/img/bl.png);background-size: 45px 45px;margin-top:0;}
    .swiper-button-next{display:none;width:45px;height:45px;background-image: url(${basePath}/plugins/successproject/img/br.png);background-size: 45px 45px;margin-top:-22px;}
    .back-img{width: 100%;height: 100%;display: block;}

	.backdrop{background:#000;position: fixed;top:0px;width: 100%;height: 100%;opacity: .5;filter:alpha(opacity:50);z-index:999}
	.successMsg {
			top:20%;
			left:47%;
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
	a{
		cursor:pointer;
	}	
	.z-main{overflow: inherit;}
    .z-main li .front {padding: 9px 10px 10px;width: 100%;position: absolute;cursor: pointer}
    </style>
    <div class="z-body">
        <div class="header-bar clearfix">
            <img class="z-logo" src="${basePath}/plugins/successproject/img/logo.png" alt="">
            <i class="search " style="visibility:hidden">
                <div class="search-input clearfix">
                    <input type="text" name="selectValue">
                    <div class="sh-btn" id="sh-btn">搜索</div>
                </div>
            </i>
            <div class="status clearfix">
                <div>
                    <a href="javascript:;" id="setHome"><img src="${basePath}/plugins/successproject/img/home.png" alt=""> 设为首页</a>
                </div>
                <div>
                    <a href="javascript:;" id="addFavorite"><img src="${basePath}/plugins/successproject/img/collect.png" alt=""> 加入收藏</a>
                </div>
                <div>
                	<#if previewUser??>
                    	<a href="javascript:;" id="logout"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> ${previewUser.previewUserName},退出</a>
                    <#else>
                    	<a href="javascript:;" id="login"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> 系统登录</a>
                    </#if>	
                </div>
            </div>
        </div>
        <div class="header clearfix">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                	 <div class="swiper-slide">
                        <div class="ip-img sld2"></div>
                        <div class="animate-pane" style="padding-left: 4%;">
                            <img class="ani ani-logo2" src="${basePath}/plugins/successproject/img/slide3-logo.png" swiper-animate-effect="zoomIn" swiper-animate-duration="0.8s" swiper-animate-delay="0.2s">
                            <div class="ani sld2-tx" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.6s" swiper-animate-delay="0.6s">以“城市社交”为主题的平台，让城市之间便捷、高效的互通一手资讯，以深圳、港澳和国际城市的交流，带动城市之间的信息互通，为“一对多” 变为 “多对多”的城市交流合作创造机遇</div>
                            <div class="ani z-button2 konwMore" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.8s" swiper-animate-delay="1s" value="5671e606b59643338fc08205988844e1" style="cursor:pointer" id="z-button2">了解详情</div>
                        </div>
                    </div>
                
                    <div class="swiper-slide">
                        <div class="ip-img sld3"></div>
                        <div class="animate-pane" style="padding-left: 0;">
                            <div class="ani sld3-tx" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.6s" swiper-animate-delay="0.2s" >整合4G微基站、无线WI_FE、新能源汽车充电桩，实现大数据交互环境下的智能照明、智慧交通、无线城市等智慧城市管理核心功能
                                <img class="ani-logo3" src="${basePath}/plugins/successproject/img/slide2-logo.png" alt="">
                            </div>
                            <div class="btn-pane">
                                <div class="ani z-button3 konwMore" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.8s" swiper-animate-delay=".4s" value="88613da36f2f4e0d9ada1433a0e9e739" style="cursor:pointer" id="z-button3">了解详情</div>
                            </div>
                        </div>
                    </div>
                   
                    
                    <div class="swiper-slide">
                        <div class="ip-img sld1"></div>
                        <div class="animate-pane">
                            <img class="ani ani-logo1" src="${basePath}/plugins/successproject/img/slide1-logo.png" alt="" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="0.1s">
                            <!-- <div class="ani sld1-title" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.8s" swiper-animate-delay="0.3s">以移动端为载体，快速部署，党建工作全覆盖</div> -->
                            <div class="clearfix"><div class="ani sld1-tx" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s">实现资讯管理、组织管理、党员管理、考核管理、学习管理等功能的一体化平台，通过大数据智能分析全面提升党建管理水平</div></div>
                            <div class="ani z-button1 konwMore" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.8s" swiper-animate-delay=".8s" value="c0352c378090469b94ba907d4d684f46" style="cursor:pointer" id="z-button1">了解详情</div>
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
                <div class="prev-pane"><div class="swiper-button-prev"></div></div>
                <div class="next-pane"><div class="swiper-button-next"></div></div>
            </div>
        </div>
        <ul class="z-main clearfix">
            <li class="bg3"  onclick="showPage(this)" name="ebusiness">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z3.png" alt="">
                        </span>
                    <span>电商</span>
                </div>
            </li>
            <li class="bg2"  onclick="showPage(this)" name="edu">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z2.png" alt="">
                        </span>
                    <span>教育</span>
                </div>
            </li>
            <li class="bg1"  onclick="showPage(this)" name="travel">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z7.png" alt="">
                        </span>
                    <span>旅游</span>
                </div>
            </li>
            <li class="bg4"  onclick="showPage(this)" name="city">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z4.png" alt="">
                        </span>
                    <span>城市</span>
                </div>
            </li>
            <li class="bg5" onclick="showPage(this)" name="office">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z5.png" alt="">
                        </span>
                    <span>办公</span>
                </div>
            </li>
            <li class="bg6" onclick="showPage(this)" name="govern">
                <div class="front">
                    <span class="icon clearfix">
                            <img src="${basePath}/plugins/successproject/img/z6.png" alt="">
                        </span>
                    <span>政务</span>
                </div>
                
            </li>
        </ul>
        <div class="z-footer">Copyright © 2005-2016中兴软件技术(南昌)有限公司版权所有. ICP备案:赣ICP备14001378号-1</div>
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
        <script type="text/javascript">
        var w = 0;
        $(".fixed-section .list").hover(function() {
            // console.log(1)
            // $(this).css('background','#3A3A3A').find('img').attr('src','${basePath}/plugins/successproject/img/phone_hover.jpg')
            w = $(this).find('.txt').outerWidth();
            $(this).find('.txt').stop(true).animate({
                'margin-left': -w + 'px'
            })
        }, function() {
            $(this).find('.txt').stop(true).animate({
                'margin-left': 0
            })
        })
        
         function showPage(e) {
	        var appfeild = $(e).attr("name");
	        window.location.href="/winui/successproject/"+appfeild;
		}
        </script>
<div class="modal fade in" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
	<div style="display:table; width:100%; height:100%;">
	<div style="vertical-align:middle; display:table-cell;">
	<div class="modal-dialog modal-sm" style="width:540px;">
        <div class="modal-content" style="border: none;">
            <div class="col-left"></div>
            <div class="col-right"> 
                <div class="modal-header">
                    <button type="button" id="login_close" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
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
                        <p class="good-tips marginB10"><a id="btnForgetpsw" class="fr"><a href="javascript:void(0);" target="_blank" id="btnRegister">还没有账号？请联系管理员</a></p>
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
        <div id="successMsg" class="successMsg"> 
    </div>
   <div class="backdrop" style="display:none"></div>
</body>
<script type="text/javascript" src="${basePath}/plugins/successproject/js/swiper/swiper.min.js"></script>
<script type="text/javascript" src="${basePath}/plugins/successproject/js/swiper/animate.min.js"></script>
<script type="text/javascript">
$(".z-main li").hover(function(){
        $(this).find(".front").stop().css({"z-index":"111","box-shadow": "rgb(104, 121, 240) 1px 1px 8px 2px"}).animate({"top":"-10px"},300)
     },function(){
        $(this).find(".front").stop().css({"z-index":"1","box-shadow": "rgb(255, 255, 255) 0 0 0 0"}).animate({"top":"0px"},300)
     })
$(function() {
    var docH = $(window).height();
    hdTop = docH - 168;
    $(".header").height(hdTop);
    $(window).resize(function() {
            docH = $(window).height()
            hdTop = docH - 168;
            $(".header").height(hdTop);
        })
        //翻转
   

	$("#login_close").click(function(){
		$(".backdrop").hide();
		$("#loginModal").hide();
	})
	
	$("#setHome").click(function(){
    	SetHome(this,window.location);
    })
    
    $("#addFavorite").click(function(){
    	AddFavorite("成功案例",window.location);
    })
    
    $(".konwMore").click(function(){
    	location.href = "http://show.zte-s.com.cn/winui/successproject/spdetail/"+$(this).attr("value");
    })
	
	$("#login").click(function(){
		$(".backdrop").show();
		$("#loginModal").show();
	})
	
	$("#logout").click(function(){
		$.ajax({
			type:"get",
			url:"/winui/previewUserLogout",
			success:function(data){
				window.location.reload();
			}
		})
	})
	
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
	
	$("#sh-btn").click(function(){
		var selectValue =$.trim($("input[name='selectValue']").val());
		if(selectValue == ""){
			return false;
		}else{
			$.ajax({
				url:"/winui/successproject/selectProject",
				data:{
					name: selectValue
				},
				type:"post",
				success:function(data){
					
				}
			})
		}
		
	})
	
    //轮播  
    var mySwiper = new Swiper('.swiper-container', {
        loop:true,

        autoplay: 10000,
         autoplayDisableOnInteraction:false,
        speed: 1000,
        // direction:'vertical',
        pagination: '.swiper-pagination',
        paginationClickable: true,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        simulateTouch: false,
        onInit: function(swiper) {
            console.log(swiper)
            swiperAnimateCache(swiper); //隐藏动画元素 
            swiperAnimate(swiper); //初始化完成开始动画
        },
        onSlideChangeEnd: function(swiper) {
            swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
        }
    })
    
    

   
    $(".prev-pane").find('.swiper-button-prev').show();
    $(".next-pane").find('.swiper-button-next').show();

    var ani = false;
    $(".search").click(function(e) {
        e.stopPropagation()
        if (!ani) {
            $(".search-input").css('display', 'block').addClass('z-fadeIn');
            ani = true;
        } else {
            $(".search-input").css('display', 'none').removeClass('z-fadeIn')
            ani = false;
        }
    })
    $(".search-input").click(function(e) {
        e.stopPropagation()
    })
    $(document).click(function() {
        $(".search-input").css('display', 'none').removeClass('z-fadeIn')
        ani = false;
    })
    
    
})

function SetHome(obj,vrl)
    {
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


function validUser(id){
				$.ajax({
					type:"get",
					url:"/winui/previewUserIsInSession/"+id,
					success:function(data){
						if(data.isValid==0){
							$("#successMsg").html("请先登录").show(200).delay(1000).fadeOut(900);
						}else if(data.isValid==1){
							location.href="/winui/successPreview/"+id
						}else if(data.isValid==2){
							$("#successMsg").html("请在指定时间段查看").show(200).delay(1000).fadeOut(900);
						}else if(data.isValid==3){
							$("#successMsg").html("你暂无查看该系统权限").show(200).delay(1000).fadeOut(900);
						}
					}
				});
	}

</script>



</html>
