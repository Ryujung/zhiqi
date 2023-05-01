package com.zhiqi.framework.config;

import com.zhiqi.common.annotation.RepeatSubmit;
import com.zhiqi.common.config.ZhiQiConfig;
import com.zhiqi.common.contant.Constants;
import com.zhiqi.framework.interceptor.AbstractRepeatSubmitInterceptor;
import com.zhiqi.framework.interceptor.impl.SameUrlDataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源相关配置
 *
 * @author RyuJung
 * @since 2023/4/19-11:24
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Autowired
    private AbstractRepeatSubmitInterceptor repeatSubmitInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
                .addResourceLocations("file:" + ZhiQiConfig.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("/swagger-ui/**",
                        "**/swagger-resources/**",
                        "/v2/api-docs",
                        "/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitInterceptor);
    }

    /**
     * 跨域控制
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        // origin address
        corsConfig.addAllowedOriginPattern("*");
        // request header
        corsConfig.addAllowedHeader("*");
        // request method
        corsConfig.addAllowedMethod("*");
        // 有效时间 30分钟
        corsConfig.setMaxAge(1800L);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(configurationSource);
    }

}
