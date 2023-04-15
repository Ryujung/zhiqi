package com.ryujung.zhiqi.common.utils;

import com.ryujung.zhiqi.common.core.domain.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author RyuJung
 * @since 2023/4/15-0:34
 */
public class SecurityUtils {

    public static Long getUserId() {
        return getLoginUser().getUserId();
    }

    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
