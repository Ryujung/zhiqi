package com.zhiqi.common.core.domain.model;

/**
 * 登录信息
 *
 * @author RyuJung
 * @since 2023/4/25-13:06
 */
public class LoginBody {

    private String username;

    private String password;

    private String code;

    private String uuid = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
