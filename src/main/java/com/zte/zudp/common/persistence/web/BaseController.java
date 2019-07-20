package com.zte.zudp.common.persistence.web;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zte.zudp.common.persistence.entity.AbstractEntity;
import com.zte.zudp.common.utils.ReflectUtils;
import com.zte.zudp.common.utils.DateUtils;

/**
 * Controller 的基类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public abstract class BaseController<T extends AbstractEntity> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 当前控制器关联的实体类
    protected final Class<T> entityClass;


    public BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        init();
    }

    /**
     * 方便扩展
     */
    protected void init() {
    }

    /**
     * 创建当前Controller对应的实体类对象
     */
    protected T newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }

    /**
     * 数据绑定
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // 防止 XSS 漏洞攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value == null ? "" : value.toString();
            }

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });

        // 将 Date 类型的数据转为 String 类型
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
}
