$.ajaxSetup({ cache: false });
/**
 * 简单的函数封装
 * @type {{bind}}
 * @private
 */
var _util = (function () {
    /**
     * 事件绑定的处理
     * @param selector 需要绑定的元素选择器
     * @param event 绑定的时间
     * @param callbackFn 回调函数
     */
    var bind = function (selector, event, callbackFn) {
        var $selector = $(selector);
        $selector.unbind(event);
        $selector.bind(event, callbackFn);
    };

    /**
     * 清空表单数据，及校验留下的 css 样式
     */
    var resetForm = function () {
        $("#signupForm input").not("input[type='radio']").not('input[type=checkbox]').val('');
        $("#signupForm select").val('');
        $("#signupForm input[type=radio]").attr("checked", false);
        $("#signupForm input[type=checkbox]").attr('checked', false);
        $("#signupForm textarea").val('');

        $("#modalForm").modal('show');
        $("#add-btn").show();
        $("#update-btn").hide();

        $("#signupForm .has-error").removeClass("has-error");
        $("#signupForm .ckeditor").siblings('span').removeClass("s-error-ckeidtor");
        $("#signupForm label.error").remove();
        $("#signupForm .error").removeClass("error");
        $("#signupForm").validate().resetForm();
    };

    /**
     *
     * @param oper
     */
    var disabledForm = function (oper) {
        $("#signupForm input").attr('disabled', oper);
        $("#signupForm select").attr('disabled', oper);
        $("#signupForm radio").attr('disabled', oper);
        $("#signupForm checkbox").attr('disabled', oper);
        $("#signupForm textarea").attr('disabled', oper);

        $("#signupForm .s-disabled").attr('disabled', true);
        $("#signupForm .s-no-disabled").attr('disabled', false);
    };

    /**
     * 通知
     * @type {{success: success, info: info, warning: warning, error: error}}
     */
    var notice = {
        success: function (text) {
            toastr.success(text);
        },
        info: function (text) {
            toastr.info(text);
        },
        warning: function (text) {
            toastr.warning(text);
        },
        error: function (text) {
            toastr.error(text);
        }
    };

    return {
        bind: bind,
        resetForm: resetForm,
        disabledForm: disabledForm
    };
})();

/**
 * 系统级别的 常用函数
 * @type {{add, addBtn, edit}}
 * @private
 */
var _sbom = (function () {

    /**
     * 初始化
     * @param url 地址
     * @param loadFn 加载函数，即查看和编辑时加载
     * @param resetFn 清空函数
     * @param addFn 提交函数
     * @param editFn 提交函数
     */
    var init = function (url, loadFn, resetFn, addFn, editFn, renewFn) {
        viewBtn(url, loadFn);
        viewBtnClass(url, loadFn);
        add(url, addFn);
        addBtn(resetFn);
        editBtn(url, loadFn);
        edit(url, editFn ? editFn : addFn);
        del(url);
        renewBtn(url, loadFn);
        renew(url, renewFn);
    };

    /**
     * 添加数据到后台的函数
     *  要求添加按钮的id为add-btn，而提交的form id为 signupForm
     * @param url 提交的 url
     * @param data 提交的返回json数据的 函数
     * @param pre
     */
    var add = function (url, data, pre) {
        _util.bind("#add-btn", "click", function () {
            // debugger;
            // $('textarea.ckeditor').each(function () {
            //     debugger;
            //     var $textarea = $(this);
            //     $textarea.val(CKEDITOR.instances[$textarea.attr('name')].getData());
            // });

            if ($("#signupForm").valid()) {
                var fun = function () {
                    $(".spinerbox").show();
                    $.ajax({
                               url: url,
                               type: "post",
                               data: JSON.stringify(data()),
                               contentType:"application/json",
                               complete: function (textStatus, xhr) {
                               },
                               success: function (data, textStatus, xhr) {
                                   if (xhr.status === 201) {
                                       toastr.success('添加成功');
                                       $(".s-only-one").attr("disabled", true);
                                       $(".s-gt-one").attr("disabled", true);
                                       $("#table").bootstrapTable('refresh', {});
                                   } else {
                                       toastr.error('添加失败，服务器出了点小问题');
                                   }
                                   $("#modalForm").modal('hide');
                                   $(".spinerbox").hide();
                               },
                               error: function () {
                                   toastr.error('服务器发生了错误！');
                                   $(".spinerbox").hide();
                               },
                               dataType: "text"
                           });
                };
                if (pre) {
                    pre(fun);
                } else {
                    fun();
                }
            }
        });
    };

    var viewBtn = function (url, view, preView) {
        _util.bind("#btn_view", "click", function () {
            var a= $('#table').bootstrapTable('getSelections');
            if (a.length === 1) {
                var id = a[0].id;
                if (id === null) {
                    toastr.info('无法获取当前选中数据的id！');
                    return;
                }

                if (typeof preView !== 'undefined') {
                    preView(id);
                }
                $.ajax({
                           url: url + id,
                           type: "get",
                           success: function (data, textStatus, xhr) {
                               var d = JSON.parse(data);

                               _util.resetForm();

                               var title = $("#s-title");
                               title.empty();
                               title.html('查看');

                               $(".s-edit-disabled").attr("disabled", true);

                               view(d, xhr);

                               _util.disabledForm(true);

                               $("#modalForm").modal('show');
                               $("#add-btn").hide();
                               $("#update-btn").hide();
                           },
                           error: function () {
                               toastr.error('抱歉，服务器发生了异常！');
                           },
                           dataType: "text"
                       });
            } else if (a.length > 1) {
                toastr.info('只能选中一行哦');
            } else {
                toastr.info('没有选中行哦');
            }
        })
    };

    /**
     * 当不使用查看按钮时调用的方法
     * @param url ...
     * @param view 元素加载方式
     * @param preView 加载之前的操作
     */
    var viewBtnClass = function (url, view, preView) {
        $(document).on('click', '.btn_view', function () {
            var id = this.id;
            if (id === null) {
                toastr.info('无法获取当前选中数据的id！');
                return;
            }

            if (preView) {
                preView(id);
            }

            $.ajax({
                       url: url + id,
                       type: "get",
                       success: function (data, textStatus, xhr) {
                           var d = JSON.parse(data);

                           _util.resetForm();

                           var title = $("#s-title");
                           title.empty();
                           title.html('查看');

                           view(d, xhr);

                           _util.disabledForm(true);

                           $(".s-edit-disabled").attr("disabled", true);

                           $("#modalForm").modal('show');
                           $("#add-btn").hide();
                           $("#update-btn").hide();
                       },
                       error: function () {
                           toastr.error('抱歉，服务器发生了异常！');
                       },
                       dataType: "text"
                   });
        })
    };

    var addBtn = function (fn) {
        _util.bind("#btn_add", "click", function() {
            _util.disabledForm(false);
            _util.resetForm();

            var title = $("#s-title");
            title.empty();
            title.html('新增');
            $(".s-edit-disabled").attr("disabled", false);

            if (fn) {
                fn();
            }
        });
    };

    var edit = function (url, data, pre) {
        _util.bind("#update-btn", "click", function () {
            var val = $("#id").val();
            if ($("#signupForm").valid()) {
                var fn = function () {
                    $(".spinerbox").show();
                    $.ajax({
                               url: url + val,
                               type: "put",
                               data: JSON.stringify(data()),
                               contentType:"application/json",
                               dataType: "text",
                               success: function (data, textStatus, xhr) {
                                   toastr.success('修改成功');
                                   $(".s-only-one").attr("disabled", true);
                                   $(".s-gt-one").attr("disabled", true);
                                   // $("#btn_edit").attr("disabled", true);
                                   // $("#btn_delete").attr("disabled", true);
                                   $("#table").bootstrapTable('refresh', {});

                                   $("#modalForm").modal('hide');
                                   $(".spinerbox").hide();
                               },
                               error: function () {
                                   toastr.error('修改失败，请重试');
                                   $(".spinerbox").hide();
                               }
                           });
                };
                if (pre) {
                    pre(fn);
                } else {
                    fn();
                }
            }
        })
    };

    /**
     *
     * @param url
     * @param edit
     * @param preEdit 在编辑前调用的函数，拥有id参数
     */
    var editBtn = function (url, edit, preEdit) {
        var $edit = $("#btn_edit");
        $edit.unbind("click");
        $edit.bind("click", function () {
            var a= $('#table').bootstrapTable('getSelections');
            if (a.length === 1) {
                var id = a[0].id;
                if (id === null) {
                    toastr.info('无法获取当前选中数据的id！');
                    return;
                }

                if (typeof preEdit !== 'undefined') {
                    preEdit(id);
                }
                $.ajax({
                           url: url + id,
                           type: "get",
                           success: function (data, textStatus, xhr) {
                               var d = JSON.parse(data);

                               _util.disabledForm(false);

                               var title = $("#s-title");
                               title.empty();
                               title.html('修改');
                               $(".s-edit-disabled").attr("disabled", true);

                               _util.resetForm();

                               edit(d, xhr);

                               $("#modalForm").modal('show');
                               $("#add-btn").hide();
                               $("#update-btn").show();
                           }, error: function () {
                        toastr.error('抱歉，服务器发生了异常！');
                    },
                           dataType: "text"
                       });
            } else if (a.length > 1) {
                toastr.info('只能选中一行哦');
            } else {
                toastr.info('没有选中行哦');
            }
        });
    };

    /**
     * 删除操作
     * @param url
     */
    var del = function (url) {
        _util.bind("#btn_delete", "click", function () {
            var a= $('#table').bootstrapTable('getSelections');
            if (a.length === 1) {
                var id = a[0].id;
                if (id === null) {
                    toastr.info('无法获取当前选中数据的id！');
                } else {
                    swal({
                             title: "你确定要删除吗?",
                             // text: "You will not be able to recover this imaginary file!",
                             type: "warning",
                             showCancelButton: true,
                             confirmButtonColor: "#DD6B55",
                             confirmButtonText: "确定，删除它",
                             cancelButtonText: "取消，不删除它",
                             closeOnConfirm: true
                         }, function (isConfirm) {
                        if (isConfirm) {
                            // swal("正在删除!", "选中的数据正在被删除", "success");
                            _ajax.del(url, id);
                        }
                    });

                }
            } else if (a.length > 1) {
                swal({
                         title: "你确定要删除吗?",
                         type: "warning",
                         showCancelButton: true,
                         confirmButtonColor: "#DD6B55",
                         confirmButtonText: "确定，删除它们",
                         cancelButtonText: "取消，不删除它们",
                         closeOnConfirm: true
                     }, function (isConfirm) {
                    if (isConfirm) {
                        // swal("正在删除!", "选中的数据正在被删除", "success");
                        var ids = [];
                        for (var i = 0; i < a.length; i++) {
                            ids[i] = a[i].id;
                        }

                        _ajax.batchDel(url, ids);
                    }
                });
            } else {
                toastr.info('没有选中行哦');
            }
        });
    };
    
    
    /**
     * @author zss
     * 用于操作更新 jp管理有使用，（弹出新款，操作内容与修改不一样）
     * @param url update preUpdate
     */
    var renewBtn = function (url, update, preUpdate) {
        var $update = $("#btn_renew");
        $update.unbind("click");
        $update.bind("click", function () {
            var a= $('#table').bootstrapTable('getSelections');
            if (a.length === 1) {
                var id = a[0].id;
                if (id === null) {
                    toastr.info('无法获取当前选中数据的id！');
                    return;
                }

                if (typeof preUpdate !== 'undefined') {
                	preUpdate(id);
                }
                $.ajax({
                           url: url + id,
                           type: "get",
                           success: function (data, textStatus, xhr) {
                               var d = JSON.parse(data);

                               _util.disabledForm(false);

                               var title = $("#s-title");
                               title.empty();
                               title.html('变更');

                               _util.resetForm();

                               update(d, xhr);

                               $("#modalForm").modal('show');
                               $("#add-btn").hide();
                               $("#update-btn").hide();
                               $("#renew-btn").show();
                           }, error: function () {
                        toastr.error('抱歉，服务器发生了异常！');
                    },
                           dataType: "text"
                       });
            } else if (a.length > 1) {
                toastr.info('只能选中一行哦');
            } else {
                toastr.info('没有选中行哦');
            }
        });
    };
    
    /**
     *  @author zss
     * 更新页面的更新按钮, 
     * 名为更新，实为先更新前一表，再为新增一条数据到另一表，使用前，请详看服务端功能
     * post put together to use update or save
     */
    var renew = function (url, data, pre) {
    	var type = "post";
        _util.bind("#renew-btn", "click", function () {
        	if(url.substring(url.lastIndexOf("/")+1)=="update"){
        		type = "put";
        		url = url.substring(0,url.lastIndexOf("/")+1)+$('#id').val();
        	}
            if ($("#signupForm").valid()) {
                var fn = function () {
                    $(".spinerbox").show();
                    $.ajax({
                               url: url,
                               type: type,
                               data: JSON.stringify(data()),
                               contentType:"application/json",
                               dataType: "text",
                               success: function (data, textStatus, xhr) {
                                   toastr.success('更新成功, 该入党申请人身份已变化');
                                   $(".s-only-one").attr("disabled", true);
                                   $(".s-gt-one").attr("disabled", true);
                                   // $("#btn_edit").attr("disabled", true);
                                   // $("#btn_delete").attr("disabled", true);
                                   $("#table").bootstrapTable('refresh', {});

                                   $("#modalForm").modal('hide');
                                   $(".spinerbox").hide();
                               },
                               error: function () {
                                   toastr.error('修改失败，请重试');
                                   $(".spinerbox").hide();
                               }
                           });
                };
                if (pre) {
                    pre(fn);
                } else {
                    fn();
                }
            }
        })
    };


    return {
        init : init,
        add : add,
        addBtn : addBtn,
        viewBtn : viewBtn,
        viewBtnClass : viewBtnClass,
        edit : edit,
        editBtn :editBtn,
        del :del,
        renewBtn : renewBtn,
        renew : renew
    };
})();

/**
 * 一些 ajax 函数封装，包括 get、post、put、delete
 * @type {{del}}
 * @private
 */
var _ajax = (function () {
    /**
     * 提示信息消息框
     * @param type 提示信息的类型，当前仅有 success 和 danger
     * @param preText 提示信息
     * @returns {string}
     */
    var message = function(type, preText) {
        if (typeof type !== 'undefined') {
            switch (type) {
                case 'success':
                case 'danger':
                    break;
                default :
                    throw 'type value is invalid!';
            }

            var preDiv = '<div class="alert alert-' + type + ' alert-dismissable">';
            var button = '<button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>';
            return preDiv + button + preText + '。</div>';
        }
    };

    /**
     * 删除单个节点
     * @param url
     * @param id
     */
    var del = function (url, id) {
        $.ajax({
                   url: url + id,
                   type: "delete",
                   success: function (data, textStatus, xhr) {
                       if (xhr.status === 204) {
                           toastr.success('删除成功');
                           $("#btn_edit").attr("disabled", true);
                           $("#btn_delete").attr("disabled", true);
                           $("#table").bootstrapTable('refresh', {});
                       } else {
                           toastr.error('删除失败，请重试');
                       }
                   },
                   error: function () {
                       toastr.error('删除失败，请重试');
                   },
                   dataType: "text"
               });
    };

    /**
     * 批量删除
     * @param url
     * @param ids
     */
    var batchDel = function (url, ids) {
        $.ajax({
                   url: url,
                   type: "delete",
                   data: JSON.stringify(ids),
                   contentType:"application/json",
                   success: function (data, textStatus, xhr) {
                       if (xhr.status === 204) {
                           toastr.success('删除成功');
                           $(".s-only-one").attr("disabled", true);
                           $(".s-gt-one").attr("disabled", true);
                           $("#btn_edit").attr("disabled", true);
                           $("#btn_delete").attr("disabled", true);
                           $("#table").bootstrapTable('refresh', {});
                       } else {
                           toastr.error('删除失败，请重试');
                       }
                   },
                   error: function () {
                       toastr.error('删除失败，请重试');
                   },
                   dataType: "text"
               });
    };
    //批量取消发布或者发布
    var issueAll = function (url, ids, issue) {
        $.ajax({
                   url: url+issue,
                   type: "post",
                   data: JSON.stringify(ids),
                   contentType:"application/json",
                   success: function (data, textStatus, xhr) {
                       var message="取消发布";
                       data=JSON.parse(data);
                       if(issue=="1"){
                           message="发布"
                       }
                       if (data.length ==0) {
                           toastr.success(message+"成功");
                           $("#table").bootstrapTable('refresh', {});
                       } else {
                           var  html="其他发布成功，发布失败的信息如下:</br>"
                           $.each(data,function (i,obj) {
                               html+=obj+"</br>";
                           });
                           toastr.error(html);
                           $("#table").bootstrapTable('refresh', {});
                       }
                   },
                   dataType: "text"
               });
    };

    /**
     * 对 get ajax 请求的封装
     * @param url 请求地址
     * @param callbackFn 回调函数，会传入已经转为json的后台数据
     */
    var get = function (url, callbackFn) {
        $.ajax({
                   url: url,
                   type: "get",
                   contentType:"application/json",
                   dataType: "text",
                   success: function (da, textStatus, xhr) {
                       var d = JSON.parse(da);
                       if (d) {
                           callbackFn(d);
                       }
                   }
               });
    };
    return {
        msg : message,
        del : del,
        batchDel : batchDel,
        get : get,
        issueAll :issueAll
    };
})();

/**
 * 此变量用于处理 bootstrap-table 插件的业务逻辑
 * @type {{callback, init}}
 * @private
 */
var _table = (function () {

    var $table = $('#table');           // 表格对象
    var $editBtn = $("#btn_edit");      // 修改按钮
    var $delBtn = $("#btn_delete");

    /**
     * 当插件获取到服务器数据时调用此方法，用以处理服务器数据的格式化等工作
     * @param result
     * @param setting
     * @returns {*}
     */
    var callback = function (result, setting) {
        for (var i = 0; i < result.rows.length; i++) {
            if (setting.isSort) {
                result.rows[i].sort = (result.current - 1) * result.size + i + 1;
            }
        }
        return result;
    };

    /**
     * 插件初始化的配置
     * @param setting
     * @returns {{method: string, url: string, toolbar: string, striped: boolean, dataField:
     *     string, pageNumber: number, pagination: boolean, queryParamsType: string,
     *     sidePagination: string, sortable: boolean, sortOrder: string, pageSize: number,
     *     pageList: [number,number,number,number], showRefresh: boolean, showColumns: boolean,
     *     clickToSelect: boolean, toolbarAlign: string, buttonsAlign: string, columns: [*],
     *     locale: string, responseHandler: responseHandler}}
     */
    var settingsF = function (setting) {
        var defaultSettings = {
            method: 'get',
            url: "",                //要请求数据的文件路径
            toolbar: '#toolbar',    //指定工具栏
            striped: true,          //是否显示行间隔色
            dataField: "rows",      // 此处使用后端分页，后台需返回 {total: 总记录数; rows: 记录集合 }
            pageNumber: 1,          //初始化加载第一页，默认第一页
            pagination: true,       //是否分页
            queryParamsType: 'limit',//查询参数组织方式
            // queryParams: queryParams,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            sortable: false,   //是否启用排序
            sortOrder: "asc",  //排序方式
            cache: false, // 不缓存
            pageSize: 15,   //单页记录数
            pageList: [5, 10, 15, 20, 30],//分页步进值
            showRefresh: true,  //刷新按钮
            showColumns: true,
            clickToSelect: true,  //是否启用点击选中行
            toolbarAlign: 'right',  //工具栏对齐方式
            buttonsAlign: 'right', //按钮对齐方式
            columns: [
                {title: '全选', field: 'select', checkbox: true, width: 25, align: 'center', valign: 'middle'},
                {title: 'id', field: 'id', visible: false}
            ],
            locale: 'zh-CN',//中文支持,
            responseHandler: function (res) {  //在ajax获取到数据，渲染表格之前，修改数据源
                var result = {};
                result.total = res.total;
                if (typeof res.result === 'undefined') {    // 修复后台查询结果为空时的bug
                    res.result = [];
                }
                result.rows = res.result;
                result.current = res.current;
                result.size = res.size;

                bind.callbackHandler(result);

                return callback(result, setting);
            }
        };

        defaultSettings.queryParams = setting.queryParams;
        defaultSettings.url = setting.url;
        if (setting.overColumns) {
            defaultSettings.columns = setting.columns;
        } else {
            defaultSettings.columns = defaultSettings.columns.concat(setting.columns);
        }

        if (setting.responseHandler !== undefined) {
            defaultSettings.responseHandler = setting.responseHandler;
        }
        if (setting.toolbar !== undefined) {
            defaultSettings.toolbar = setting.toolbar;
        }
        if (typeof setting.showColumns !== 'undefined') {
            defaultSettings.showColumns = setting.showColumns;
        }
        if (typeof setting.showRefresh !== 'undefined') {
            defaultSettings.showRefresh = setting.showRefresh;
        }

        return defaultSettings;
    };

    /**
     * bootstrap-table 插件初始化调用的函数
     * @param settings 插件的配置
     * @param queryParam 查询参数
     * @param $selector 修改默认的table值
     */
    var init = function (settings, queryParam, $selector) {
        // 设置bootstrap-table的请求服务器数据时所传参数 请求服务器时所传的参数
        settings.queryParams = function (params) {
            if (params.limit === undefined) {
                params.limit = 10;
            }
            if (params.offset === undefined) {
                params.offset = 1;
            }

            var no = (params.offset + params.limit) / params.limit;

            var query = queryParam();
            query.page_size = params.limit;        //每页多少条数据
            query.page_no = parseInt(no);     //请求第几页

            return query;
        };

        // 设置列表页
        settings.url += "list";
        var endSettings = settingsF(settings);

        var table = $table;
        if ($selector) {
            table = $selector;

            table.bootstrapTable(endSettings);           // 参数初始化
        } else {
            table = $table;

            table.bootstrapTable(endSettings);           // 参数初始化
            // 查询按钮处理
            _util.bind("#search_btn", "click", function () {
                $(".s-only-one").attr("disabled", true);
                $(".s-gt-one").attr("disabled", true);
                table.bootstrapTable('refresh', {url: endSettings.url});
            });
            // 重置按钮处理
            _util.bind("#reset_btn", "click", function () {
                $("#searchForm").find(':input').not(':button, :submit, :reset').val('')
                    .removeAttr('checked').removeAttr('selected');
            });
        }


        // 表格的选中与反选的操作处理
        table
            .on('check.bs.table', function (e, row, ele) {
                // 选中事件
                var $checked = $("input[name='btSelectItem']:checked");

                $(".s-only-one").attr("disabled", $checked.length !== 1);
                $editBtn.attr("disabled", $checked.length !== 1);

                $delBtn.attr("disabled", $checked.length < 1);
                $(".s-gt-one").attr("disabled", $checked.length < 1);

                $("#id").val(row.id);

                bind.checked(e, row, ele);
            })
            .on('uncheck.bs.table', function (e, row, ele) {
                // 取消选中事件
                var $checked = $("input[name='btSelectItem']:checked");

                $(".s-only-one").attr("disabled", $checked.length !== 1);
                $editBtn.attr("disabled", $checked.length !== 1);

                $delBtn.attr("disabled", $checked.length < 1);
                $(".s-gt-one").attr("disabled", $checked.length < 1);

                bind.unchecked(e, row, ele);
            })
            .on('check-all.bs.table', function (e, dataArr) {
                // 全选事件
                var $checked = $("input[name='btSelectItem']:checked");

                $(".s-only-one").attr("disabled", $checked.length !== 1);
                $editBtn.attr("disabled", $checked.length !== 1);

                $delBtn.attr("disabled", $checked.length < 1);
                $(".s-gt-one").attr("disabled", $checked.length < 1);

                bind.checkedAll(e, dataArr);
            })
            .on('uncheck-all.bs.table', function (e, dataArr) {
                // 取消全选事件

                $(".s-only-one").attr("disabled", true);
                $editBtn.attr("disabled", true);

                $delBtn.attr("disabled", true);
                $(".s-gt-one").attr("disabled", true);

                bind.uncheckedAll(e, dataArr);
            });

        // 启用校验
        $("#signupForm").validate();

        // 通知插件配置
        toastr.options = {
            closeButton: true,
            debug: false,
            progressBar: true,
            positionClass: "toast-top-right",
            onclick: null,
            showDuration: "400",
            hideDuration: "1000",
            timeOut: "7000",
            extendedTimeOut: "1000",
            showEasing: "swing",
            hideEasing: "linear",
            showMethod: "fadeIn",
            hideMethod: "fadeOut"
        };
    };

    var bind = {
        checked: function (e, row, ele) {},
        unchecked: function (e, row, ele) {},
        checkedAll: function (e, row, ele) {},
        uncheckedAll: function (e, row, ele) {},
        /**
         * 当表格从服务器上请求到了所需数据后的回调函数
         * @param res
         */
        callbackHandler: function (res) {}
    };

    return {
        callback: callback,
        init: init,
        bind: bind
    };
})();

/**
 * 框架中插件的封装
 * @type {{ztree}}
 * @private
 */
var _plugins = (function () {
    /**
     * ztree 的简单封装
     * @param initDiv 需要进行 ztree 初始化的 div，需要通过 $() 可以查找到
     * @param url 后台数据链接地址
     * @param callbackFn 事件处理
     * @param settings 其他设置
     */
    var ztree = function (initDiv, url, callbackFn, settings) {


        // ztree 相关
        var defaultSettings = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            // check: {
            //     enable: defaultCheckStatus
            // },
            async: {
                enable: false
            },
            callback: callbackFn
        };

        if (settings) {
            $.extend(defaultSettings, settings);
        }

        $.ajax({
                   url: url,
                   type: "get",
                   success: function (result) {
                       $.fn.zTree.init($(initDiv), defaultSettings, result);
                   }
               });
    };

    /**
     * chosen 插件的各种操作
     * @type {{init: init, reload: reload}}
     * @example
     *  _plugins.chosen.init("/sys/partymember/chosen",
     *                       "#partymember",
     *                       {disable_search: false, width: "95%",create_option: true});
     */
    var chosen = {
        /*
         初始化 chosen 插件
         */
        init: function(url, selector, settings) {
            $.ajax({
                       url: url,
                       type: "get",
                       contentType:"application/json",
                       success: function (data, textStatus, xhr) {
                           var $chosen = $(selector);
                           var d = JSON.parse(data);
                           if (xhr.status === 200) {
                               $chosen.empty();
                               for (var i = 0; i < d.length; i++) {
                                   $chosen.append('<option value="' + d[i].value + '">' + d[i].text + '</option>');
                               }
                               $chosen.chosen(settings);
                           } else {
                               $chosen.append('<option value="">从服务器获取数据失败</option>');
                           }
                       },
                       dataType: "text"
                   });
        },
        /*
         重新加载数据和设置默认值，默认值可以为空
         */
        reload: function (url, selector, defaultObj) {
            _ajax.get(url, function (data) {
                var $select = $(selector);
                $select.empty();
                for (var i = 0; i < data.length; i++) {
                    $select.append('<option value="' + data[i].value + '">' + data[i].text + '</option>');
                }
                if (defaultObj) {
                    $select.append('<option value="' + defaultObj.value + '">' + defaultObj.text + '</option>');
                    $select.val(defaultObj.value).trigger("chosen:updated");
                } else {
                    $select.val('').trigger("chosen:updated");
                }
            });
        }
    };

    var validInit = function () {
        jQuery.validator.addMethod("loginName", function(value, element, param) {
            var card = /^[\w]{3,20}$/;

            return card.test(value);
        }, "登录名必须为3-20 个任意大小写字母、数字和下划线");

        jQuery.validator.addMethod("Card", function(value, element, param) {
            return _validate.card(value);
        }, "身份证格式不正确，请核对！");

        jQuery.validator.addMethod("Phone", function(value, element, param) {
            var phone = /^0?(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$/;
            var m =/[0-9-()（）]{7,18}/;//验证电话号码为7-8位数字并带有区号

            return phone.test(value)||m.test(value);
        }, "手机号码格式不正确，请核对");
        jQuery.validator.addMethod("RPhone", function(value, element, param) {
            var phone = /^0?(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$/;

            return phone.test(value);
        }, "手机号码格式不正确，请核对");
        jQuery.validator.addMethod("Money", function(value, element, param) {
            var money = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

            return money.test(value);
        }, "金额格式不正确，请核对");
        jQuery.validator.addMethod("Score", function(value, element, param) {
            var score = /^(\d+)(\.\d{1})?$/;

            return score.test(value);
        }, "分数格式不正确，只能有一位小数，请核对");
    };

    return {
        ztree: ztree,
        chosen: chosen,
        validInit: validInit
    };
})();
/*
 * 废弃代码
 // 根据窗口调整表格高度
 $(window).resize(function () {
 $('#table').bootstrapTable('resetView', {});
 });
 */