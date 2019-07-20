<#include "common/head.ftl">
<#include "common/body.ftl">
<!DOCTYPE html>
<html>
<@head title="主页">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<#--<link href="${basePath}/css/jquery.Jcrop.min.css" rel="stylesheet" />-->
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" >
<link rel="stylesheet" href="${basePath}/css/common/global.css">
<link rel="stylesheet" href="http://at.alicdn.com/t/font_bmgv5kod196q1tt9.css">
<link rel="stylesheet" href="${basePath}/css/home.css" media="all">
</@head>
<@body cls="fixed-sidebar full-height-layout bo">
<#--<div class="shade" style="display:none;"></div>-->
<#--<div class="shade_over" style="display:none;">-->
    <#--<div class="top">裁剪您选择的文件-->
        <#--<div class="top_close"></div>-->
    <#--</div>-->
    <#--<div class="main">-->
        <#--&lt;#&ndash;<img alt="image" src="${basePath}/${user.partymember.avatar}" id="preview">&ndash;&gt;-->
    <#--</div>-->
    <#--<div class="ft">-->
        <#--<div class="sbmt">保存裁剪图片</div>-->
    <#--</div>-->
<#--</div>-->
<div class="layui-layout layui-layout-admin" id="layui_layout">
    <!-- 顶部区域 -->
    <div class="layui-header header-menu">
        <div class="logo posb" id="log">
            <img src="${basePath}/img/fav/logo.png">
        </div>
        <div class="layui-main posb">
            <!-- 左侧导航收缩开关 -->
            <div class="side-menu-switch posb" id="toggle">
                <span class="switch"  ara-hidden="true"></span>
            </div>
            <!-- 顶级菜单 -->
            <div class="larry-top-menu posb">
                <ul class="layui-nav clearfix" id="menu">
                </ul>
            </div>
            <!-- 右侧常用菜单导航 -->
            <div class="larry-right-menu posb">
                <!--<button class="layui-btn layui-btn-small" id="dianzhan">-->
                <!--<i class="larry-icon larry-dianzan"></i>-->
                <!--打赏作者-->
                <!--</button>-->
                <ul class="layui-nav clearfix">
                    <li class="layui-nav-item">
                        <a href="${basePath}/winui/index" target="_blank"><i class="larry-icon larry-wangzhanshouye"></i>返回主页</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="clearCached"><i class="larry-icon larry-qingchuhuancun"></i>清除缓存</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="larryTheme"><i class="larry-icon larry-theme1"></i>设置主题</a>
                    </li>
                    <!--<li class="layui-nav-item kjfs">
                        <a class="kuaijiefangshi"><i class="larry-icon larry-kuaijie"></i><cite>快捷方式</cite></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="http://www.larrycms.com/" target="_blank">网站主页</a>
                            </dd>
                            <dd>
                                <a href="http://blog.larrycms.com/" target="_blank">我的博客</a>
                            </dd>
                        </dl>
                    </li>-->
                    <li class="layui-nav-item exit">
                        <a  id="logout"><i class="larry-icon larry-exit"></i><cite>退出</cite></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side larrycms-left" id="larry-side">
        <div class="layui-side-scroll" >
            <!-- 管理员信息      -->
            <!--<div class="user-info">
                 <div class="photo">
                     <img src="images/user.jpg" alt="">
                 </div>
                 <p>admin您好！欢迎登录</p>
            </div>-->
            <!-- 系统菜单 -->
            <div class="sys-menu-box" >
                <ul class="layui-nav layui-nav-tree" id="larrySideNav" lay-filter="side" >

                </ul>
            </div>
        </div>
    </div>
    <!-- 右侧主题内容 -->
    <div class="layui-body" id="larry-body">
        <div class="layui-tab" id="larry-tab" lay-filter="larryTab">
            <div class="larry-title-box">
                <div class="go-left key-press pressKey" id="titleLeft" title="滚动至最右侧"><i class="larry-icon larry-weibiaoti6-copy"></i> </div>
                <ul class="layui-tab-title" lay-allowClose="true" id="layui-tab-title" lay-filter="subadd">
                    <li class="layui-this" id="admin-home"  lay-id="0" fresh=1>
                        <i class="larry-icon larry-houtaishouye"></i><em>后台首页</em>
                    </li>
                </ul>
                <div class="title-right" id="titleRbox">
                    <div class="go-right key-press pressKey" id="titleRight" title="滚动至最左侧"><i class="larry-icon larry-right"></i></div>
                    <div class="refresh key-press" id="refresh_iframe"><i class="larry-icon larry-shuaxin2"></i><cite>刷新</cite></div>
                    <div class="often key-press" lay-filter='larryOperate' id="buttonRCtrl">
                        <ul class="layui-nav posr">
                            <li class="layui-nav-item posb">
                                <a class="top"><i class="larry-icon larry-caozuo"></i><cite>常用操作</cite></a>
                                <dl class="layui-nav-child">
                                    <dd>
                                        <a  data-eName="closeCurrent"><i class="larry-icon larry-guanbidangqianye"></i>关闭当前选项卡</a>
                                    </dd>
                                    <dd>
                                        <a  data-eName="closeOther"><i class="larry-icon larry-guanbiqita"></i>关闭其他选项卡</a>
                                    </dd>
                                    <dd>
                                        <a  data-eName="closeAll"><i class="larry-icon larry-guanbiquanbufenzu"></i>关闭全部选项卡</a>
                                    </dd>
                                    <dd>
                                        <a  data-eName="refreshAdmin"><i class="larry-icon larry-kuangjia_daohang_shuaxin"></i>刷新最外层框架</a>
                                    </dd>
                                </dl>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
            <!--iframe-->
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                <iframe class="larry-iframe" data-id='0' name="ifr_0" id='ifr0' src="">
                    <!--内容-->
                </iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <div class="layui-footer layui-larry-foot psob" id="larry-footer">
        <div class="layui-main">
            <div class="left-box" style="display:none;">


            </div>
            <p>2016-2017 © 中兴软件(南昌)技术有限公司 版权所有</p>
        </div>
    </div>
    <!-- footer end -->
    <!-- layui-layout-admin end -->
</div>
<script src="${basePath}/plugins/layui/layui.js"></script>
<script src="${basePath}/js/home.js"></script>
</@body>
<script src=""></script>
</html>
