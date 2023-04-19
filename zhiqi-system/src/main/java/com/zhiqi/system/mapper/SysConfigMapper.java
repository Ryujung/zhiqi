package com.zhiqi.system.mapper;
import org.apache.ibatis.annotations.Param;

import com.zhiqi.system.domain.SysConfig;

/**
 * @Entity com.zhiqi.system.domain.SysConfig
 */
public interface SysConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insertSysConfig(SysConfig sysConfig);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

}




