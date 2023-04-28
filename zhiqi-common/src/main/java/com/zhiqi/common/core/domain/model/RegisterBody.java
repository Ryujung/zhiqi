package com.zhiqi.common.core.domain.model;

/**
 * 用户注册对象
 *
 * @author RyuJung
 * @since 2023/4/28-13:05
 */
public class RegisterBody extends LoginBody {

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
