package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysUserPost;

/**
 * @Entity com.zhiqi.system.domain.SysUserPost
 */
public interface SysUserPostMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserPost record);

    int insertSelective(SysUserPost record);

    SysUserPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserPost record);

    int updateByPrimaryKey(SysUserPost record);

}




