package com.zhiqi.web.config;

import com.zhiqi.common.config.ZhiQiConfig;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/16-23:12
 */
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    @Autowired
    private ZhiQiConfig zhiQiConfig;

    private boolean enabled;

    private String pathMapping;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(enabled)
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                /* FIXME 设置安全模式，swagger可以设置访问token  */
//                .securityContexts(getSecurityContextList())
//                .securitySchemes(getSecuritySchemaList())
                .apiInfo(apiInfo())
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .pathMapping(pathMapping);
    }

    private List<SecurityScheme> getSecuritySchemaList() {
        ArrayList<SecurityScheme> securitySchemaList = new ArrayList<>();
        securitySchemaList.add(new ApiKey("Authorization",
                "Authorization", In.HEADER.toValue()));
        return securitySchemaList;
    }

    private List<SecurityContext> getSecurityContextList() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(t -> t.requestMappingPattern().matches("/.*"))
                .build();
        return Collections.singletonList(securityContext);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference authorization = new SecurityReference("Authorization", authorizationScopes);
        return Collections.singletonList(authorization);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("知其项目接口文档")
                .description("这是知其项目的接口文档，使用Swagger3搭建")
                .version("版本：" + zhiQiConfig.getVersion())
                .contact(new Contact("ryujung", null, "ryujung@163.com"))
                .build();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPathMapping() {
        return pathMapping;
    }

    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }
}
