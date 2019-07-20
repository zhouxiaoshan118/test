package com.zte.zudp.modules.sys.user.service;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.user.dao.UserDao;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.utils.UserUtils;
import com.zte.zudp.modules.sys.utils.enums.AccountStatus;
import com.zte.zudp.system.cache.CacheUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BusinessService<User> {

    private UserDao dao() {
        return (UserDao) dao;
    }

    @Override
    @Transactional
    protected void pre(User user) {
        super.pre(user);

        CacheUtils.clear(CacheUtils.USER_CACHE);
    }

    @Override
    protected void preInsert(User user) {
        super.preInsert(user);

        user.randomSalt();
        UserUtils.encrypt(user);
        user.setLastAccessHost(ServletUtils.getRemoteHost());
        user.setLastAccessTime(new Date());
    }

    @Override
    @Transactional()
    protected void preUpdate(User user) {
        super.preUpdate(user);

        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.randomSalt();
            UserUtils.encrypt(user);
        }
    }

    /**
     * 管理员修改
     * 修改个人信息或账户信息
     */
    @Transactional()
    public void update(User user, AccountStatus status, Boolean admin) {
        if (status != null) {
            user.setStatus(status);
        }

        if (admin != null) {
            user.setAdmin(admin);
        }

        save(user);
    }

    /**
     * 通过登录名获取用户信息
     */
    public User getUserByLoginName(String loginName) {
        return dao().getUserByLoginName(loginName);
    }

    /**
     * 登录成功，更新个人信息
     */
    void loginSuccess(User user) {
        user.setLastAccessTime(new Date());
        user.setLastAccessHost(ServletUtils.getIPAddr());

        dao().updateLoginInfo(user);
    }

    void updatePassword(User user) {
        dao().changePassword(user);
    }

    public Set<String> findRoleIds(User user) {
        return dao().findRoleIds(user);
    }
}
