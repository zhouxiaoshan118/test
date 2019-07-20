package com.zte.zudp.modules.sys.utils.enums;

/**
 * 账户所处的状态
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public enum AccountStatus {
    /* 通过邮件、手机号码已确认 */
    ACTIVED(0, "激活"),
    /* 未通过邮件、手机号码确认，账户注册后默认状态 */
    FREEZED(1, "冻结"),
    /* 账户禁止登录 */
    BLOCKED(2, "封禁");

    private String info;

    private int value;

    AccountStatus(int value, String info) {
        this.value = value;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
