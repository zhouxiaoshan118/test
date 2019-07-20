package com.zte.zudp.modules.sys.user.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.menu.entity.Menu;
import com.zte.zudp.modules.sys.menu.service.MenuService;
import com.zte.zudp.modules.sys.role.entity.Role;
import com.zte.zudp.modules.sys.role.service.RoleService;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.exception.AccountExistsException;
import com.zte.zudp.modules.sys.user.exception.PasswordNotMatchException;
import com.zte.zudp.modules.sys.user.exception.PasswordRetryLimitExceedException;
import com.zte.zudp.modules.sys.user.exception.UserNoExistsException;
import com.zte.zudp.modules.sys.user.utils.UserUtils;
import com.zte.zudp.modules.sys.user.utils.ValidateUtils;
import com.zte.zudp.modules.sys.utils.enums.AccountStatus;
import com.zte.zudp.system.cache.CacheType;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
@Service
public class PasswordService {

    public static final String LOGIN_RECORD_CACHE = CacheType.LOGIN_RECORD.getName();

    private final MenuService menuService;
    private final UserService userService;
    private final RoleService roleService;

    private final CacheManager ehcacheCacheManager;
    private Cache loginRecordCache;

    @Value(value = "${user.password.captchaEnabledCount}")
    private int captchaEnabledCount;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 10;

    @Autowired
    public PasswordService(CacheManager ehcacheCacheManager, UserService userService,
                           RoleService roleService, MenuService menuService) {
        this.ehcacheCacheManager = ehcacheCacheManager;
        this.userService = userService;
        this.roleService = roleService;
        this.menuService = menuService;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheCacheManager.getCache(LOGIN_RECORD_CACHE);
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    /**
     * 登录验证
     *
     * @return 登录成功的账户
     */
    @Transactional()
    public User login(String loginName, char[] password) {
        if (!ValidateUtils.isLoginName(loginName)) {
            throw new UserNoExistsException();
        }

        if (!ValidateUtils.isPassword(String.valueOf(password))) {
            throw new PasswordNotMatchException();
        }

        captcha();
        User user = userService.getUserByLoginName(loginName);
        if (user == null) {
            throw new UserNoExistsException();
        }

        validate(user, password);
        ValidateUtils.checkStatus(user.getStatus());

        userService.loginSuccess(user);
        return user;
    }

    private void captcha() {
        Object o = loginRecordCache.get(ServletUtils.getIPAddr() + "_num");
        if (o == null) {
            loginRecordCache.put(new Element(ServletUtils.getIPAddr() + "_num", 1));
        } else {
            Integer count = (Integer) ((Element) o).getObjectValue();
            loginRecordCache.put(new Element(ServletUtils.getIPAddr() + "_num", count + 1));
            if (count >= captchaEnabledCount) {
                loginRecordCache.put(new Element(ServletUtils.getIPAddr(), true));
            }
        }
    }

    /**
     * 注册方法
     */
    @Transactional()
    public void register(User user) {
        if (userService.getUserByLoginName(user.getLoginName()) != null) {
            throw new AccountExistsException();
        }

        ValidateUtils.checkLoginName(user.getLoginName());
        ValidateUtils.checkPassword(user.getPassword());

        user.randomSalt();
        UserUtils.encrypt(user);
        user.setStatus(AccountStatus.FREEZED);
        user.setLastAccessHost(ServletUtils.getRemoteHost());
        user.setLastAccessTime(new Date());

        userService.save(user);
    }

    /**
     * 修改密码
     *
     * @return 如果新旧密码匹配则修改密码，否则不修改
     */
    @Transactional()
    public boolean changePassword(User newUser, String newPassword, String oldPassword) {
        User oldUser = userService.get(newUser.getId());

        char[] encrypt = UserUtils.encrypt(oldUser.getLoginName(), oldPassword.toCharArray(), oldUser.getSalt());
        if (!oldUser.getPassword().equals(new String(encrypt))) {
            return false;
        }

        oldUser.setPassword(newPassword);
        oldUser.randomSalt();
        UserUtils.encrypt(oldUser);
        userService.updatePassword(oldUser);
        return true;
    }

    /**
     * 查找指定用户的所拥有的所有系统角色名
     */
    public Set<String> findStringRoles(User account) {
        Set<Role> activedRoles = findRoles(account);

        Set<String> roles = new HashSet<>();
        for (Role activedRole : activedRoles) {
            roles.add(activedRole.getRole());
        }
        return roles;
    }

    /**
     * 通过指定账户查找其拥有的所有可用的角色
     *
     * @param account .
     * @return 所有可用的角色
     */
    public Set<Role> findRoles(User account) {
        Set<String> roleIds = userService.findRoleIds(account);
        return roleService.findActivedRoles(roleIds);
    }

    /**
     * 查找指定账户下所有可用的权限集合
     *
     * @param account 指定的账户
     * @return 所有可用的权限集合
     */
    public Set<String> findPermissions(User account) {
        Set<String> permissions = new HashSet<>();

        Set<Role> roles = findRoles(account);
        for (Role role : roles) {
            Set<String> permissionIds = findPermissionIds(role);
            for (String permissionId : permissionIds) {
                Menu menu = menuService.get(permissionId);
                if (menu != null && StringUtils.isNotEmpty(menu.getPermission())) {
                    permissions.add(menu.getPermission());
                }
            }
        }
        return permissions;
    }

    /**
     * 查找所有权限的id集合
     *
     * @param role 。
     * @return 可用的权限id集合
     */
    private Set<String> findPermissionIds(Role role) {
        return roleService.findActivedPermissionIds(role);
    }

    /**
     * 校验账户的用户名密码是否匹配
     *
     * @param account  账户信息
     * @param password 用户输入的密码
     */
    private void validate(User account, char[] password) {
        String loginName = account.getLoginName();
        String salt = account.getSalt();

        int retryCount = 0;

        Element element = loginRecordCache.get(loginName);
        if (element != null) {
            retryCount = ((Integer) element.getObjectValue());

            if (retryCount > maxRetryCount) {
                throw new PasswordRetryLimitExceedException(maxRetryCount);
            }
        }

        char[] encrypt = UserUtils.encrypt(loginName, password, salt);

        if (!new String(encrypt).equals(account.getPassword())) {
            loginRecordCache.put(new Element(loginName, ++retryCount));
            throw new PasswordNotMatchException();
        }

        clearLoginRecord(loginName);
    }

    /**
     * 登录成功则清除登录次数的记录
     *
     * @param loginName 登录次数记录的缓存 key。
     */
    private void clearLoginRecord(String loginName) {
        loginRecordCache.remove(loginName);
    }
}
