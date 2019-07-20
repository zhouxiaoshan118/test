package com.zte.zudp.modules.sys.user.dao;

import java.util.Set;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.modules.sys.user.entity.User;

/**
 * 用户的DAO
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * 通过登录名查找用户，此处系统默认登录名不可重复
     *
     * @param loginName 用户登录名
     * @return 用户信息
     */
    User getUserByLoginName(String loginName);

    /**
     * 查找当前用户所有角色的ID
     *
     * @param user 当前用户
     * @return 角色ID的集合
     */
    Set<String> findRoleIds(User user);

    /**
     * 通过登录名查找账户
     */
    User findAccountByLoginName(String loginName);

    /**
     * 修改密码
     */
    void changePassword(User user);

    /**
     * 更新登录信息
     */
    void updateLoginInfo(User user);
    
    /**
     * APP手机端修改密码
     */
    void modifyPassword(User user);
}
