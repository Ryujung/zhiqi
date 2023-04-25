package com.zhiqi.common.utils;

import com.zhiqi.common.contant.HttpStatus;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全服务工具类
 *
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

    public static String getUserName() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
