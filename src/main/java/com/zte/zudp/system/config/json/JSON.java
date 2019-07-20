package com.zte.zudp.system.config.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// @Repeatable(JSONS.class)
public @interface JSON {
    Class<?> type();
    String include() default "";
    String filter() default "";
}

