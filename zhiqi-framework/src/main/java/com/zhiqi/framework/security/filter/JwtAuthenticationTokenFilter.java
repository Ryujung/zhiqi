package com.zhiqi.framework.security.filter;

import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验jwt token的过滤器
 *
 * @author RyuJung
 * @since 2023/4/19-11:34
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);

        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotNull(SecurityUtils.getAuthentication())) {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken usernamePassAuthToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            usernamePassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePassAuthToken);
        }

        filterChain.doFilter(request, response);
    }

}
