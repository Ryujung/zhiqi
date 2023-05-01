package com.zhiqi.framework.config;

import com.zhiqi.framework.security.filter.JwtAuthenticationTokenFilter;
import com.zhiqi.framework.security.handler.AuthenticationEntryPointImpl;
import com.zhiqi.framework.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author RyuJung
 * @since 2023/4/19-11:09
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private CorsFilter corsFilter;

    @Value("${swagger.pathMapping}")
    private String swaggerPathMapping;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] STATIC_RESOURCES = {
            // static resources
            "/",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/profile/**"
    };

    /**
     * druid resources path, anonymous
     */
    private static final String[] DRUID_RESOURCES = {"/druid/**"};

    /**
     * swagger resources path, anonymous
     */
    private static final String[] SWAGGER_RESOURCES = {
            "/swagger-ui.html", "/swagger-resources/**", "/swagger-ui/**", "/*/api-docs", "/webjars/**",

            //FIXME 临时开放生成代码
            "/tool/gen/**"};

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
                .antMatchers(swaggerPathMapping + "/**").anonymous()
                .anyRequest().authenticated();

        // config exception handler
        httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // config logout handler
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

        // add authentication filter and cors filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }
}
