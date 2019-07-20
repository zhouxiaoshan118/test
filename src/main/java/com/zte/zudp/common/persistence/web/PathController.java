package com.zte.zudp.common.persistence.web;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.utils.SpELUtils;

/**
 * 路径的处理
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-20.
 */
public abstract class PathController<T extends DateEntity> extends BaseController<T> {

    protected static final String LIST = "list";

    @Value("${sys.url.admin}")
    protected String adminPath;

    // 视图前缀
    private String viewPrefix;

    // 视图的路径
    private String defaultViewPath;

    @Override
    protected void init() {
        super.init();
        setViewPrefix(defaultViewPrefix());
    }

    /**
     * <p>通过获取存在于当前Controller中的RequestMapping中的value值作为默认的视图前缀</p>
     * <p>
     * 如果当前Controller中没有RequestMapping注解，则获取去除 "Controller" 字符串的类名作为
     * 默认的视图前缀
     * </p>
     *
     * @return 获取默认的视图前缀
     */
    protected String defaultViewPrefix() {
        String viewPrefix = null;
        RequestMapping mapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);

        if (mapping != null && mapping.value().length > 0) {
            viewPrefix = SpELUtils.parser(mapping.value()[0]);
        }

        if (StringUtils.isEmpty(viewPrefix)) {
            viewPrefix = this.getClass().getSimpleName().replace("Controller", "");
        }

        return viewPrefix;
    }

    /**
     * <p>在 视图前缀 上追加 视图后缀：<code>viewPrefix + "/" + suffixName</code></p>
     */
    protected String appendSuffix(String suffix) {
        if (!suffix.startsWith("/")) {
            suffix = "/" + suffix;
        }

        return getViewPrefix() + suffix;
    }

    /**
     * <p>获取 默认的视图</p>
     * <p>如果已设置默认的defaultViewPath，则直接返回其</p>
     *      如果默认的 defaultViewPath 为空，则采用默认形式进行：即根据映射路径来
     *      defaultViewPath可通过 init()方法进行设置
     * @see PathController#appendSuffix(String)
     */
    protected ModelAndView defaultView() {
        if (StringUtils.isEmpty(defaultViewPath)) {
            defaultViewPath = appendSuffix(LIST);
        }

        return new ModelAndView(defaultViewPath);
    }

    protected ModelAndView view(String first, String... path) {
        return new ModelAndView(Paths.get(first, path).toString());
    }

    /**
     * 重定向到指定 URL ，格式如 /admin/sys/role 等
     *
     * @param backURL null 将重定向到默认View视图
     * @see PathController#getDefaultViewPath()
     */
    protected String redirectTo(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getDefaultViewPath();
        }

        if (!backURL.startsWith("/")) {
            backURL = "/" + backURL;
        }

        return "redirect:" + backURL;
    }

    // ---------------------------------------------------------------------------------------------

    protected void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    protected String getViewPrefix() {
        return viewPrefix;
    }

    protected String getDefaultViewPath() {
        return defaultViewPath;
    }

    protected void setDefaultViewPath(String defaultViewPath) {
        this.defaultViewPath = defaultViewPath;
    }
}
