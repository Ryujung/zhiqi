package com.zhiqi.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数配置表
 * @TableName sys_config
 */
public class SysConfig implements Serializable {
    /**
     * 参数主键
     */
    private Integer configId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    private String configType;

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
     * 参数主键
     */
    public Integer getConfigId() {
        return configId;
    }

    /**
     * 参数主键
     */
    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    /**
     * 参数名称
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * 参数名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * 参数键名
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * 参数键名
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * 参数键值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 参数键值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 系统内置（Y是 N否）
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 系统内置（Y是 N否）
     */
    public void setConfigType(String configType) {
        this.configType = configType;
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