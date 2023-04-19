package com.zhiqi.system.mapper;

import com.zhiqi.common.core.domain.entity.SysUser;

/**
 * @Entity com.zhiqi.system.domain.SysUser
 */
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insertSysUser(SysUser sysUser);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}




