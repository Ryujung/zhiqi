package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysOperLog;

/**
 * @Entity com.zhiqi.system.domain.SysOperLog
 */
public interface SysOperLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    SysOperLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);

}




