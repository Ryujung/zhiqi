package com.zhiqi.generator.mapper;

import com.zhiqi.generator.model.GenTable;

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

    List<GenTable> selectDbTableList(GenTable genTable);
}
