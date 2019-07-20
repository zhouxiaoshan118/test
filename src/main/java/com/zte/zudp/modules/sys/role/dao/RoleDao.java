package com.zte.zudp.modules.sys.role.dao;

import java.util.List;
import java.util.Set;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.common.persistence.entity.AssociationEntity;
import com.zte.zudp.modules.sys.role.entity.Role;
import com.zte.zudp.modules.sys.user.entity.User;

/**
 * 角色Dao操作类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public interface RoleDao extends AbstractDao<Role> {

    /**
     * 查找所有激活可用的角色
     *
     * @param roleIds 查找的角色id集合
     * @return 角色信息集合
     */
    Set<Role> findActivedRoles(List<String> roleIds);

    /**
     * 查找所有激活可用的权限id
     *
     * @param role .
     * @return 指定角色拥有的所有可用权限id
     */
    Set<String> findActivedPermissionIds(Role role);

    /**
     * 批量添加角色和权限关联表记录
     *
     * @param associationEntities 关联表对象实体集合
     */
    void insertSrmBash(List<AssociationEntity> associationEntities);

    /**
     * 批量删除角色和权限关联表记录
     *
     * @param role 关联表中的角色对象
     */
    void deleteSrmBash(Role role);

    /**
     * 在账户角色关联表中查找拥有指定角色的所有账户id
     *
     * @param role 指定的角色
     * @return 拥有指定角色的所有账户集合
     */
    List<User> findAccountsSra(Role role);

    /**
     * 批量删除角色和账户关联表记录
     *
     * @param role 关联表中的角色对象id
     */
    void deleteSraBash(Role role);

    /**
     * 批量插入 角色和账户关联表 记录
     *
     * @param associationEntities 关联表对象实体集合
     */
    void insertSraBash(List<AssociationEntity> associationEntities);

    /**
     * 移除指定账户与角色的关联关系
     *
     * @param entity 映射一个账户和角色的关联实体
     */
    void deleteSra(AssociationEntity entity);
}
