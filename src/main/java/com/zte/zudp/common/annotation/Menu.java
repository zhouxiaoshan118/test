package com.zte.zudp.common.annotation;

import com.zte.zudp.common.enums.MenuEnum;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Menu {
    String id();
    String name();
    Class parent();
    int order();
    MenuEnum type();
}
