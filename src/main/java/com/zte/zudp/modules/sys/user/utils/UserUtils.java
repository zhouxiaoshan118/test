package com.zte.zudp.modules.sys.user.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;

import com.zte.zudp.common.utils.EncryptUtils;
import com.zte.zudp.common.utils.SpringUtils;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.service.UserService;
import com.zte.zudp.system.cache.CacheUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class UserUtils {

    private static UserService userService = SpringUtils.getBean(UserService.class);

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录的用户
     *
     * @return 当前登录的用户信息
     */
    public static User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        String loginName = (String) subject.getPrincipal();
        User user = null;

        if (loginName != null) {
            user = (User) CacheUtils.get(CacheUtils.USER_CACHE, loginName);
        }

        if (user == null) {
            user = userService.getUserByLoginName(loginName);
            CacheUtils.put(CacheUtils.USER_CACHE, loginName, user);
        }

        return user;
    }

    /**
     * 获取当前登录的用户的 Session 对象
     *
     * @return 当前登录用户的 Session 对象，如果不存在则创建，如果无法创建则抛出异常
     */
    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        if (session == null) {
            throw new SessionException();
        }

        return session;
    }

    /**
     * 将 登录名、密码和salt一起MD5加密
     *
     * @param loginName .
     * @param password  .
     * @param salt      .
     * @return 加密后的字符数组
     */
    public static char[] encrypt(String loginName, char[] password, String salt) {
        StringBuilder msg = new StringBuilder(loginName).append(password).append(salt);
        byte[] bytes = EncryptUtils.toMD5(msg);
        return Hex.encodeHex(bytes);
    }

    /**
     * 简化版的加密密码，直接调用 {@link UserUtils#encrypt(String, char[], String)}
     *
     * @param account 要加密密码的账户，注意登录名、密码、salt值都不能为空
     */
    public static User encrypt(final User account) {
        char[] encrypt = encrypt(account.getLoginName(), account.getPassword().toCharArray(), account.getSalt());
        account.setPassword(new String(encrypt));

        return account;
    }
}
