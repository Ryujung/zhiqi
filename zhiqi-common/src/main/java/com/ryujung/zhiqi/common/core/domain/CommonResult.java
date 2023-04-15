package com.ryujung.zhiqi.common.core.domain;

import com.ryujung.zhiqi.common.contant.HttpStatus;
import com.ryujung.zhiqi.common.utils.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @author RyuJung
 * @since 2023/4/14-21:46
 */
public class CommonResult extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public CommonResult() {

    }

    public CommonResult(int code, String msg) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
    }

    public CommonResult(int code, String msg, Object data) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            put(DATA_TAG, data);
        }
    }

    public static CommonResult success() {
        return CommonResult.success("操作成功");
    }

    public static CommonResult success(String msg) {
        return CommonResult.success(msg, null);
    }

    public static CommonResult success(Object data) {
        return CommonResult.success("操作成功", data);
    }

    public static CommonResult success(String msg, Object data) {
        return new CommonResult(HttpStatus.SUCCESS, msg, data);
    }

    public static CommonResult error() {
        return error("操作失败");
    }

    public static CommonResult error(String msg) {
        return CommonResult.error(msg, null);
    }

    public static CommonResult error(String msg, Object data) {
        return new CommonResult(HttpStatus.ERROR, msg, data);
    }

    public static CommonResult error(int code, String msg) {
        return new CommonResult(code, msg);
    }

    @Override
    public CommonResult put(String key, Object val) {
        super.put(key, val);
        return this;
    }
}
