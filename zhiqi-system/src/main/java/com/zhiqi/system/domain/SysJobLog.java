package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度日志表
 * @TableName sys_job_log
 */
public class SysJobLog implements Serializable {
    /**
     * 任务日志ID
     */
    private Long jobLogId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    private String invokeTarget;

    /**
     * 日志信息
     */
    private String jobMessage;

    /**
     * 执行状态（0正常 1失败）
     */
    private String status;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志ID
     */
    public Long getJobLogId() {
        return jobLogId;
    }

    /**
     * 任务日志ID
     */
    public void setJobLogId(Long jobLogId) {
        this.jobLogId = jobLogId;
    }

    /**
     * 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 任务组名
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 任务组名
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 调用目标字符串
     */
    public String getInvokeTarget() {
        return invokeTarget;
    }

    /**
     * 调用目标字符串
     */
    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    /**
     * 日志信息
     */
    public String getJobMessage() {
        return jobMessage;
    }

    /**
     * 日志信息
     */
    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    /**
     * 执行状态（0正常 1失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 执行状态（0正常 1失败）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 异常信息
     */
    public String getExceptionInfo() {
        return exceptionInfo;
    }

    /**
     * 异常信息
     */
    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}