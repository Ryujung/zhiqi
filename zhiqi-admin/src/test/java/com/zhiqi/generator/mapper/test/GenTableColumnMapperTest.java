package com.zhiqi.generator.mapper.test;

import java.util.Date;

import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.domain.GenTableColumn;
import com.zhiqi.generator.mapper.GenTableColumnMapper;
import com.zhiqi.generator.mapper.GenTableMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author RyuJung
 * @since 2023/4/18-12:16
 */
@SpringBootTest
public class GenTableColumnMapperTest {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Autowired
    private GenTableMapper genTableMapper;

    private Long columnId = 0L;
    public static final String TEST_TABLE_COLUMN_NAME = "test_table_column_name";
    public static final String TEST_TABLE_COLUMN_COMMENT = "test_table_column_comment";

    @Test
    void insertGenTableColumnTest() {
        GenTable genTable = genTableMapper.selectGenTableByName(GenTableMapperTest.TEST_TABLE_NAME);
        Assertions.assertNotNull(genTable);

        GenTableColumn genTableColumn = new GenTableColumn();
        genTableColumn.setTableId(genTable.getTableId());
        genTableColumn.setColumnName(TEST_TABLE_COLUMN_NAME);
        genTableColumn.setColumnComment(TEST_TABLE_COLUMN_COMMENT);
        genTableColumn.setColumnType("");
        genTableColumn.setJavaType("");
        genTableColumn.setJavaField("");
        genTableColumn.setIsPk("");
        genTableColumn.setIsIncrement("");
        genTableColumn.setIsRequired("");
        genTableColumn.setIsInsert("");
        genTableColumn.setIsEdit("");
        genTableColumn.setIsList("");
        genTableColumn.setIsQuery("");
        genTableColumn.setQueryType("");
        genTableColumn.setHtmlType("");
        genTableColumn.setDictType("");
        genTableColumn.setSort(0);
        genTableColumn.setCreateBy("RyuJung");
        genTableColumn.setCreateTime(new Date());
        int i = genTableColumnMapper.insertGenTableColumn(genTableColumn);
        Assertions.assertEquals(1, i);
        Assertions.assertNotNull(genTableColumn.getColumnId());
        Assertions.assertNotNull(genTableColumn.getTableId());
        Assertions.assertNotNull(genTableColumn.getColumnName());
        Assertions.assertNotNull(genTableColumn.getColumnComment());
        Assertions.assertNotNull(genTableColumn.getCreateTime());
        Assertions.assertNotNull(genTableColumn.getCreateBy());
    }

}
