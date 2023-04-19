package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysJob;

/**
 * @Entity com.zhiqi.system.domain.SysJob
 */
public interface SysJobMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysJob record);

    int insertSelective(SysJob record);

    SysJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysJob record);

    int updateByPrimaryKey(SysJob record);

}




