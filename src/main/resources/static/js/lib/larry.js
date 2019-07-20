/**
 * @author Larry_qin 2017-05-20 /参考Beginner navbar.js并进行改进
 * @link      http://www.larrycms.com/
 * @version   [0.01]
 * @license   [license]
 * @param     {[type]}     [description]
 * @return    {[type]}     [description]
 */
layui.define(['jquery', 'layer', 'element', 'common'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        element = layui.element,
        common = layui.common,
        cacheName = 'navbarCache';

    var Navbar = function () {
        /**
         *  默认配置
         */
        this.config = {
            elem: undefined, //容器
            data: undefined, //数据源
            url: undefined, //数据源地址
            type: 'GET', //读取方式
            cached: false, //是否使用缓存
            spreadOne: true //是否设置菜单动画
        };
    };

    Navbar.prototype.render = function () {
        var _that = this;
        var _config = _that.config;
        if (typeof(_config.elem) !== 'string' && typeof(_config.elem) !== 'object') {
            common.cmsError('LarryCMS Nav error: elem参数未定义或设置出错', 'elem参数出错');
        }
        // 若为字符串
        var $container;
        if (typeof(_config.elem) === 'string') {
            $container = $('' + _config.elem + '');
        }
        // 若为object
        if (typeof(_config.elem) === 'object') {
            $container = _config.elem;
        }
        if ($container.length === 0) {
            common.cmsError('LarryCMS Nav error:找不到elem参数配置的容器，请检查.', '容器参数配置出错');
        }
        if (_config.data === undefined && _config.url === undefined) {
            common.cmsError('LarryCMS Nav error:请为Nav配置数据源.', '数据源配置出错')
        }
        // data处理
        if (_config.data !== undefined && typeof (_config.data) === 'object') {


            var html = getHtml(_config.data);
            $container.html(html);
            element.init();
            _that.config.elem = $container;



        } else {
            // 若有开启缓存
            if (_config.cached) {
                var cacheNav = layui.data(cacheName);
                if (cacheNav.larry === undefined) {
                    $.ajax({
                        type: _config.type,
                        url: _config.url,
                        async: false,
                        dataType: 'json',
                        success: function (result, status, xhr) {
                            //添加缓存
                            layui.data(cacheName, {
                                key: 'larry',
                                vlue: result
                            });
                            var html = getHtml(result);
                            $container.html(html);
                            element.init();
                        },
                        error: function (xhr, status, error) {
                            common.cmsError('"LarryCMS error"' + error, 'Nav缓存开启错误！');
                        },
                        complete: function () {
                            _that.config.elem = $container;
                        }
                    });
                } else {
                    var html = getHtml(cacheNav.larry);
                    $container.html(html);
                    element.init();
                    _that.config.elem = $container;
                }
            } else {
                // 清空缓存
                layui.data(cacheName, null);
                $.ajax({
                    type: _config.type,
                    url: _config.url,
                    async: false, //_config.async,

                    dataType: 'json',
                    success: function (result, status, xhr) {
                        var html = getHtml(result);
                        $container.html(html);
                        element.init();
                    },
                    error: function (xhr, status, error) {
                        common.cmsError('"LarryCMS error"' + error, 'Nav缓存开启错误！');
                    },
                    complete: function (xhr, status) {
                        _that.config.elem = $container;
                    }
                });
            }
        }

        if (_config.spreadOne) {
            if($container.find('li.layui-nav-item').children('dl').length > 0){
                $container.find('li.layui-nav-item').on('click',function(){
                    if($(this).attr('class').indexOf('layui-nav-itemed') > 0){
                        $(this).removeClass('layui-nav-itemed').children('dl').slideDown(300);
                        $(this).addClass('layui-nav-itemed');
                    }else{
                        $(this).addClass('layui-nav-itemed').children('dl').slideUp(300);
                        $(this).removeClass('layui-nav-itemed');
                    }
                });
            }
            // $container.find('li.layui-nav-item').on('click',function(){
            //
            // });
            $container.find('dl.nav-parent a').on('click', function () {
                if($(this).siblings('ul').attr('class')=="layui-nav-child"){
                    $(this).siblings('ul').addClass('nav-child');
                }else{
                    $(this).siblings('ul').removeClass('nav-child');
                }

            });

        }
        return _that;
    };


    // Navbar设置
    Navbar.prototype.set = function (options) {
        var that = this;
        // 传入参数进行配置
        $.extend(that.config, options);
        return that;
    };

    // Navbar事件处理
    /**
     * @author Larry_qin 2017-05-20
     * @copyright [copyright]
     * @link      http://www.larrycms.com/
     * @version   [version]
     * @license   [license]
     * @param     {[type]}                 events   [description]
     * @param     {Function}               callback [description]
     * @return    {[type]}                          [description]
     */
    Navbar.prototype.on = function (events, callback) {
        var that = this;
        var _con = that.config.elem;
        if (typeof(events) !== 'string') {
            common.cmsError('LarryCMS Nav error:事件名配置出错，请参考API文档.', '事件名配置出错')
        }
        var lIndex = events.indexOf('(');
        var eventName = events.substr(0, lIndex);
        var filter = events.substring(lIndex + 1, events.indexOf(')'));
        if (eventName === 'click') {
            if (_con.attr('lay-filter') !== undefined) {
                _con.find('li').each(function () {
                    var $this = $(this);
                    if ($this.find('dl').length > 0) {
                        var $dd = $this.find('dl').each(function () {
                            if($this.find('li').length > 0){
                                var $li = $this.find('li').each(function () {
                                    $(this).on('click', function () {
                                        var $a = $(this).children('a');
                                        var href = $a.data('url');
                                        var icon = $a.children('i:first').data('icon');
                                        var name = $a.children('cite').text();
                                        var data = {
                                            elem: $a,
                                            field: {
                                                href: href,
                                                icon: icon,
                                                title: name
                                            }
                                        };
                                        callback(data);
                                    });
                                });

                            }else{
                                var $dl = $this.find('dl').each(function () {
                                    $(this).on('click', function () {
                                        var $a = $(this).children('a');
                                        var href = $a.data('url');
                                        var icon = $a.children('i:first').data('icon');
                                        var name = $a.children('cite').text();
                                        var data = {
                                            elem: $a,
                                            field: {
                                                href: href,
                                                icon: icon,
                                                title: name
                                            }
                                        };
                                        callback(data);
                                    });
                                });
                            }

                        });
                    } else {
                        $this.on('click', function () {
                            var $a = $this.children('a');
                            var href = $a.data('url');
                            var icon = $a.children('i:first').data('icon');
                            var title = $a.children('cite').text();
                            var data = {
                                elem: $a,
                                field: {
                                    href: href,
                                    icon: icon,
                                    title: title
                                }
                            };
                            callback(data);
                        });
                    }
                });
            }
        }
    };

    /**
     * 清除缓存
     */
    Navbar.prototype.cleanCached = function () {
        layui.data(cacheName, null);
        localStorage.clear();
    };
    /**
     * @author Larry_qin 2017-05-20
     * @copyright [copyright]
     * @link      http://www.larrycms.com/
     * @version   [version]
     * @license   [license]
     * @param     {[type]}                 data [获取Html字符串]
     * @return    {[type]}                      [返回Html字符串]
     */
    function getHtml(data) {
        var ulHtml = '';
        for (var i = 0; i < data.length; i++) {
            if (data[i].id !== false && data[i].id !== 'undefined') {
                ulHtml += '<li class="layui-nav-item" data-pid="' + data[i].id + '">';
            } else if (data[i].spread) {
                ulHtml += '<li class="layui-nav-item">';
            } else {
                ulHtml += '<li class="layui-nav-item">';
            }
            //如果有二级菜单
            if (data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0 && !data.ishead) {
                ulHtml += '<a>';
                if (data[i].icon !== undefined && data[i].icon !== '') {
                    if (data[i].icon.indexOf('larry-') !== -1) {
                        ulHtml += '<i class="larry-icon ' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true" ></i>';
                    } else if (data[i].icon.indexOf('icon-') !== -1) {
                        ulHtml += '<i class="iconfont' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true"></i>';
                    }
                    else {
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                    }
                }
                ulHtml += '<cite>' + data[i].name + '</cite>';
                ulHtml += '</a>';
                var item = data[i].children;
                $.each(item,function(j,item2){
                    ulHtml += '<dl class="layui-nav-child nav-parent">';
                        //if(item2.children.length>0){}
                        var dataUrl = (item2.children.length > 0) ? '' : '' + item2.url +''
                        ulHtml += '<a  data-url="'+ dataUrl +'" nav-id="'+item2.id+'"><i class="'+item2.icon+'"></i><cite>'+item2.name+'</cite>'
                        ulHtml += '</a>';
                        //如果有三级菜单
                        if(item2.children !== undefined && item2.children.length > 0){
                            ulHtml += '<ul class="layui-nav-child nav-child" style="padding-left:15px;">';
                            $.each(item2.children,function(k,item3){
                                ulHtml += '<li>'+
                                    '<a href="javascript:;" data-url="'+item3.url+'" nav-id="'+item3.id+'">'+
                                    '<i class="'+item3.icon+'"></i>'+
                                    '<cite>'+item3.name+'</cite>'+
                                    '</a>'+
                                    '</li>';
                            });
                            ulHtml += '</ul>';
                        }

                    ulHtml += '</dl>';
                });


            } else {
                var dataUrl = (data[i].url !== undefined && data[i].url !== '') ? 'data-url="' + data[i].url + '"' : '';
                ulHtml += '<a ' + dataUrl + '>';
                if (data[i].icon !== undefined && data[i].icon !== '') {
                    if (data[i].icon.indexOf('larry-') !== -1) {
                        ulHtml += '<i class="larry-icon ' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true"></i>';
                    } else if (data[i].icon.indexOf('icon-') !== -1) {
                        ulHtml += '<i class="iconfont ' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true"></i>';
                    } else {
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                    }
                }
                ulHtml += '<cite>' + data[i].name + '</cite>';
                ulHtml += '</a>';
            }
            ulHtml += '</li>';
        }

        return ulHtml;
    }

    var larry = new Navbar();

    exports('larry', function (options) {
        return larry.set(options);
    })
    // ulHtml += '<a>';
    // if (data[i].icon !== undefined && data[i].icon !== '') {
    //     if (data[i].icon.indexOf('larry-') !== -1) {
    //         ulHtml += '<i class="larry-icon ' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true" ></i>';
    //     } else if (data[i].icon.indexOf('icon-') !== -1) {
    //         ulHtml += '<i class="iconfont' + data[i].icon + '" data-icon="' + data[i].icon + '" aria-hidden="true"></i>';
    //     }
    //     else {
    //         ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
    //     }
    // }
    // ulHtml += '<cite>' + data[i].name + '</cite>';
    // ulHtml += '</a>';
    // //var child = data[i].children;
    // ulHtml += '<dl>';
    // for (var j = 0; j < data[i].children.length; j++) {
    //     ulHtml += '<dt class="layui-nav-item">';
    //     ulHtml += '<a data-url="' + data[i].children[j].url + '">';
    //     if (data[i].children[j].icon !== undefined && data[i].children[j].icon !== '') {
    //         if (data[i].children[j].icon.indexOf('larry-') !== -1) {
    //             ulHtml += '<i class="larry-icon ' + data[i].children[j].icon + '" data-icon="' + data[i].children[j].icon + '" aria-hidden="true" ></i>';
    //         } else if (data[i].icon.indexOf('icon-') !== -1) {
    //             ulHtml += '<i class="iconfont' + data[i].children[j].icon + '" data-icon="' + data[i].children[j].icon + '" aria-hidden="true"></i>';
    //         } else {
    //             ulHtml += '<i class="layui-icon" data-icon="' + data[i].children[j].icon + '">' + data[i].children[j].icon + '</i>';
    //         }
    //     }
    //     ulHtml += '<cite>' + data[i].children[j].name + '</cite>';
    //     ulHtml += '</a>';
    //     ulHtml += '</dt>';
    // }
    // ulHtml += '</dl>';
})