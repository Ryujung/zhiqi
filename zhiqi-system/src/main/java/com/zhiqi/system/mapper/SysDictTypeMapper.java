package com.zhiqi.system.mapper;

import com.zhiqi.common.core.domain.entity.SysDictType;

/**
 * @Entity com.zhiqi.system.domain.SysDictType
 */
public interface SysDictTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);

}




