package com.zhiqi.common.exception.user;

import com.zhiqi.common.exception.base.BaseException;

/**
 * @author RyuJung
 * @since 2023/4/25-17:59
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
