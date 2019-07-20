<#macro body cls="">
<body class="gray-bg ${cls}">
    <#nested>
</body>
<script src="${basePath}/plugins/jquery/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/plugins/bootstrap/bootstrap.min.js?v=3.3.6"></script>
<script src="${basePath}/plugins/validate/jquery.validate.js"></script>
<script src="${basePath}/plugins/validate/messages_zh.min.js"></script>
<#-- Automatic page load progress bars -->
<script src="${basePath}/plugins/pace/pace.min.js"></script>
<#--<script src="${basePath}/plugins/layer/layer.min.js"></script>-->
<script src="${basePath}/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${basePath}/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${basePath}/plugins/dataTables/jquery.dataTables.min.js"></script>
<#--<script src="${basePath}/js/hplus.min.js?v=4.1.0"></script>-->
<script src="${basePath}/js/zudp-1.js"></script>
</#macro>