<#include "common/head.ftl">
<#include "common/body.ftl">
<!DOCTYPE html>
<html>
<@head title="登录">
<link href="${basePath}/css/login.css" rel="stylesheet" />
<link href="${basePath}/plugins/bootstrap/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet" />
<link href="${basePath}/css/style.min862f.css" rel="stylesheet" />
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</@head>
<@body>
<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">ZUDP</h1>
        </div>
        <h3>欢迎使用 中兴统一开发平台</h3>

        <form class="m-t" role="form" action="${basePath}/login" id="loginForm" method="post">
            <div class="form-group">
                <input type="text" name="loginName" id="loginName" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input id="password" type="password" name="password" class="form-control" placeholder="密码" required="">
            </div>
            <#if isEnabledCaptcha>
                <div class="form-group" style="position: relative">
                    <input type="text" name="jcaptchaCode" class="form-control" placeholder="验证码" required="">
                    <img id="captcha" src="${basePath}/img/captcha" title="点击更换验证码" class="captcha">
                </div>
            </#if>
            <div style="color: red" id="loginErrorMessage">${error!}</div>
            <button id="login" type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            <p class="text-muted text-center">
                <a href="login.html#"><small>忘记密码了？</small></a> | <a href="${basePath}/register">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>
<div id="footer"> ©2017 中兴通讯股份有限公司 版权所有</div>
</@body>
<#--<script src="${basePath}/js/plugins/validate/jquery.validate.min.js"></script>-->
<#--<script src="${basePath}/js/plugins/validate/messages_zh.min.js"></script>-->
<script type="text/javascript">
    $(document).ready(function () {
        $("#loginName").focus();

    <#--<c:if test="${sbomf:isEnableCaptcha()}">-->
               <#--$("#captcha").click(function() {-->
                   <#--var img = $("#captcha");-->
                   <#--var imageSrc = img.attr("src");-->
                   <#--if(imageSrc.indexOf("?") > 0) {-->
                       <#--imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));-->
                   <#--}-->
                   <#--imageSrc = imageSrc + "?" + new Date().getTime();-->
                   <#--img.attr("src", imageSrc);-->
               <#--});-->
    <#--</c:if>-->

//        $("#loginForm").validate({
//                                     submitHandler:function(form) {
//                                         form.submit();
//                                     }
//                                 });
    });
</script>
</html>
