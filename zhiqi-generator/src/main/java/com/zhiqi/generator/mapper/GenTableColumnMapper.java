package com.zhiqi.generator.mapper;

import com.zhiqi.generator.domain.GenTableColumn;

/**
 * @Entity com.zhi.generator.mybatisx.domain.GenTableColumn
 */
public interface GenTableColumnMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GenTableColumn record);

    int insertSelective(GenTableColumn record);

    GenTableColumn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GenTableColumn record);

    int updateByPrimaryKey(GenTableColumn record);

}




