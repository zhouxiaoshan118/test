package com.zte.zudp.system.cache;

/**
 * 缓存类型
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-04.
 */
public enum CacheType {
    LOGIN_RECORD("loginRecord"),
    USER("userCache"),      // 记录 user 缓存
    SYS("sysCache"),
    SYS_USERCACHE("sysUserCache"),
    SYS_AUTHCACHE("sysAuthCache"),
    SYS_MENUCACHE("sysMenuCache"),
    AUTHORIZATIONCACHE("org.apache.shiro.realm.UserRealm.authorizationCache"),
    ACTIVESESSIONSCACHE("activeSessionsCache");

    CacheType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
