package com.zhiqi.framework.config;

import com.zhiqi.framework.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;

/**
 * @author RyuJung
 * @since 2023/4/19-11:09
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${swagger.pathMapping}")
    private String pathMapping;

    //    @Autowired
    AuthenticationEntryPoint unauthenrizedHandler; // TODO

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler; // TODO

    @Autowired
    JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private static final String[] STATIC_RESOURCES = {
            // static resources
            "/",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/profile/**",

            //FIXME 临时开放
            "/tool/gen/**"
    };

    /**
     * druid resources path, anonymous
     */
    private static final String[] DRUID_RESOURCES = {"/druid/**"};

    /**
     * swagger resources path, anonymous
     */
    private static final String[] SWAGGER_RESOURCES = {
            "/swagger-ui.html", "/swagger-resources/**", "/swagger-ui/**", "/*/api-docs", "/webjars/**"};

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable();

        // request control
        httpSecurity.authorizeRequests()
                .antMatchers("/login", "/register", "/captchaImage").anonymous()
                .antMatchers(HttpMethod.GET, STATIC_RESOURCES).permitAll()
                .antMatchers(DRUID_RESOURCES).anonymous()
                .antMatchers(SWAGGER_RESOURCES).anonymous()
                .antMatchers(pathMapping + "/**").anonymous()
                .anyRequest().authenticated();

        // config exception handler
//                .exceptionHandling().authenticationEntryPoint(unauthenrizedHandler).and() // FIXME

        // config logout handler
//        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler); // FIXME

        // add authentication filter and cors filter
//        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // FIXME
//        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
//        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }
}
