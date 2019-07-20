package com.zte.zudp.common.persistence.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zte.zudp.common.config.Constants;
import com.zte.zudp.common.persistence.entity.DateEntity;

/**
 * 传统的URL请求方式
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-13.
 */
public abstract class TraditionController<T extends DateEntity> extends AbstractController<T> {

    protected static final String CREATE_SUCCESS = "新增成功";
    protected static final String UPDATE_SUCCESS = "修改成功";
    protected static final String DELETE_SUCCESS = "删除成功";

    protected static final String SEE_VIEW = "editForm";
    protected static final String CREATE_VIEW = "editForm";
    protected static final String UPDATE_VIEW = "editForm";
    protected static final String DELETE_VIEW = "editForm";

    /**
     * 列表页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(T t, Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        if (isListAlsoCommonData()) {
            setCommonData(model);
        }

        model.addAttribute("page", service.findPage(t));

        return appendSuffix("list");
    }

    /**
     * 跳转到指定id的数据显示页
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @NotNull @PathVariable("id") String id) {

        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.MODEL, service.get(id));
        return appendSuffix(SEE_VIEW);
    }

    /**
     * 跳转到创建页
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        setCommonData(model);
        if (!model.containsAttribute(Constants.MODEL)) {
            model.addAttribute(Constants.MODEL, newModel());
        }
        return appendSuffix(CREATE_VIEW);
    }

    /**
     * 创建数据
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute(Constants.MODEL) T t,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        if (hasError(t, result)) {
            return showCreateForm(model);
        }
        service.save(t);
        addMessage(redirectAttributes, CREATE_SUCCESS);
        return redirectTo(null);
    }

    /**
     * 跳转到指定id的更新页
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@NotNull @PathVariable("id") String id,
                                 @ModelAttribute(Constants.MODEL) T t,
                                 Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.MODEL, service.get(id));
        return appendSuffix(UPDATE_VIEW);
    }

    /**
     * 更新指定id的数据
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Model model, BindingResult result, @PathVariable("id") String id,
                         @Valid @ModelAttribute(Constants.MODEL) T t,
                         RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        if (hasError(t, result)) {
            return showUpdateForm(id, t, model);
        }
        service.update(t);
        addMessage(redirectAttributes, UPDATE_SUCCESS);
        return redirectTo(null);
    }

    /**
     * 跳转到删除指定id的页面
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") T t, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.MODEL, t);
        return appendSuffix(DELETE_VIEW);
    }

    /**
     * 删除指定id的页面
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") T t, RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        service.delete(t);

        addMessage(redirectAttributes, DELETE_SUCCESS);
        return redirectTo(null);
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public String deleteInBatch(@RequestParam(value = "ids", required = false) String[] ids,
                                RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        service.delete(ids);
        addMessage(redirectAttributes, DELETE_SUCCESS);
        return redirectTo(null);
    }
}
