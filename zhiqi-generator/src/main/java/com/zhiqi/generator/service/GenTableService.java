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
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 根据表名称数组，查询据库列表
     *
     * @param tableNameArr 表名称数组，英文逗号隔开
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNameArr);

    int importGenTable(List<GenTable> tableList);

    /**
     * 使用Mybatis XML中的foreach标签组装insert语句，形如：
     * insert(..) values(..),(..);
     *
     * @param tableList 数据库表对象集合
     * @return 插入数据记录数
     */
    int batchInsert(List<GenTable> tableList);

    /**
     * 使用BatchExecutor批量执行器进行批量数据插入
     * <br>
     * [华为云数据库中间件DDM中对SQL使用的最佳实践](https://support.huaweicloud.com/bestpractice-ddm/ddm_01_0014.html)
     *
     * MySQL的JDBC连接的url中要加rewriteBatchedStatements参数，
     * 并保证5.1.13以上版本的驱动，才能实现批量插入。
     * MySQL JDBC驱动在默认情况下会无视executeBatch()语句，
     * 把预计批量执行的一组sql语句拆散，一条一条地发给MySQL数据库，
     * 批量插入实际上是单条插入，直接造成较低的性能。
     * 只有把rewriteBatchedStatements参数置为true,
     * 驱动才会批量执行SQL。另外这个选项对INSERT/UPDATE/DELETE操作都有效。
     *
     * 注意：
     * 开启rewriteBatchedStatements后的批量INSERT/UPDATE/DELETE操作中，
     * 要注意设置合理的batch size，单次操作的数据量过大可能造成性能下降。
     *
     * 如无特殊需求，建议batch size不超过1000。
     *
     * 当前设置的batch size 为5000
     *
     * @param tableList 数据库表对象集合
     * @return 插入数据记录数
     */
    public int batchInsertByBatchExecutor(List<GenTable> tableList);

}
