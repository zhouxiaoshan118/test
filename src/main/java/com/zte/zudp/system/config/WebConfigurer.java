package com.zte.zudp.system.config;

import com.zte.zudp.common.config.Configuration;
import com.zte.zudp.system.config.interceptor.AccessLogInterceptor;
import com.zte.zudp.system.config.interceptor.CORSInterceptor;
import com.zte.zudp.system.config.interceptor.PermissionInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-11-15.
 */
@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration
@ComponentScan
public class WebConfigurer extends WebMvcConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebConfigurer.class);

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public CORSInterceptor corsInterceptor() {
        return new CORSInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
		super.addInterceptors(registry);

        boolean enabeld = Boolean.parseBoolean(Configuration.getProperty("zudp.permission.enabled", "true"));

        registry.addInterceptor(accessLogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**,/js/**,/img/**,/plugins/**");
        
        if (enabeld) {
            logger.info("enabled permission!");
            registry.addInterceptor(permissionInterceptor())
                    .addPathPatterns("/admin/**")
                    .excludePathPatterns("/css/**,/js/**,/img/**,/plugins/**");
        }

        registry.addInterceptor(localeChangeInterceptor());

        registry.addInterceptor(corsInterceptor());

    }
}
