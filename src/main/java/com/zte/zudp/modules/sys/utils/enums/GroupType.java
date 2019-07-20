package com.zte.zudp.modules.sys.utils.enums;

import com.zte.zudp.common.enums.IntValueEnum;

/**
 * 组的分类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public enum GroupType implements IntValueEnum {
    COMMITTEE(0, "党委", "COMMITTEE"),
    TOTAL_BRANCH(1, "总支", "TOTAL_BRANCH"),
    BRANCH(2, "党支部", "BRANCH"),
    UNION_BRANCH(3, "联合党支部", "UNION_BRANCH"),
    GROUP(4, "党小组", "GROUP"),
    FLOW_BRANCH(5, "流动党支部", "FLOW_BRANCH");

    private int value;

    private String name;

    private String desc;

    GroupType(int value, String name, String desc) {
        this.value = value;
        this.name = name;
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
