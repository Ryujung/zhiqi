package com.zhiqi.system.mapper;

import com.zhiqi.common.core.domain.entity.SysDictData;

/**
 * @Entity com.zhiqi.system.domain.SysDictData
 */
public interface SysDictDataMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysDictData record);

    int insertSelective(SysDictData record);

    SysDictData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictData record);

    int updateByPrimaryKey(SysDictData record);

}




