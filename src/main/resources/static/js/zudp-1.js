var language = {
    sProcessing: "处理中...",
    sLengthMenu: "显示 _MENU_ 项结果",
    sZeroRecords: "没有匹配结果",
    sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
    sInfoFiltered: "(由 _MAX_ 项结果过滤)",
    sInfoPostFix: "",
    sSearch: "搜索:",
    sUrl: "",
    sEmptyTable: "未搜索到数据",
    sLoadingRecords: "载入中...",
    sInfoThousands: ",",
    oPaginate: {
        sFirst: "首页",
        sPrevious: "上页",
        sNext: "下页",
        sLast: "末页"
    },
    oAria: {
        sSortAscending: ": 以升序排列此列",
        sSortDescending: ": 以降序排列此列"
    }
};

var employee = {};

employee.property = {
    version: "v1.0",
    name: "employee",
    tableId: "tb",
    checkAllId: "employeeCheckAll",
    buttonId: "employeeButtonId",
    formId: "employeeForm",
    corporateFormId: "employeeForm",
    returnStatus: "SUCCESS",
    returnTitle: "操作成功",
    statusTitle: "请选择一条数据！",
    idFailure: "获取id失败",
    prompt: "提示",
    pleaseConfirm: "请确认！",
    wantToDelete: "您确定要删除吗?",
    sexMan: "男",
    sexWoman: "女",
    isTest: "是",
    noTest: "否",
    banned: "禁用",
    enable: "启用"
};

//初始化配置

employee.gridInit = {
    searching: true,
    lengthChange: true,
    paging: true,
    scrollCollapse: true,
    serverSide: false,
    search: true,
    processing: true,
    scrollY: 500,
    scrollX: "100%",
    scrollXInner: "100%",
    scrollCollapse: true,
    jQueryUI: false,
    autoWidth: true,
    autoSearch: false
};

//路径配置

// employee.url = "${adminPath}";
employee.requestUrl = {
    // queryList: employee.url + "/sys/dict/list.json"
};

employee.search = {
    uuid: ""
};

employee.status = [
    {"searchable": false, "orderable": false, "targets": 0},//第一行不进行排序和搜索

//        {"targets": [12], "visible": false},    //设置第13列隐藏/显示

//        {"width": "10%", "targets": [1]},  //设置第2列宽度

//        {

//            对第7列内容进行替换处理

//            targets: 6,

//            render: function (data, type, row, meta) {

//                if (data == "1") {

//                    return employee.table.sexMan;

//                }

//                if (data == "0") {

//                    return employee.table.sexWoman;

//                }

//            }

//        },

    {defaultContent: '', targets: ['_all']} //所有列设置默认值为空字符串

];

employee.filed = [
    {
        "data": "id",
        "createdCell": function (nTd, sData, oData, iRow, iCol) {
            $(nTd).html("<input type='checkbox' name='checkList' value='" + sData + "'>");
        }
    },
    {"data": "type"},
    {"data": "label"},
    {"data": "value"},
    {"data": "mark"},
    {"data": "weight"},
    {
        "data": "", "createdCell": function (nTd, sData, oData, iRow, iCol) {
        $(nTd).html("<button class='btn btn-success'>操作</button>");
    }
    }
];

employee.buttons =
    '<button class="btn btn-default"  type="button" id="reload" data-toggle="modal" data-target="#employeeModal">刷新表格</button>'
    +
    '<button class="btn btn-primary" type="button" id="batchIds" style="margin-left:20px;" data-toggle="modal" >多选</button>'
    +
    '<button class="btn btn-primary" type="button" id="selection" style="margin-left:20px;" data-toggle="modal" >单选</button>'
    +
    '<button class="btn btn-default" type="button" id="search" style="margin-left:20px;" data-toggle="modal" >查询</button>'
    +
    '<button class="btn btn-danger" type="button" id="clearSearch" style="margin-left:20px;" data-toggle="modal" >重置</button>';

var eloancn = {};

eloancn.table = {
    grid: "",
    statusTitle: "请选择一条数据！"
};

//dataTables方法封装
var grid = (function () {

    var baseSettings = {
        searching: true,
        lengthChange: true,
        paging: true,
        processing: true,
        scrollCollapse: true,
        serverSide: false,
        scrollY: 500,
        scrollX: "100%",
        scrollXInner: "100%",
        scrollCollapse: true,
        jQueryUI: false,
        autoWidth: true
        // autoSearch: false,
        // search: true
    };

    var ajax = function (url) {
        return {
            url: url,
            dataSrc: "result",
            error: function (jqXHR, textStatus, errorMsg) {
                alert("请求失败");
            }
        }
    };

    var init = function (tableId, url) {
        baseSettings.ajax = ajax(url);
        baseSettings.dom = "<'row'<'col-sm-2'l><'#" + employee.property.buttonId
                           + ".col-sm-10'><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>";
        baseSettings.columns = employee.filed;
        baseSettings.columnDefs = employee.status;
        baseSettings.language = language;
        // baseSettings.initComplete = function () {
        //     $("#" + employee.property.buttonId).append(employee.buttons);
        //     if (employee.gridInit.search) {
        //         $("input[type='search']").parent().hide();
        //         //隐藏默认的搜索框
        //         // $search.parent().hide();
        //     }
        //     //加载完成之后 初始化checkbox
        //     checkbox(tableId);
        //
        //     $("#reload").click(function () {
        //         reload();
        //     });
        //
        //     $("#batchIds").click(function () {
        //         batchIds();
        //     });
        //
        //     $("#selection").click(function () {
        //         selection();
        //     });
        //     $("#search").click(function () {
        //         search();
        //     });
        //     $("#clearSearch").click(function () {
        //         clearSearch("form-controlSearch");
        //     });
        //
        //     //checkbox全选
        //     $("#" + employee.property.checkAllId).click(function () {
        //         if ($(this).prop("checked")) {
        //             $("input[name='checkList']")
        //                 .prop("checked", true);
        //             $("tr").addClass('selected');
        //         } else {
        //             $("input[name='checkList']")
        //                 .prop("checked", false);
        //             $("tr").removeClass('selected');
        //         }
        //     });
        //
        // };

        eloancn.table.grid = $('#' + tableId).DataTable(baseSettings);

        //回调，如果返回的时候有问题提示信息
        eloancn.table.grid.on('xhr.dt', function (e, settings, json, xhr) {
            console.log("重新加载了数据");
            console.log(json);
        });

        //鼠标经过高亮
        // var lastIdx = null;
        // eloancn.table.grid.on('mouseover', 'td', function () {
        //
        //     if (typeof(eloancn.table.grid.cell(this).index()) != "undefined") {
        //         var colIdx = eloancn.table.grid.cell(this).index().column;
        //         if (colIdx !== lastIdx) {
        //             $(eloancn.table.grid.cells().nodes()).removeClass('highlight');
        //             $(eloancn.table.grid.column(colIdx).nodes()).addClass('highlight');
        //         }
        //     }
        // });

        // eloancn.table.grid.on('mouseleave', function () {
        //     $(eloancn.table.grid.cells().nodes()).removeClass('highlight');
        // });

        //自动搜索方法
        // $('.form-controlSearch').on('keyup change', function () {
        //     elo.gridInit.autoSearch = $("#autoSearch").prop("checked");
        //     if (elo.gridInit.autoSearch) {
        //         filterColumn($(this).attr('data-column'));
        //     }
        // });
    };

    return {
        init: init
    };
})();

function dataTablesInit(elo, url) {

    //错误信息提示
    // $.fn.dataTable.ext.errMode = function (s, h, m) {
    //     if (h == 1) {
    //         alert("连接服务器失败！");
    //     } else if (h == 7) {
    //         alert("返回数据错误！");
    //     }
    // };

}

//选中一行 checkbox选中
function checkbox(tableId) {
    //每次加载时都先清理
    $('#' + tableId + ' tbody').off("click", "tr");
    $('#' + tableId + ' tbody').on("click", "tr", function () {
        $(this).toggleClass('selected');
        if ($(this).hasClass("selected")) {
            $(this).find("input").prop("checked", true);
        } else {
            $(this).find("input").prop("checked", false);
        }
    });

}

//按钮搜索方法
function search() {

    var oSettings = "";
    $("[data-column]").each(function () {
        var filedValue = $(this).attr('data-column');
        if (filedValue != "") {
            console.log($('#col' + filedValue + '_filter').val());
            oSettings = eloancn.table.grid.column(filedValue).search(
                $('#col' + filedValue + '_filter').val()
            );
        }
    });
    //搜索的数据一次条件，节省资源
    eloancn.table.grid.draw(oSettings);
}

//搜索
function filterColumn(i) {

    eloancn.table.grid.column(i).search(
        $('#col' + i + '_filter').val()
    ).draw();
}

//清理搜索数据
function clearSearch(id) {

    $("." + id).each(function () {
        $(this).val("");
    });
    //清空查询条件重新读取数据
    eloancn.table.grid.columns().search("").draw();
}

//获取所有选中行的UUID
function batchIds() {

    var uuid = '';
    var uuids = eloancn.table.grid.rows(".selected").data();
    if (uuids.length == 0) {
        alert(eloancn.table.statusTitle);
    } else {
        for (var i = 0; i < uuids.length; i++) {
            uuid = uuid + uuids[i].extn + ",";
        }
        alert(uuid);
    }
}

//单选
function selection() {

    if (eloancn.table.grid.rows(".selected").data().length == 1) {
        var uuid = eloancn.table.grid.row(".selected").data().extn;

        if (uuid == "") {
            alert(eloancn.table.statusTitle);
        } else {
            alert(uuid);
        }

    } else {
        alert(eloancn.table.statusTitle);
    }
}

//刷新页面
//重新加载数据
function reload() {
    eloancn.table.grid.ajax.reload();
}

//销毁table
function destroyDataTable(tableId) {

    var dttable = $('#' + tableId).DataTable();
    dttable.destroy();
}

