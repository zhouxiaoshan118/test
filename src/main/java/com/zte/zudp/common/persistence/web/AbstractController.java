package com.zte.zudp.common.persistence.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.web.permission.PermissionList;

/**
 * 对Controller的基类进行补充，添加权限功能，Service层的注解
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public abstract class AbstractController<T extends DateEntity> extends PathController<T> {

    protected BusinessService<T> service;

    private boolean listAlsoCommonData;

    protected PermissionList permissionList;

    @Autowired
    public void setService(BusinessService<T> service) {
        this.service = service;
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create，此处会添加形成一个基本的增删改查的列表
     *
     * @see PermissionList
     */
    protected void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }

    /**
     * 设置公共数据
     */
    protected void setCommonData(Model model) {
    }

    /**
     * 设置RESTful中的公共数据处理
     */
    protected void setCommonData(T t) {
    }

    /**
     * 检测是否校验不通过
     *
     * @param t      校验实体类字段
     * @param result 。
     */
    public boolean hasError(T t, BindingResult result) {
        Assert.notNull(t, "实体类为null");
        return result.hasErrors();
    }

    /**
     * 重定向时也保存数据，防止重定向导致数据丢失
     *
     * @param redirectAttributes 。
     * @param message            保存的数据，获取数据通过 {@link Constants#MESSAGE}
     */
    public void addMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, message);
    }

    // ---------------------------------------------------------------------------------------------

    public void setListAlsoCommonData(boolean listAlsoCommonData) {
        this.listAlsoCommonData = listAlsoCommonData;
    }

    public boolean isListAlsoCommonData() {
        return listAlsoCommonData;
    }

    public PermissionList getPermissionList() {
        return permissionList;
    }
}
