package com.zhiqi.common.exception.user;

/**
 * 验证码失效异常类
 *
 * @author RyuJung
 * @since 2023/4/28-13:33
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }
}
