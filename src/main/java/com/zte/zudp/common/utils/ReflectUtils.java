package com.zte.zudp.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-16.
 */
public class ReflectUtils {

    /**
     * 获取指定类(或其父类)泛型实参数组
     *
     * @return 如果该类的父类没有泛型则返回null
     */
    public static Type[] findParameterizedType(Class<?> clazz) {
        Type parameterizedType = clazz.getGenericSuperclass();

        // CGLUB subclass target object(泛型在父类上)
        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }
        if (!(parameterizedType instanceof ParameterizedType)) {
            return null;
        }

        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }

        return actualTypeArguments;
    }

    /**
     * 获取指定类(或其父类)泛型实参
     *
     * @param clazz 泛型所在类或泛型所在类的子类
     * @param index 该泛型的索引
     * @param <T>   泛型实参类
     * @return 泛型实参类对象
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type[] parameterizedType = findParameterizedType(clazz);
        if (parameterizedType != null) {
            return (Class<T>) parameterizedType[index];
        }
        return null;
    }

    /**
     * 如果忽略异常信息则可以使用此方法进行创建对象
     */
    public static <T> T newInstanceIgnore(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // ignore exception
            }
        }
        return field;
    }
}
