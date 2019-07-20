<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>Examples</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/officer/css/reset.css">
    <link href="${basePath}/plugins/officer/css/index.css" rel="stylesheet">
    <script type="text/javascript" src=""></script>
    <style type="text/css">
    .clearfix:after{clear:both;content:"";display: block;}
        a:link{
            color:#14191E;
            text-decoration: none;
        }
        .content{
            width: 100%;
            min-height: 400px;
            background:url(${basePath}/plugins/officer/img/2018-1-3.jpg) center top no-repeat;
            background-size:100% auto;
        }
        .main{
            padding-top: 452px;
            width: 100%;
            font-family: "微软雅黑";
            font-size: 14px;

        }
        .container{
            width: 80%;
            margin: 0 auto;
            min-height: 701px;
            padding-top: 30px;
        }
        .container .lecture-item{
            float: left;
            position:relative;
            width:18%;
            height: 248px;
            margin-right:18px;
            margin-bottom: 30px;
            box-sizing:border-box;
            text-align:center;
            background: rgba(255,255,255,0.8);
            box-shadow:0 4px 8px 0 rgba(7,17,27,.5);
            border-radius: 15px;
            transition:all .3s;
        }
        .container .lecture-item .lecture-img{
            position: absolute;
            top: -16px;
            left: 50%;
            margin-left:-48px;
            border-radius: 50%;
            width:96px;
            height:96px;
            transition: all 0.3s;
        }

        .container .lecture-item .lecture-name, .container .lecture-item .lecture-p{
            display: block;
            padding: 0 36px;
            word-wrap:break-word;
            overflow:hidden;
            word-break:break-all;
        }
        .container .lecture-item .lecture-name{
            font-size: 16px;
            line-height:24px;
            margin-top: 92px;
            white-space: nowrap;
            color: #07111b;
            font-weight: 700;
        }
        .container .lecture-item .lecture-title{
            display: block;
            font-size: 12px;
            margin-bottom:12px;
            color: #4D555D
            height:24px;
            line-height: 24px;
        }
        .container .lecture-item .lecture-p{
            position:relative;
            top: 0;
            margin: 0 auto;
            font-size: 12px;
            line-height: 24px;
            color: #4D555D;
            height:72px;
            font-weight: 200;
            transition: all .3s;
        }
       
        .container .lecture-item .lecture-p .lecture-tags{
            display: block;
            float: left;
            color:#fff;
            background: #79F06C;
            margin: 2px auto;
            border: 1px solid #efefef;
            padding: 3px;
            line-height: 12px;
            border-radius:3px;

        }
        .container .lecture-item .lecture-p .lecture-tags:nth-child(1){background-color: #666F78;}
        .container .lecture-item .lecture-p .lecture-tags:nth-child(2n+2){background-color: #DD5044;}
        .container .lecture-item .lecture-p .lecture-tags:nth-child(3n){background-color: #6699CC;}
    </style>
</head>
<body>
<div class="content">
    <div class="main" style="padding-top:300px">
        <div class="container clearfix">
			<#list officers as officer>
            <a href="/winui/officer/officerDetail/${officer.id}" class="lecture-item">
                <img class="lecture-img" src="${officer.image}" title="">
                <span class="lecture-name">${officer.name}</span>
                <span class="lecture-title">${officer.job}</span>
                <p class="lecture-p" title="技能">
					<#list officer.skill?split(",") as smallSkill>
						<span class="lecture-tags">${smallSkill}</span>
					</#list>	
                </p>
            </a>
			</#list>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/plugins/officer/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".lecture-item").on('mouseenter',function(){
            $(this).children('img').css({"transform":"scale(0.6,0.6)","transform-origin":"center top"});
            $(this).children('span').css("display","none");
            $(this).children('p').css({"top":"60px","height":"120px"});
            $(this).css("background","#ffffff");
        });
        $(".lecture-item").on('mouseleave',function(){
            $(this).children('img').css("transform","scale(1,1)");
            $(this).children('span').css("display","block");
            $(this).children('p').css({"top":"0","height":"72px"});
            $(this).css("background","rgba(255,255,255,.8)");
        });
    });
</script>
</body>
</html>
