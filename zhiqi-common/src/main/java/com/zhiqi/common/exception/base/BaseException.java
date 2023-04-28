package com.zhiqi.common.exception.base;

import com.zhiqi.common.utils.MessageUtils;
import com.zhiqi.common.utils.StringUtils;

/**
 * @author RyuJung
 * @since 2023/4/25-17:59
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
