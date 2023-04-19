package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysPost;

/**
 * @Entity com.zhiqi.system.domain.SysPost
 */
public interface SysPostMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysPost record);

    int insertSelective(SysPost record);

    SysPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPost record);

    int updateByPrimaryKey(SysPost record);

}




