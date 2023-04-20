package com.zhiqi.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.framework.manager.AsyncManager;
import com.zhiqi.framework.manager.factory.AsyncFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import com.zhiqi.framework.web.service.TokenService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author RyuJung
 * @since 2023/4/19-17:35
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            tokenService.deleteLoginUser(loginUser);
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(CommonResult.success("退出成功")));
    }

}
