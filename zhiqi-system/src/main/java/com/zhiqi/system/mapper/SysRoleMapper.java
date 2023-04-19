package com.zhiqi.system.mapper;

import com.zhiqi.common.core.domain.entity.SysRole;

/**
 * @Entity com.zhiqi.system.domain.SysRole
 */
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}




