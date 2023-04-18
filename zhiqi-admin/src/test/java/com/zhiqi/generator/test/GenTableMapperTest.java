package com.zhiqi.generator.test;

import com.ryujung.zhiqi.common.utils.DateUtils;
import com.zhiqi.ZhiQiApplication;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.mapper.GenTableMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/17-16:11
 */
@SpringBootTest(classes = ZhiQiApplication.class)
public class GenTableMapperTest {
    @Autowired
    private GenTableMapper genTableMapper;

    private Long testTableId = 0L;
    public static final String TEST_TABLE_NAME = "testTableName1";

    @Test
    void insertGenTableTest() {
        GenTable genTable = new GenTable();
        genTable.setTableName(TEST_TABLE_NAME);
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
        int i = genTableMapper.insertGenTable(genTable);

        testTableId = genTable.getTableId();
        Assertions.assertEquals(1, i);
    }

    @Test
    void deleteByIdsTest() {
        int i = genTableMapper.deleteByIds();
        Assertions.assertEquals(0, i);
        if (!testTableId.equals(0L)) {
            i = genTableMapper.deleteByIds(testTableId);
            Assertions.assertEquals(1, i);
        }
    }

    @Test
    void selectGenTableByNameTest() {
        GenTable genTable = genTableMapper.selectGenTableByName(TEST_TABLE_NAME);
        Assertions.assertNotNull(genTable);
    }

    @Test
    void selectGenTableAllTest() {
        List<GenTable> genTableList = genTableMapper.selectGenTableAll();
        Assertions.assertNotNull(genTableList);
        Assertions.assertFalse(genTableList.isEmpty());
    }

    @Test
    void updateGenTableTest() {
        List<GenTable> genTableList = genTableMapper.selectGenTableAll();
        if (genTableList == null || genTableList.isEmpty()) {
            insertGenTableTest();
        } else {
            testTableId = genTableList.get(0).getTableId();
        }
        GenTable genTable = new GenTable();
        genTable.setTableId(testTableId);
        // 注意空值不应该触发更新
        genTable.setTableName("");
        String testUpdateComment = "test update comment";
        genTable.setTableComment(testUpdateComment);

        int i = genTableMapper.updateGenTable(genTable);
        Assertions.assertEquals(1, i);

        genTable = genTableMapper.selectGenTableById(testTableId);
        Assertions.assertNotNull(genTable.getTableName());
        Assertions.assertNotEquals("", genTable.getTableName());
    }

    @Test
    void selectGenTableListTest() {
        GenTable genTable = generateGenTableForSelect();

        List<GenTable> genTableList = genTableMapper.selectGenTableList(genTable);
        Assertions.assertNotNull(genTableList);
        Assertions.assertFalse(genTableList.isEmpty());
    }

    private GenTable generateGenTableForSelect() {
        GenTable genTable = new GenTable();
        genTable.setTableName(TEST_TABLE_NAME);
        genTable.setTableComment("comment");

        HashMap<String, Object> params = new HashMap<>();
        params.put("beginTime", "2020-01-01");
        String dateTimeNowStr = DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS);
        params.put("endTime", dateTimeNowStr);
        genTable.setParams(params);
        return genTable;
    }

    @Test
    void selectDbTableListTest() {
        GenTable genTable = generateGenTableForSelect();
        // test table name's pattern match.
        genTable.setTableName("user");
        genTable.setTableComment(null);
        List<GenTable> genTableList = genTableMapper.selectDbTableList(genTable);
        Assertions.assertNotNull(genTableList);
        Assertions.assertFalse(genTableList.isEmpty());
    }

    @Test
    void selectDbTableListByNameTest() {
        String[] names = null;
        List<GenTable> genTableList = genTableMapper.selectDbTableListByNames(names);
        Assertions.assertTrue(genTableList.isEmpty());

        names = new String[]{};
        genTableList = genTableMapper.selectDbTableListByNames(names);
        Assertions.assertTrue(genTableList.isEmpty());

        names = new String[]{"sys_job","sys_user"};
        genTableList = genTableMapper.selectDbTableListByNames(names);
        Assertions.assertFalse(genTableList.isEmpty());
    }

}
