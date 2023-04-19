package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表
 * @TableName sys_job
 */
public class SysJob implements Serializable {
    /**
     * 任务ID
     */
    private Long jobId;

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
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    private String misfirePolicy;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    private String concurrent;

    /**
     * 状态（0正常 1暂停）
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 任务ID
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
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
     * cron执行表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * cron执行表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    /**
     * 是否并发执行（0允许 1禁止）
     */
    public String getConcurrent() {
        return concurrent;
    }

    /**
     * 是否并发执行（0允许 1禁止）
     */
    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    /**
     * 状态（0正常 1暂停）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态（0正常 1暂停）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    /**
     * 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}