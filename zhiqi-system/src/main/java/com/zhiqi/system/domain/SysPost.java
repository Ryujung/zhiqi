package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位信息表
 * @TableName sys_post
 */
public class SysPost implements Serializable {
    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示顺序
     */
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
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

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 岗位ID
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * 岗位编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 岗位编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 岗位名称
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 岗位名称
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * 显示顺序
     */
    public Integer getPostSort() {
        return postSort;
    }

    /**
     * 显示顺序
     */
    public void setPostSort(Integer postSort) {
        this.postSort = postSort;
    }

    /**
     * 状态（0正常 1停用）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态（0正常 1停用）
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
}