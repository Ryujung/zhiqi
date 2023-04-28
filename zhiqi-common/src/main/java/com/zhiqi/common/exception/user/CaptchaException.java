package com.zhiqi.common.exception.user;

/**
 * 验证码错误异常
 *
 * @author RyuJung
 * @since 2023/4/28-13:36
 */
public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
