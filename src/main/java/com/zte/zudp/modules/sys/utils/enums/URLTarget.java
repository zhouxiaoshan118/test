package com.zte.zudp.modules.sys.utils.enums;

import com.zte.zudp.common.enums.IntValueEnum;

/**
 * 链接 URL 打开的方式
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-15.
 */
public enum URLTarget implements IntValueEnum {
    MAINFRAME(0, "mainFrame", ""),
    BLANK(1, "_blank", ""),
    SELF(2, "_self", ""),
    PARENT(3, "_parent", ""),
    TOP(4, "_top", "");

    private int value;

    private String name;

    private String desc;

    URLTarget(int value, String name, String desc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int getValue() {
        return value;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}
