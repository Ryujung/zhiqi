package com.zhiqi.system.mapper;

import com.zhiqi.system.domain.SysNotice;

/**
 * @Entity com.zhiqi.system.domain.SysNotice
 */
public interface SysNoticeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNotice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKey(SysNotice record);

}




