package com.zhiqi.system.service.impl;

import java.util.List;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.redis.RedisCache;
import com.zhiqi.common.core.text.Converter;
import com.zhiqi.common.utils.DateUtils;
import com.zhiqi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysConfigMapper;
import com.zhiqi.system.domain.SysConfig;
import com.zhiqi.system.service.SysConfigService;

import javax.annotation.Resource;

/**
 * 参数配置Service业务层处理
 *
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Resource
    private SysConfigMapper configMapper;

    @Resource
    private RedisCache redisCache;

    /**
     * 查询参数配置
     *
     * @param configId 参数配置主键
     * @return 参数配置
     */
    @Override
    public SysConfig selectSysConfigByConfigId(Integer configId) {
        return configMapper.selectSysConfigByConfigId(configId);
    }

    /**
     * 查询参数配置列表
     *
     * @param sysConfig 参数配置
     * @return 参数配置
     */
    @Override
    public List<SysConfig> selectSysConfigList(SysConfig sysConfig) {
        return configMapper.selectSysConfigList(sysConfig);
    }

    /**
     * 新增参数配置
     *
     * @param sysConfig 参数配置
     * @return 结果
     */
    @Override
    public int insertSysConfig(SysConfig sysConfig) {
        sysConfig.setCreateTime(DateUtils.getNowDate());
        return configMapper.insertSysConfig(sysConfig);
    }

    /**
     * 修改参数配置
     *
     * @param sysConfig 参数配置
     * @return 结果
     */
    @Override
    public int updateSysConfig(SysConfig sysConfig) {
        sysConfig.setUpdateTime(DateUtils.getNowDate());
        return configMapper.updateSysConfig(sysConfig);
    }

    /**
     * 批量删除参数配置
     *
     * @param configIds 需要删除的参数配置主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigByConfigIds(Integer[] configIds) {
        return configMapper.deleteSysConfigByConfigIds(configIds);
    }

    /**
     * 删除参数配置信息
     *
     * @param configId 参数配置主键
     * @return 结果
     */
    @Override
    public int deleteSysConfigByConfigId(Integer configId) {
        return configMapper.deleteSysConfigByConfigId(configId);
    }

    @Override
    public boolean selectCaptchaOnOff() {
        String captchaOnOff = selectSysConfigByKey("sys.account.captchaOnOff");
        if (StringUtils.isEmpty(captchaOnOff)) {
            return true;
        }
        return Converter.toBool(captchaOnOff);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectSysConfigByKey(String configKey) {
        String configValue = redisCache.getCacheObject(getCacheKey(configKey));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig sysConfig = configMapper.selectSysConfig(config);
        if (StringUtils.isNotNull(sysConfig)) {
            redisCache.setCacheObject(getCacheKey(configKey), sysConfig.getConfigValue());
            return sysConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }


    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY_PREFIX + configKey;
    }

}
