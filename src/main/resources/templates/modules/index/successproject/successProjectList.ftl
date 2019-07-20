<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>项目列表列表</title>
	<link rel="stylesheet" href="${basePath}/plugins/successproject/css/index.css">
    <link rel="stylesheet" href="${basePath}/plugins/successproject/iconfont/css/iconfont.css">
    <link rel="stylesheet" href="${basePath}/css/login/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/login/index.css">
	<script src="${basePath}/plugins/successproject/js/jquery-1.11.3.min.js"></script>
	<script src="${basePath}/plugins/paging/js/paging.js"></script>
    <style>
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
        .backdrop{background:#000;position: fixed;top:0px;width: 100%;height: 100%;opacity: .5;filter:alpha(opacity:50);z-index:999}
    	.ul-list,.left-title h3,.fixed-section .list .txt,.fixed-section .list img{box-sizing:content-box;-webkit-box-sizing:content-box;}
    	
    </style>
</head>
<body>
	<div class="header-bar clearfix">
        <img src="${basePath}/plugins/successproject/img/logo.png" alt="" class="z-logo">
            <i class="search " style="visibility:hidden">
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
                <div>
                	<#if previewUser??>
                    	<a href="javascript:void(0);" id="logout"><img src="${basePath}/plugins/successproject/img/login.png" alt="">${previewUser.previewUserName},退出</a>
                    <#else>
                    	<a href="javascript:void(0);" id="login"><img src="${basePath}/plugins/successproject/img/login.png" alt=""> 系统登录</a>
                    </#if>	
                </div>
            </div>
    </div> 
    
    <div class="fixed-section">
        <div class="list">
            <img src="${basePath}/plugins/successproject/img/phone.jpg" alt="">
            <!-- <span>电话</span> -->
            <div class="txt">电话：1565545654</div>
        </div>
        <div class="list">
        	<i class="iconfont iconfont-qq1" style="font-size:28px;margin: 0 7px"></i>
            <!-- <span>地址</span> -->
            <div class="txt">QQ：1799741585</div>
        </div>
    </div>
	<div class="z-container container-lsbg">
		<div class="content-list clearfix">
			<div class="list-left">
				<div class="left-title">
					<h3>您现在的位置：<a href="/winui/successproject">首页</a> > <a class="place" href="#">教学研究</a></h3>
				</div>
				<ul id="list_content">

				</ul>
                <div value="1 0"></div>
                <div id="page" class="page_div"></div>
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
        <div id="successMsg" class="successMsg"> 
    </div>
   <div class="backdrop" style="display:none"></div>
	<div class="z-footer">Copyright © 2005-2016中兴软件技术(南昌)有限公司版权所有. ICP备案:赣ICP备14001378号-1</div>
	<script src="${basePath}/plugins/successproject/js/echarts.js"></script>
	<script src="${basePath}/plugins/successproject/js/china.js"></script>
	
	<script>
		$(".container").css("min-height",$(window).height());
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
		})
	    
		var filed = "${appField}";
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
            } else if(name == "city") {
                return "城市";
            }
        }

        ajaxPage(1);

        function ajaxPage(num) {
            var data = new Object();
            data.page_size = 5;
            data.page_no = num;
            data.applicationField = "${appField}";
            $.ajax({
                url: "/front/sys/sp/list",
                type: "get",
                data: data,
                dataType: "json",
                success: function(data) {
                    var result = data.result;
                    // html代码拼接
					var html = handleHtmls(result);
                    $("#list_content").empty();
                    $("#list_content").append(html);
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
				    var image_topic = (res[i].topicImage == null||res[i].topicImage==undefined || res[i].topicImage=="" )?"":res[i].topicImage;
                	html += "<li class='ul-list'>";
                	html += "<div class='imgbox'>";
                    html += "<img src='"+image_topic+"'alt='' >";
                    html += "</div>";
                    html += "<div class='font-box'>";
                    html += "<div class='font-title'>"+res[i].name+"</div>";
                    html += "<div class='font-info'>";
					html +=	cutString(handleHtmlTag(res[i].info));//info部分，但是要去掉html标签
					html += "<a href='/winui/successproject/spdetail/"+res[i].id+"'>[阅读全文]</a>";
					html += "</div>";
					html += "<div class='font-time'>";
					html += "<span>"+getMyDate(res[i].createDate)+"</span>";
                    html += "</div></div></li>";
				}
			} else {
			//    没有数据
				html = "<li class='ul-list'><h1 style='text-align: center;'>暂无数据</h1></li>";
			}
			return html;
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

        function cutString(str) {
            if(str.length > 140) {
                return str.substring(0, 140)+"...";
            } else {
                return str;
            }
        }
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
        })
       $(".search-input").click(function(e){
        e.stopPropagation()
       })
       $(document).click(function(){
         $(".search-input").css('display','none').removeClass('z-fadeIn') 
             ani = false;
       })

     var w = 0;
        $(".fixed-section .list").hover(function(){
            // console.log(1)
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
		$('.demo-1').click(function(){
		  $.gDialog.alert("<div>111</div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero erat, scelerisque sit amet dolor nec, euismod feugiat massa.", {
		    title: "Alert Dialog Box",
		    required: true,
		 	animateIn : "rollIn",
		    animateOut: "rollOut"
		  });
		});

		
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