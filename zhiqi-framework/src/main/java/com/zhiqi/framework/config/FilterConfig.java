package com.zhiqi.framework.config;

import com.zhiqi.common.filter.RepeatableWrapFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 添加自定义过滤器的配置
 *
 * @author RyuJung
 * @since 2023/5/1-11:59
 */
@Configuration
public class FilterConfig {

    // TODO add XSS filter


    @Bean
    public FilterRegistrationBean<Filter> repeatableWrapFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RepeatableWrapFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("repeatableWrapFilter");
        registrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registrationBean;
    }

}
