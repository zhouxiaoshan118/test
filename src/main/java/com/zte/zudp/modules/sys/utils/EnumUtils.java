package com.zte.zudp.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import com.zte.zudp.modules.sys.utils.enums.AccountStatus;
import com.zte.zudp.modules.sys.utils.enums.URLTarget;

/**
 * Enum 的帮助类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-20.
 */
public class EnumUtils {

    /**
     * 转换 URLTarget 类实例为一个 List 对象
     *
     * @return 获取一个 URLTarget 所有实例的 List 对象
     */
    public static List<URLTarget> getURLTargets() {
        final List<URLTarget> availableTargets = new ArrayList<>();

        availableTargets.add(URLTarget.MAINFRAME);
        availableTargets.add(URLTarget.BLANK);
        availableTargets.add(URLTarget.SELF);
        availableTargets.add(URLTarget.PARENT);
        availableTargets.add(URLTarget.TOP);

        return availableTargets;
    }

    /**
     * 转换 URLTarget 类实例为一个 List 对象
     *
     * @return 获取一个 URLTarget 所有实例的 List 对象
     */
    public static List<AccountStatus> getAccountStatus() {
        final List<AccountStatus> accountStatuses = new ArrayList<>();

        accountStatuses.add(AccountStatus.ACTIVED);
        accountStatuses.add(AccountStatus.FREEZED);
        accountStatuses.add(AccountStatus.BLOCKED);

        return accountStatuses;
    }
}
