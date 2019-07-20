package com.zte.zudp.system.config.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.exception.PasswordNotMatchException;
import com.zte.zudp.modules.sys.user.exception.PasswordRetryLimitExceedException;
import com.zte.zudp.modules.sys.user.exception.UserBlockedException;
import com.zte.zudp.modules.sys.user.exception.UserException;
import com.zte.zudp.modules.sys.user.exception.UserFreezedException;
import com.zte.zudp.modules.sys.user.exception.UserNoExistsException;
import com.zte.zudp.modules.sys.user.service.PasswordService;
import com.zte.zudp.modules.sys.user.service.UserService;
import com.zte.zudp.system.config.shiro.token.AccountToken;

/**
 * shiro 账户数据源
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    private final PasswordService passwordService;
    private final UserService userService;

    @Autowired
    public UserRealm(PasswordService passwordService, UserService userService) {
        this.passwordService = passwordService;
        this.userService = userService;
    }

    /**
     * 登录认证时触发的方法
     *
     * @param token 账户令牌，包含账户信息
     * @return 包含账户信息的认证信息类
     * @throws AuthenticationException 认证失败时会被抛出
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AccountToken accountToken = (AccountToken) token;

        String loginName = accountToken.getUsername().trim();
        char[] password = accountToken.getPassword();

        User user;

        try {
            user = passwordService.login(loginName, password);
        } catch (UserNoExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (PasswordNotMatchException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (PasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException | UserFreezedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException(new UserException("user.unknown.error", null));
        }

        return new SimpleAuthenticationInfo(user.getLoginName(), password, getName());
    }

    /**
     * 授权时触发的方法
     *
     * @param principals .
     * @return 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) principals.getPrimaryPrincipal();
        User user = userService.getUserByLoginName(loginName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(passwordService.findStringRoles(user));
        authorizationInfo.setStringPermissions(passwordService.findPermissions(user));

        return authorizationInfo;
    }

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals .
     * @param permission 权限拼接字符串
     * @return 是否有权限
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] orPermissions = permission.split(OR_OPERATOR);
            for (String orPermission : orPermissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }

            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] andPermissions = permission.split(AND_OPERATOR);
            for (String andPermission : andPermissions) {
                if (!isPermittedWithNotOperator(principals, andPermission)) {
                    return false;
                }
            }

            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    /**
     * 对 not 运算符的支持
     *
     * @param principals .
     * @param permission .
     * @return 递归检查是否有权限
     */
    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
