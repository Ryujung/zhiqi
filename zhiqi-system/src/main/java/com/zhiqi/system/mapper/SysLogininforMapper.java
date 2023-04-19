package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysLogininfor;

/**
 * @Entity com.zhiqi.system.domain.SysLogininfor
 */
public interface SysLogininforMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLogininfor record);

    int insertSelective(SysLogininfor record);

    SysLogininfor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLogininfor record);

    int updateByPrimaryKey(SysLogininfor record);

}




