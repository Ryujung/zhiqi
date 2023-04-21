package com.zhiqi.generator.mapper;
import java.util.Collection;

import com.zhiqi.generator.domain.GenTable;

import java.util.List;

/**
 * @Entity com.zhiqi.generator.mybatisx.domain.GenTable
 */
public interface GenTableMapper {

    int insertBatch(Collection<GenTable> tableList);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    int insertGenTable(GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteByIds(Long... ids);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    int updateGenTable(GenTable genTable);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询数据库列表
     *
     * @param names 表名称数组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String... names);

    /**
     * 查询表ID业务信息
     *
     * @param tableId 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long tableId);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);
}




