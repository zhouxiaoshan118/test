package com.zte.zudp.system.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Operation;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.AbstractController;
import com.zte.zudp.common.persistence.web.permission.PermissionList;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-13.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Object bean = method.getBean();
            HasPermission hasPermission = method.getMethodAnnotation(HasPermission.class);

            if (bean instanceof AbstractController) {
                return checkRESTfulController((AbstractController) bean, hasPermission);
            }
        }

        return super.preHandle(request, response, handler);
    }

    private boolean checkRESTfulController(AbstractController controller, HasPermission hasPermission) {
        if (hasPermission == null) {
            return true;
        } else {
            PermissionList permissionList = controller.getPermissionList();
            Operation operation = hasPermission.operation();
            Permission[] value = hasPermission.value();

            String[] permissions = new String[value.length];
            for (int i = 0; i < value.length; i++) {
                permissions[i] = value[i].getInfo();
            }

            if (operation == Operation.ALL) {
                permissionList.assertHasAllPermission(permissions);
            } else if (operation == Operation.ANY) {
                permissionList.assertHasAnyPermission(permissions);
            }

            return true;
        }
    }
}
