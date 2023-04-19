package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录
 * @TableName sys_logininfor
 */
public class SysLogininfor implements Serializable {
    /**
     * 访问ID
     */
    private Long infoId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginTime;

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * 访问ID
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 用户账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 登录IP地址
     */
    public String getIpaddr() {
        return ipaddr;
    }

    /**
     * 登录IP地址
     */
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    /**
     * 登录地点
     */
    public String getLoginLocation() {
        return loginLocation;
    }

    /**
     * 登录地点
     */
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    /**
     * 浏览器类型
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 浏览器类型
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 操作系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 操作系统
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 登录状态（0成功 1失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 登录状态（0成功 1失败）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 提示消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 提示消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 访问时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 访问时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}