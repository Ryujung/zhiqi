package com.zhiqi.generator.test;


import java.util.Date;

import com.zhiqi.generator.domain.GenTableColumn;

import com.zhiqi.ZhiQiApplication;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.mapper.GenTableMapper;
import org.apache.commons.compress.utils.Lists;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/17-16:11
 */
@SpringBootTest(classes = ZhiQiApplication.class)
public class GenTableMapperTest {
    @Autowired
    private GenTableMapper genTableMapper;

    @Test
    void insertGenTableTest() {
        GenTable genTable = new GenTable();
        genTable.setTableName("testTableName1");
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
        genTable.setRemark("test fake data");
        int i = genTableMapper.insertGenTable(genTable);
        System.out.println(genTable.getTableId());
        Assertions.assertEquals(1, i);
    }

    @Test
    void selectGenTableByNameTest() {
        GenTable genTable = genTableMapper.selectGenTableByName("sys_user");
        System.out.println(genTable);
        Assertions.assertNotNull(genTable);
    }

    @Test
    void selectGenTableAllTest() {
        List<GenTable> genTableList = genTableMapper.selectGenTableAll();
        System.out.println(genTableList);
        Assertions.assertNotNull(genTableList);
    }
}
