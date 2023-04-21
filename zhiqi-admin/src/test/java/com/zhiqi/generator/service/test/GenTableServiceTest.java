package com.zhiqi.generator.service.test;

import com.zhiqi.BaseTest;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.service.GenTableService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author RyuJung
 * @since 2023/4/20-17:29
 */
public class GenTableServiceTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(GenTableServiceTest.class);

    @Autowired
    GenTableService genTableService;

    private List<GenTable> tableList;
    private static final int TEST_DATA_COUNT = 10_000;

    public GenTableServiceTest() {
        initData();
    }

    void initData() {
        tableList = new ArrayList<>();
        for (int i = 1; i <= TEST_DATA_COUNT; i++) {
            GenTable genTable = new GenTable();
            genTable.setTableName("testTableName_" + i);
            genTable.setTableComment("testableComment1");
            genTable.setClassName("");
            genTable.setTplCategory("");
            genTable.setPackageName("");
            genTable.setModuleName("");
            genTable.setBusinessName("");
            genTable.setFunctionName("");
            genTable.setFunctionAuthor("");
            genTable.setGenType("");
            genTable.setGenPath("");
            genTable.setCreateBy("RyuJung");
            genTable.setCreateTime(new Date());
            genTable.setRemark("test fake data");
            tableList.add(genTable);
        }
    }

    @Test
    void testBatchInsert() {
        long executeTimeMillis = batchInsertAndLogTimeMillis(genTableService::batchInsert, tableList);
        log.info("foreach拼接方式批量插入{}条数据，总耗时：{} 毫秒", TEST_DATA_COUNT, executeTimeMillis);
        //foreach拼接方式批量插入10000条数据，总耗时：1605 毫秒
    }

    /**
     * Batch执行器效率远高于mybatis中使用foreach的方式，
     * 且batch手动执行，对单次批量操作的数据量可以控制，避免单次执行的数据量过大
     * 需要在MySQL连接url中添加请求参数：rewriteBatchedStatements=true
     */
    @Test
    void testBatchInsertByBatchExecutor() {
        long executeTimeMillis = batchInsertAndLogTimeMillis(genTableService::batchInsertByBatchExecutor, tableList);
        log.info("Batch执行器批量插入{}条数据，总耗时：{} 毫秒", TEST_DATA_COUNT, executeTimeMillis);
        //Batch执行器批量插入10000条数据，总耗时：772 毫秒
    }

    /**
     * 执行批量插入并返回记录方法的执行时间，单位：毫秒
     *
     * @param function 执行批量插入的方法，该方法应该接收List参数，返回插入数量（int）类型
     * @param dataList 要插入的数据列表
     * @param <T>      数据类型，例如需要向gen_table表中批量插入GenTable类型的数据
     * @return 批量插入方法总执行时间
     */
    private <T> long batchInsertAndLogTimeMillis(Function<List<T>, Integer> function, List<T> dataList) {
        long startTime = System.currentTimeMillis();
        Integer resultNum = function.apply(dataList);
        // 判断是否成功完成批量插入
        Assertions.assertEquals(TEST_DATA_COUNT, resultNum);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

}
