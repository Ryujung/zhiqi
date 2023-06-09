package com.zhiqi.system.service;

import java.util.List;
import com.zhiqi.system.domain.SysConfig;

/**
 * 参数配置Service接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysConfigService
{
    /**
     * 查询参数配置
     * 
     * @param configId 参数配置主键
     * @return 参数配置
     */
    public SysConfig selectSysConfigByConfigId(Integer configId);

    /**
     * 查询参数配置列表
     * 
     * @param sysConfig 参数配置
     * @return 参数配置集合
     */
    public List<SysConfig> selectSysConfigList(SysConfig sysConfig);

    /**
     * 新增参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    public int insertSysConfig(SysConfig sysConfig);

    /**
     * 修改参数配置
     * 
     * @param sysConfig 参数配置
     * @return 结果
     */
    public int updateSysConfig(SysConfig sysConfig);

    /**
     * 批量删除参数配置
     * 
     * @param configIds 需要删除的参数配置主键集合
     * @return 结果
     */
    public int deleteSysConfigByConfigIds(Integer[] configIds);

    /**
     * 删除参数配置信息
     * 
     * @param configId 参数配置主键
     * @return 结果
     */
    public int deleteSysConfigByConfigId(Integer configId);

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    boolean selectCaptchaOnOff();
    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    public String selectSysConfigByKey(String configKey);
}
