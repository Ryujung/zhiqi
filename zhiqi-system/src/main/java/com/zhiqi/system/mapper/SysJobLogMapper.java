package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysJobLog;

/**
 * @Entity com.zhiqi.system.domain.SysJobLog
 */
public interface SysJobLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysJobLog record);

    int insertSelective(SysJobLog record);

    SysJobLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysJobLog record);

    int updateByPrimaryKey(SysJobLog record);

}




