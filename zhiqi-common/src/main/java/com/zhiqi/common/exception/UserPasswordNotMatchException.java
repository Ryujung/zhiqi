package com.zhiqi.common.exception;

import com.zhiqi.common.exception.user.UserException;

/**
 * @author RyuJung
 * @since 2023/4/25-17:58
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
