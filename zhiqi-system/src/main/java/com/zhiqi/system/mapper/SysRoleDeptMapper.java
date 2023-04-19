package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysRoleDept;

/**
 * @Entity com.zhiqi.system.domain.SysRoleDept
 */
public interface SysRoleDeptMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept record);

    int updateByPrimaryKey(SysRoleDept record);

}




