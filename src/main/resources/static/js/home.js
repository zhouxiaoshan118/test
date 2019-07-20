var navtab;
layui.config({
    base: 'js/lib/' //layui自定义layui组件目录
}).extend({
    larry: 'larry',
    navtab: 'navtab',
    elemnts: 'elements',
    common: 'common'
});
layui.use(['elements', 'jquery', 'layer', 'larry', 'navtab', 'form', 'common'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        device = layui.device(),
        elements = layui.elements(),
        larry = layui.larry(),
        form = layui.form,
        common = layui.common;
        navtab = layui.navtab({
        elem: '#larry-tab'
    });

    // 页面禁止双击选中
    $('body').bind("selectstart", function () {
        return false;
    });

    //通过id遍历树找节点
    // var getNodeById = function(node, id) {
    //     if(id == node.id) {
    //         return node;
    //     } else {
    //         var childs = node.children;
    //         if(childs != null) {
    //             for(var i = 0; childs.length; i++) {
    //                 var tar = getNodeById(childs[i],id);
    //                 if(tar != ""){
    //                     return tar;
    //                 }
    //             }
    //         }
    //     }
    // }


    $(document).ready(function () {
        // 浏览器兼容检查
        if (device.ie && device.ie < 9) {
            layer.alert('最低支持ie9，您当前使用的是古老的 IE' + device.ie + '！');
        }
        // 001界面初始化
        AdminInit();
        //绑定导航数据
        $.ajaxSettings.async = false;

        $.ajax({
            type:"get",
            url:"/tree",
            success:function(res) {
                menus = res;
                var heads = res.children;
                heads.ishead = true;
                larry.set({
                    elem: '#menu',
                    data: heads,
                    cached: false
                });
                larry.render();
            }

        })

        //通过id遍历树找节点
        var getNodeById = function(node, id) {
            if(id == node.id) {
                return node;
            } else {
                var childs = node.children;
                if(childs != null) {
                    for(var i = 0; i < childs.length; i++) {
                        var tar = getNodeById(childs[i],id);
                        if(typeof(tar)!="undefined"){
                            return tar;
                        }
                    }
                }
            }
        }


        var $menu = $('#menu');
        $menu.find('li.layui-nav-item').each(function () {
            var $that = $(this);


            //绑定一级导航的点击事件
            $that.on('click', function () {
                var id = $that.data('pid');
                var node = getNodeById(menus, id);
                console.log(node);

                // $('.sys-menu-box').append('');
                $.ajaxSettings.async = false;
                $.ajax({
                    type:"get",
                    url:"/tree",
                    success:function(res) {
                        var tar = getNodeById(res, id);
                        //console.log(tar.children);
                        larry.set({
                            elem: '#larrySideNav',
                            data: tar.children,
                            cached: false
                        });
                        //加载菜单Dom
                        larry.render();
                        //添加选项卡
                        larry.on('click(side)', function (data) {
                            navtab.tabAdd(data.field);
                        });
                    }

                })


            });

        });
        // 左侧导航点击事件
        $menu.find('li[data-pid=0]').click();
        $("#larrySideNav").find("li").eq(0).addClass('layui-this');
        $.ajaxSettings.async = true;

        // 点击触发打开第一个菜单
        $menu.find('li.layui-nav-item').eq(0).click();



    });

    $('#larry-tab').bind("contextmenu", function () {
        return false;
    });

    // 常用操作
    $('#buttonRCtrl').find('dd').each(function () {
        $(this).on('click', function () {
            var eName = $(this).children('a').attr('data-eName');
            navtab.tabCtrl(eName);
        });
    });

    // 窗口自适应
    $(window).on('resize', function () {
        AdminInit();
        // iframe窗口自适应
        var $content = $('#larry-tab .layui-tab-content');
        $content.height($(this).height() - 153);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
    }).resize();

    // 刷新iframe
    $("#refresh_iframe").click(function () {
        $(".layui-tab-content .layui-tab-item").each(function () {
            if ($(this).hasClass('layui-show')) {
                $(this).children('iframe')[0].contentWindow.location.reload(true);
            }
        });
    });

    function AdminInit() {
        $('.layui-layout-admin').height($(window).height());
        $('body').height($(window).height());
        $('#larry-body').width($('.layui-layout-admin').width() - $('#larry-side').width());
        $('#larry-footer').width($('.layui-layout-admin').width() - $('#larry-side').width());
    }

    //清除缓存
    $('#clearCached').on('click', function () {
        larry.cleanCached();
        layer.alert('缓存清除完成!本地存储数据也清理成功！', {icon: 1, title: '系统提示'}, function () {
            location.reload();//刷新
        });
    });

    // 设置主题
    var fScreen = localStorage.getItem("fullscreen_info");
    var themeName = localStorage.getItem('themeName');
    if (themeName) {
        $("body").attr("class", "");
        $("body").addClass("larryTheme-" + themeName);
    }
    if (fScreen && fScreen != 'false') {
        var fScreenIndex = layer.alert('按ESC退出全屏', {
            title: '进入全屏提示信息',
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 4,
            offset: '100px'
        }, function () {
            entryFullScreen();
            $('#FullScreen').html('<i class="larry-icon larry-quanping"></i>退出全屏');
            layer.close(fScreenIndex);
        });
    }
    $('#larryTheme').on('click', function () {
        var fScreen = localStorage.getItem('fullscreen_info');
        var themeName = localStorage.getItem('themeName');
        layer.open({
            type: 1,
            title: false,
            closeBtn: true,
            shadeClose: false,
            shade: 0.35,
            area: ['450px', '300px'],
            isOutAnim: true,
            resize: false,
            anim: Math.ceil(Math.random() * 6),
            content: $('#LarryThemeSet').html(),
            success: function (layero, index) {
                if (fScreen && fScreen != 'false') {
                    $("input[lay-filter='fullscreen']").attr("checked", "checked");
                }
                if (themeName) {
                    $("#themeName option[value='" + themeName + "']").attr("selected", "selected");
                }
                form.render();
            }
        });

        // 主题设置
        form.on('select(larryTheme)', function (data) {
            var themeValue = data.value;
            localStorage.setItem('themeName', themeValue);//themeName:themeValue
            if (themeName) {
                $("body").attr("class", "");
                $("body").addClass("larryTheme-" + themeName);
            }
            form.render('select');
        });


    });


    // 顶部左侧导航控制开关
    $('#toggle').click(function () {
        var sideWidth = $('#larry-side').width();
        var bodyW = $('#larry-body').width();
        if (sideWidth === 200) {
            bodyW += 203;
            $('#larry-body').animate({
                left: '0',
                width: bodyW
            });
            $('#larry-footer').animate({
                left: '0',
                width: bodyW
            });
            $('#larry-side').animate({
                width: '0'
            });
        } else {
            bodyW -= 203;
            $('#larry-body').animate({
                left: '203px',
                width: bodyW
            });
            $('#larry-footer').animate({
                left: '203px',
                width: bodyW
            });
            $('#larry-side').animate({
                width: '200px'
            });
        }
    });

    // 登出系统
    $('#logout').on('click', function () {
        var url = 'login.html';
        common.logOut('退出登陆提示！', '你真的确定要退出系统吗？', url);
    })

})