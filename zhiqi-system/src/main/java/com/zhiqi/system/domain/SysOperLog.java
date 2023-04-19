package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录
 * @TableName sys_oper_log
 */
public class SysOperLog implements Serializable {
    /**
     * 日志主键
     */
    private Long operId;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请求URL
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operTime;

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    public Long getOperId() {
        return operId;
    }

    /**
     * 日志主键
     */
    public void setOperId(Long operId) {
        this.operId = operId;
    }

    /**
     * 模块标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 模块标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 方法名称
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求方式
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 请求方式
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * 操作人员
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 操作人员
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 请求URL
     */
    public String getOperUrl() {
        return operUrl;
    }

    /**
     * 请求URL
     */
    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    /**
     * 主机地址
     */
    public String getOperIp() {
        return operIp;
    }

    /**
     * 主机地址
     */
    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    /**
     * 操作地点
     */
    public String getOperLocation() {
        return operLocation;
    }

    /**
     * 操作地点
     */
    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    /**
     * 请求参数
     */
    public String getOperParam() {
        return operParam;
    }

    /**
     * 请求参数
     */
    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    /**
     * 返回参数
     */
    public String getJsonResult() {
        return jsonResult;
    }

    /**
     * 返回参数
     */
    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 操作状态（0正常 1异常）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 操作状态（0正常 1异常）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 错误消息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 错误消息
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}