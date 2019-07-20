package com.zte.zudp.common.enums;

/**
 * 系统中拥有的所有权限操作
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-13.
 */
public enum Permission {
    CREATE("create"),       // 创建
    DELETE("delete"),       // 删除
    UPDATE("update"),       // 更新
    VIEW("view"),           // 查看
    // EDIT("edit"),        // 编辑，包含创建、更新和删除
    ASSIGN("assign"),       // 分配
    ASSOCIATED("associated"),   // 关联
    AUDIT("audit"),         // 审核
    ISSUE("issue"),         //发布
    UPLOAD("upload"),       //上传
    CANCEL("cancel"),       // 撤消
    TRANSFER("transfer"),       // 转接
    DOWNLOAD("download"),       // 下载
    CHANGE("change");           // 变更，对 业务对象 状态变更的操作权限

    private String info;

    Permission(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
