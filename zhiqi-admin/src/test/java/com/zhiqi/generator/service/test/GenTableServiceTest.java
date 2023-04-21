package com.zhiqi.generator.service.test;

import com.zhiqi.BaseTest;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.service.GenTableService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/20-17:29
 */
public class GenTableServiceTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(GenTableServiceTest.class);

    @Autowired
    GenTableService genTableService;

    private List<GenTable> tableList;

    public GenTableServiceTest(){
        initData();
    }

    void initData() {
        tableList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
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
        long startTime = System.currentTimeMillis();
        int insertNum = genTableService.batchInsert(tableList);
        long endTime = System.currentTimeMillis();
        log.info("foreach标签方式批量数据插入测试耗时：" + (endTime - startTime) + " 毫秒，成功插入数据：" + insertNum + " 条");
    }

    /**
     * Batch执行器效率远高于mybatis中使用foreach的方式，
     * 且batch手动执行，对单次批量操作的数据量可以控制，避免单次执行的数据量过大
     * 需要在MySQL连接url中添加请求参数：rewriteBatchedStatements=true
     */
    @Test
    void testBatchInsertByBatchExecutor() {
        long startTime = System.currentTimeMillis();
        int insertNum = genTableService.batchInsertByBatchExecutor(tableList);
        long endTime = System.currentTimeMillis();
        log.info("Batch执行器方式批量数据插入测试耗时：" + (endTime - startTime) + " 毫秒，成功插入数据：" + insertNum + " 条");
    }


}
