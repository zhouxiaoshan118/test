package com.zte.zudp.modules.sys.user.entity;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.utils.check.FirstCheck;
import com.zte.zudp.modules.sys.role.entity.Role;
import com.zte.zudp.modules.sys.utils.enums.AccountStatus;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 用户信息类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class User extends DateEntity {

    private static final long serialVersionUID = 1L;

    public static final AccountStatus DEFAULT_ACCOUNT_STATUS = AccountStatus.FREEZED;

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 20;

    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 32;

    public static final int SALT_LENGTH = 10;
    public static final Boolean DEFAULT_ADMIN_STATUS = Boolean.FALSE;

    /**
     * 登录名
     */
    @JsonView(View.Default.class)
    @NotEmpty(message = "{not.null}")
    @Pattern(regexp = Constants.LOGINNAME_PATTERN, message = "{user.loginName.not.valid}", groups = FirstCheck.class)
    private String loginName;

    /**
     * 密码
     */
    @NotEmpty(message = "{not.null}")
    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = "{user.password.not.valid}")
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 是否是管理员
     */
    @JsonView(View.Default.class)
    private Boolean admin = DEFAULT_ADMIN_STATUS;

    /**
     * 最后一次访问的主机地址
     */
    private String lastAccessHost;

    /**
     * 最后一次访问的时间
     */
    private Date lastAccessTime;

    /**
     * 真实名字
     */
    @JsonView(View.Default.class)
    private String realname;

    /**
     * 性别
     */
    @JsonView(View.Default.class)
    private Boolean gender;

    /**
     * 账户状态
     */
    @JsonView(View.Default.class)
    private AccountStatus status;

    /**
     * 拥有的角色
     */
    private Set<Role> roles;

    /**
     * 自动生成 salt 值
     */
    public void randomSalt() {
        setSalt(RandomStringUtils.randomAlphanumeric(SALT_LENGTH));
    }

    // ----------------------------------------------------------------------------------------------

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getLastAccessHost() {
        return lastAccessHost;
    }

    public void setLastAccessHost(String lastAccessHost) {
        this.lastAccessHost = lastAccessHost;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
