package com.zhiqi.generator.service.impl;

import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.mapper.GenTableMapper;
import com.zhiqi.generator.service.GenTableService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author RyuJung
 * @since 2023/4/16-17:26
 */
@Service
public class GenTableServiceImpl implements GenTableService {

    @Autowired
    private GenTableMapper genTableMapper;

    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        generatorCode(tableName, zipOutputStream);
        IOUtils.closeQuietly(zipOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 基本流程
     * 查询表信息 - 初始化引擎 - 获取模板 - 遍历模板并生成template - 将template写入输出流，
     *
     * @param tableName       表名称
     * @param zipOutputStream 指定写出的输出流
     */
    @Override
    public void generatorCode(String tableName, ZipOutputStream zipOutputStream) {
        GenTable genTable = genTableMapper.selectGenTableByName(tableName);


    }

    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return genTableMapper.selectDbTableList(genTable);
    }
}
