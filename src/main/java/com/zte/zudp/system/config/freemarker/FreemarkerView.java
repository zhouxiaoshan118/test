package com.zte.zudp.system.config.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.config.Configuration;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-14.
 */
public class FreemarkerView extends FreeMarkerView {

    private String adminPath;

    {
        adminPath = Configuration.getProperty("sys.url.admin");
        if (adminPath == null) {
            throw new RuntimeException("property 'adminPath' initial fail!");
        }
    }

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request)
            throws Exception {
        String basePath = ServletUtils.contentPath(request);
        model.put("basePath", basePath);
        model.put("adminPath", basePath + adminPath);
        super.exposeHelpers(model, request);
    }
}
