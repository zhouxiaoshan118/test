package com.zte.zudp.system.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.zte.zudp.system.config.shiro.cache.SpringCacheManagerWrapper;
import com.zte.zudp.system.config.shiro.filter.autchc.CustomFormAuthenticationFilter;
import com.zte.zudp.system.config.shiro.filter.captcha.CaptchaValidateFilter;
import com.zte.zudp.system.config.shiro.realm.UserRealm;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-14.
 */
@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Autowired
    private SpringCacheManagerWrapper cacheManager;

    /**
     * 不指定名字的话，自动创建一个方法名第一个字母小写的bean
     * @Bean(name = "securityManager")
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        logger.info("注入Shiro的Web过滤器-->securityManager", ShiroFilterFactoryBean.class);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //注入缓存管理器;
        securityManager.setCacheManager(cacheManager);//这个如果执行多次，也是同样的一个对象;
        return securityManager;
    }

    @Bean(name = "captchaValidateFilter")
    public CaptchaValidateFilter captchaValidateFilter() {
        CaptchaValidateFilter filter = new CaptchaValidateFilter();
        filter.setCaptchaParam("jcaptchaCode");
        filter.setCapatchaErrorUrl("/login?captchaError=1");
        return filter;
    }

    /**
     * Shiro的Web过滤器Factory 命名:shiroFilter<br />
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        logger.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();

        CustomFormAuthenticationFilter authenticationFilter = new CustomFormAuthenticationFilter();
        authenticationFilter.setUsernameParam("loginName");
        authenticationFilter.setPasswordParam("password");
        authenticationFilter.setAdminDefaultSuccessURL("/admin");
        authenticationFilter.setDefaultSuccessURL("/index");
        filters.put("authc", authenticationFilter);
        filters.put("captcha", captchaValidateFilter());

        shiroFilterFactoryBean.setFilters(filters);


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        filterChainDefinitionMap.put("/login", "captcha,authc");
        filterChainDefinitionMap.put("/reg", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/pages/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/front/**", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/winui/**", "anon");
        filterChainDefinitionMap.put("/admin/sys/sp/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        //shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		boolean enabeld = Boolean.parseBoolean(com.zte.zudp.common.config.Configuration.getProperty("zudp.permission.enabled", "true"));
        if (enabeld) {
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        } else {
            shiroFilterFactoryBean.setFilterChainDefinitionMap(new LinkedHashMap<String, String>() {{
                put("/**", "anon");
            }});
        }


        return shiroFilterFactoryBean;
    }

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)，借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
