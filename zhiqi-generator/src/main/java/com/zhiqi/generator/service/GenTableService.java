package com.zhiqi.generator.service;

import com.zhiqi.generator.domain.GenTable;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author RyuJung
 * @since 2023/4/16-17:25
 */
public interface GenTableService {

    /**
     * 获取生成的代码数据（下载方式）
     *
     * @param tableName 表名称
     * @return 数据字节数组
     */
    byte[] downloadCode(String tableName);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     */
    void generatorCode(String tableName, ZipOutputStream zipOutputStream);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);
}
