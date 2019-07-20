package com.zte.zudp.common.persistence.web.permission;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.common.utils.CollectionUtils;
import com.zte.zudp.common.utils.MessageUtils;

/**
 * 权限列表对象，表示访问当前对象所需要权限，可对其进行权限检查
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-03.
 */
public class PermissionList implements Serializable {

    public static final String CREATE_PERMISSION = "create";
    public static final String UPDATE_PERMISSION = "update";
    public static final String DELETE_PERMISSION = "delete";
    public static final String VIEW_PERMISSION = "view";

    private static final String NO_PERMISSION = "no.permission";
    private static final String DEFAULT_ERROR_CODE = NO_PERMISSION;

    private static final long serialVersionUID = 3517245195646765322L;

    /**
     * 资源前缀
     */
    private final String resourceIdentity;

    /**
     * key： 权限
     * value： 资源
     */
    private Map<String, String> resourcePermissions;

    private PermissionList(String resourceIdentity) {
        this.resourceIdentity = resourceIdentity;

        this.resourcePermissions = new HashMap<>();
        this.resourcePermissions.put(CREATE_PERMISSION, getResourcePermission(CREATE_PERMISSION));
        this.resourcePermissions.put(DELETE_PERMISSION, getResourcePermission(DELETE_PERMISSION));
        this.resourcePermissions.put(UPDATE_PERMISSION, getResourcePermission(UPDATE_PERMISSION));
        this.resourcePermissions.put(VIEW_PERMISSION, getResourcePermission(VIEW_PERMISSION));
    }

    public static PermissionList newPermissionList(String resourceIdentity) {
        return new PermissionList(resourceIdentity);
    }

    /**
     * 为当前权限列表添加权限
     */
    public void addPermission(String permission) {
        resourcePermissions.put(permission, resourceIdentity + ":" + permission);
    }

    public void assertHasCreatePermission() {
        assertHasPermission(CREATE_PERMISSION, "no.create.permission");
    }

    public void assertHasUpdatePermission() {
        assertHasPermission(UPDATE_PERMISSION, "no.update.permission");
    }

    public void assertHasDeletePermission() {
        assertHasPermission(DELETE_PERMISSION, "no.delete.permission");
    }

    public void assertHasViewPermission() {
        assertHasPermission(VIEW_PERMISSION, "no.view.permission");
    }

    /**
     * 校验是否有指定权限
     * @param permission 例如 create、delete等
     * @param errorCode 对应的错误代码
     */
    private void assertHasPermission(String permission, String errorCode) {
        if (StringUtils.isEmpty(errorCode)) {
            errorCode = DEFAULT_ERROR_CODE;
        }

        if (StringUtils.isEmpty(permission)) {
            throw new UnauthorizedException(
                    MessageUtils.message(errorCode, getResourcePermission(permission)));
        }

        String resourcePermission = resourcePermissions.get(permission);
        if (resourcePermission == null) {
            resourcePermission = this.resourceIdentity + ":" + permission;
        }

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isPermitted(resourcePermission)) {
            throw new UnauthorizedException(
                    MessageUtils.message(errorCode, getResourcePermission(permission)));
        }
    }

    /**
     * 判断是否有所有 权限列表 的权限
     * @param permissions 权限列表
     * @throws UnauthorizedException 没有权限抛出此异常
     */
    public void assertHasAllPermission(String[] permissions) {
        assertHasAllPermission(permissions, null);
    }

    /**
     * 判断是否有所有 权限列表 的权限
     * @param permissions 权限列表
     * @param errorCode 错误消息代码
     * @throws UnauthorizedException 没有权限抛出此异常
     */
    public void assertHasAllPermission(String[] permissions, String errorCode) {
        if (StringUtils.isEmpty(errorCode)) {
            errorCode = DEFAULT_ERROR_CODE;
        }

        if (CollectionUtils.isEmpty(permissions)) {
            throw new UnauthorizedException(
                    MessageUtils.message(errorCode, getResourcePermission(permissions)));
        }

        Subject subject = SecurityUtils.getSubject();

        for (String permission : permissions) {
            String resourcePermission = resourcePermissions.get(permission);
            if (resourcePermission == null) {
                resourcePermission = getResourcePermission(permission);
            }
            if (!subject.isPermitted(resourcePermission)) {
                throw new UnauthorizedException(
                        MessageUtils.message(errorCode, getResourcePermission(permissions)));
            }
        }
    }

    /**
     * 校验是否有编辑的权限
     */
    public void assertHasEditPermission() {
        assertHasAnyPermission(new String[]{
                CREATE_PERMISSION,
                UPDATE_PERMISSION,
                DELETE_PERMISSION
        });
    }

    /**
     * 校验是否拥有 权限列表 中任意一个权限
     * @param permissions 权限列表
     * @throws UnauthorizedException 没有权限抛出此异常
     */
    public void assertHasAnyPermission(String[] permissions) {
        assertHasAnyPermission(permissions, null);
    }

    /**
     * 校验是否拥有 权限列表 中任意一个权限
     * @param permissions 权限列表
     * @param errorCode 错误消息代码
     * @throws UnauthorizedException 没有权限抛出此异常
     */
    public void assertHasAnyPermission(String[] permissions, String errorCode) {
        if (StringUtils.isEmpty(errorCode)) {
            errorCode = DEFAULT_ERROR_CODE;
        }

        if (CollectionUtils.isEmpty(permissions)) {
            throw new UnauthorizedException(
                    MessageUtils.message(errorCode, getResourcePermission(permissions)));
        }

        Subject subject = SecurityUtils.getSubject();

        for (String permission : permissions) {
            String resourcePermission = this.resourcePermissions.get(permission);
            if (resourcePermission == null) {
                resourcePermission = getResourcePermission(permission);
            }
            if (subject.isPermitted(resourcePermission)) {
                return;
            }
        }

        throw new UnauthorizedException(MessageUtils.message(errorCode, getResourcePermission(permissions)));
    }


    private String getResourcePermission(String permission) {
        return resourceIdentity + ":" + permission;
    }

    private String getResourcePermission(String[] permissions) {
        return resourceIdentity + ":" + Arrays.toString(permissions);
    }
}
