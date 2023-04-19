package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知公告表
 * @TableName sys_notice
 */
public class SysNotice implements Serializable {
    /**
     * 公告ID
     */
    private Integer noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告状态（0正常 1关闭）
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
     * 备注
     */
    private String remark;

    /**
     * 公告内容
     */
    private byte[] noticeContent;

    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    public Integer getNoticeId() {
        return noticeId;
    }

    /**
     * 公告ID
     */
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * 公告标题
     */
    public String getNoticeTitle() {
        return noticeTitle;
    }

    /**
     * 公告标题
     */
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    /**
     * 公告类型（1通知 2公告）
     */
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * 公告类型（1通知 2公告）
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * 公告状态（0正常 1关闭）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 公告状态（0正常 1关闭）
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
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 公告内容
     */
    public byte[] getNoticeContent() {
        return noticeContent;
    }

    /**
     * 公告内容
     */
    public void setNoticeContent(byte[] noticeContent) {
        this.noticeContent = noticeContent;
    }
}