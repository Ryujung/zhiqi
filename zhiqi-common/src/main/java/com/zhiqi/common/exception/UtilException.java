package com.zhiqi.common.exception;

/**
 * @author RyuJung
 * @since 2023/4/30-20:17
 */
public class UtilException extends RuntimeException{

    public UtilException() {
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

}
