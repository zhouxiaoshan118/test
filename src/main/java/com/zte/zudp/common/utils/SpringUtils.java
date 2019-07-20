package com.zte.zudp.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor {

    /* Spring 应用上下文 */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * 获取对象
     *
     * @param name 类实例的id名
     * @param <T>  类名
     * @return 类实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取对象
     *
     * @param cla 类的Class对象
     * @param <T> 类名
     * @return 类实例
     */
    public static <T> T getBean(Class<T> cla) throws BeansException {
        return beanFactory.getBean(cla);
    }

    /**
     * 如果 beanFactory 包含一个名字为 name 的实例，则返回true。
     *
     * @param name 实例名
     * @return 存在一个名字为 name 的实例则返回 true。
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断给定 name 注册的 bean 的类型是否为 singleton，如果不存在此 name 的 bean ，则抛出异常
     *
     * @param name 实例名
     * @return 如果给定 name 的 bean 类型为 singleton则返回 true。
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    /**
     * 返回给定name的实例的 Class实例
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    /**
     * 返回给定 name 的实例的所有别名
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }
}
