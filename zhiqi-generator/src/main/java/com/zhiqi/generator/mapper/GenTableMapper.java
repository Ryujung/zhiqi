package com.zhiqi.generator.mapper;

import com.zhiqi.generator.domain.GenTable;

import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/16-17:38
 */
public interface GenTableMapper {
    /**
     * 根据名称获取生成表对象
     *
     * @param tableName
     * @return
     */
    GenTable selectGenTableByName(String tableName);

    /**
     * 根据名称获取生成表对象
     *
     * @param genTable
     * @return
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    int deleteByPrimaryKey(Long id);

    int insert(GenTable record);

    int insertSelective(GenTable record);

    GenTable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GenTable record);

    int updateByPrimaryKey(GenTable record);
}
