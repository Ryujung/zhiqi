package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置Mapper接口
 *
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysConfigMapper {
    /**
     * 查询参数配置
     *
     * @param configId 参数配置主键
     * @return 参数配置
     */
    SysConfig selectSysConfigByConfigId(Integer configId);

    /**
     * 查询参数配置列表
     *
     * @param sysConfig 参数配置
     * @return 参数配置集合
     */
    List<SysConfig> selectSysConfigList(SysConfig sysConfig);

    /**
     * 查询参数配置
     *
     * @param sysConfig 参数配置
     * @return 参数配置
     */
    SysConfig selectSysConfig(SysConfig sysConfig);

    /**
     * 新增参数配置
     *
     * @param sysConfig 参数配置
     * @return 结果
     */
    int insertSysConfig(SysConfig sysConfig);

    /**
     * 修改参数配置
     *
     * @param sysConfig 参数配置
     * @return 结果
     */
    int updateSysConfig(SysConfig sysConfig);

    /**
     * 删除参数配置
     *
     * @param configId 参数配置主键
     * @return 结果
     */
    int deleteSysConfigByConfigId(Integer configId);

    /**
     * 批量删除参数配置
     *
     * @param configIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysConfigByConfigIds(Integer[] configIds);

}
