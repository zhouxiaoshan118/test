package com.zte.zudp.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zte.zudp.common.enums.Operation;
import com.zte.zudp.common.enums.Permission;

/**
 * 框架的校验注解，仅对继承了 AbstractController 的类具有校验权限
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-13.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface HasPermission {
    Permission[] value();

    Operation operation() default Operation.ALL;
}
