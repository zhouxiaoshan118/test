<#include "../../common/all.ftl">
<!DOCTYPE html>
<html>
<@head title="字典列表"></@head>
<@body>
<div class="panel panel-default col-sm-12">
  <form action="#" id="searchForm" class="form-inline">
    <div class="form-group">
      <label class="control-label" for="search_type">类型：</label>
      <input type="text" class="form-control" name="type" id="search_type"/>
    </div>
    <div class="form-group">
      <label class="control-label" for="search_label">标签：</label>
      <input type="text" class="form-control" name="label" id="search_label"/>
    </div>
    <div class="form-group">
      <a class="btn btn-success" id="reset_btn">清空</a>
      <a class="btn btn-primary" id="search_btn">查询</a>
    </div>
  </form>
</div>
<table id="tb" class="display">
    <thead>
    <tr>
        <th><input type="checkbox" id="select" /></th>
        <th>类型</th>
        <th>标签</th>
        <th>值</th>
        <th>描述</th>
        <th>排序</th>
        <th>操作</th>
    </tr>
    </thead>
</table>
<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
    <button id="btn_add" type="button" class="btn btn-primary" data-target="#modalForm">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增
    </button>
    <button id="btn_edit" type="button" class="btn btn-info s-only-one" disabled="disabled">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
    </button>
    <button id="btn_delete" type="button" class="btn btn-warning s-gt-one" disabled="disabled">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
    </button>
</div>

<div class="modal inmodal fade" id="modalForm" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">编辑字典</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal m-t" id="signupForm" novalidate="novalidate" method="post" action="#">
          <input id="id" type="hidden" />
          <div class="form-group">
            <label for="type" class="col-sm-3 control-label">类型：</label>
            <div class="col-sm-8">
              <input id="type" name="type" class="form-control" type="text" maxlength="64" required>
            </div>
            <i class="req">*</i>
          </div>
          <div class="form-group">
            <label for="label" class="col-sm-3 control-label">标签：</label>
            <div class="col-sm-8">
              <input id="label" name="label" class="form-control" type="text" required>
            </div>
            <i class="req">*</i>
          </div>
          <div class="form-group">
            <label for="value" class="col-sm-3 control-label">值：</label>
            <div class="col-sm-8">
              <input id="value" name="value" class="form-control" type="text" required>
            </div>
            <i class="req">*</i>
          </div>
          <div class="form-group">
            <label for="mark" class="col-sm-3 control-label">描述：</label>
            <div class="col-sm-8">
              <input id="mark" name="mark" class="form-control" type="text">
            </div>
          </div>
          <div class="form-group">
            <label for="weight" class="col-sm-3 control-label">排序：</label>
            <div class="col-sm-8">
              <input id="weight" name="weight" class="form-control" type="text" digits="true" max="2147483646">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="add-btn">添加</button>
        <button type="button" class="btn btn-primary" id="update-btn">修改</button>
      </div>
    </div>
  </div>
</div>
<div class="masks" style="display: none;"></div>
</@body>
<script type="text/javascript">
    $().ready(function () {
        var currentURL = "${adminPath}/sys/dict/";

//        _table.init({
//                        url: currentURL,
//                        columns: [
//                            {title: '类型', field: 'type', sortable: true},
//                            {title: '标签', field: 'label', sortable: true},
//                            {title: '值', field: 'value', sortable: true},
//                            {title: '描述', field: 'mark', sortable: true},
//                            {title: '排序', field: 'weight', sortable: true}
//                        ]
//                    }, function () {
//            return {
//                type : $("#search_type").val(),
//                label : $("#search_label").val()
//            };
//        });
//
//        _table.bind.callbackHandler = function (res) {
//            var data = res.rows;
//            for (var i = 0; i < data.length; i++) {
//                data[i].type = '<a  class="btn_view" id="' + data[i].id + '">' + data[i].type + '</a>';
//            }
//        };
//
//        jQuery.validator.addMethod("s_name", function(value, element, param) {
//            var card = /^[\w]{1,64}$/;
//
//            return card.test(value);
//        }, "必须为 1-64 个任意大小写字母、数字和下划线");
//        $("#type").rules("add", {
//            s_name: true
//        });
//
//        _sbom.add(currentURL, function () {
//            return {
//                type: $("#type").val(),
//                label: $("#label").val(),
//                value: $("#value").val(),
//                weight: $("#weight").val(),
//                mark: $("#mark").val()
//            };
//        });
//
//        _sbom.addBtn();
//
//        _sbom.edit(currentURL, function () {
//            return {
//                type: $("#type").val(),
//                label: $("#label").val(),
//                value: $("#value").val(),
//                weight: $("#weight").val(),
//                mark: $("#mark").val()
//            };
//        });
//
//        _sbom.viewBtnClass(currentURL, function (data) {
//            $("#id").val(data.id);
//            $("#type").val(data.type);
//            $("#label").val(data.label);
//            $("#value").val(data.value);
//            $("#mark").val(data.mark);
//            $("#weight").val(data.weight);
//        });
//
//        _sbom.editBtn(currentURL, function (data) {
//            $("#id").val(data.id);
//            $("#type").val(data.type);
//            $("#label").val(data.label);
//            $("#value").val(data.value);
//            $("#mark").val(data.mark);
//            $("#weight").val(data.weight);
//        });
//
//        _sbom.del(currentURL);
    });



    // ------------------------------------






    $(document).ready(function(){
        grid.init('tb', '${adminPath}/sys/dict/list.json');
    });
</script>
</html>
