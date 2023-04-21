package com.zhiqi.generator.service.impl;

import com.zhiqi.common.contant.GenConstants;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.domain.GenTableColumn;
import com.zhiqi.generator.mapper.GenTableColumnMapper;
import com.zhiqi.generator.mapper.GenTableMapper;
import com.zhiqi.generator.service.GenTableService;
import com.zhiqi.generator.util.GenUtils;
import com.zhiqi.generator.util.VelocityInitializer;
import com.zhiqi.generator.util.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.jdbc.object.BatchSqlUpdate.DEFAULT_BATCH_SIZE;

/**
 * @author RyuJung
 * @since 2023/4/16-17:26
 */
@Service
public class GenTableServiceImpl implements GenTableService {

    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Autowired
    private GenTableMapper genTableMapper;

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        generatorCode(tableName, zipOutputStream);
        IOUtils.closeQuietly(zipOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     *
     * 基本流程
     * 查询表信息设置主键 - 初始化引擎 - 获取模板 - 遍历模板并生成template - 将template写入输出流，
     *
     * @param tableName       表名称
     * @param zipOutputStream 指定写出的输出流
     */
    private void generatorCode(String tableName, ZipOutputStream zipOutputStream) {
        GenTable table = genTableMapper.selectGenTableByName(tableName);
        // set table's pk column if exists pk ,or set the first column as pk
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }

        // set sub table's pk column if exists a pk ,or set the first column as pk
        String subTableName = table.getSubTableName();
        if (StringUtils.isNotEmpty(subTableName)) {
            table.setSubTable(genTableMapper.selectGenTableByName(subTableName));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.isPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (StringUtils.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }

        // initialize velocity engine
        VelocityInitializer.initVelocity();
        VelocityContext velocityContext = VelocityUtils.prepareContext(table);

        List<String> templateList = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String name : templateList) {
            StringWriter stringWriter = new StringWriter();
            Template template = Velocity.getTemplate(name);
            template.merge(velocityContext, stringWriter);
            try {
                zipOutputStream.putNextEntry(new ZipEntry(VelocityUtils.getFileName(name, table)));
                IOUtils.write(stringWriter.toString(), zipOutputStream, StandardCharsets.UTF_8);
                IOUtils.closeQuietly(stringWriter);
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }

    }

    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return genTableMapper.selectDbTableList(genTable);
    }

    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNameArr) {
        return genTableMapper.selectDbTableListByNames(tableNameArr);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importGenTable(List<GenTable> tableList) {
//        String operatorName = SecurityUtils.getUserName();  // FIXME
        for (GenTable table : tableList) {
            String tableName = table.getTableName();
//            GenUtils.initTable(table, operatorName);
            GenUtils.initTable(table, null); // FIXME
            int rowNum = genTableMapper.insertGenTable(table);
            if (rowNum > 0) {
                List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByTableName(tableName);
                for (GenTableColumn genTableColumn : genTableColumns) {
                    GenUtils.initColumnField(genTableColumn, table);
                    genTableColumnMapper.insertGenTableColumn(genTableColumn);
                }
            }
        }
    }

    @Override
    public int batchInsert(List<GenTable> tableList) {
        return genTableMapper.insertBatch(tableList);
    }

    @Override
    public int batchInsertByBatchExecutor(List<GenTable> tableList) {
        // 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            GenTableMapper mapper = sqlSession.getMapper(GenTableMapper.class);
            for (int i = 0; i < tableList.size(); i++) {
                mapper.insertGenTable(tableList.get(i));

                if (i % DEFAULT_BATCH_SIZE == 0 && i != 0) {
                    sqlSession.flushStatements();
                }
            }
            // 提交事务并关闭 SqlSession
            sqlSession.commit();
            return tableList.size();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            sqlSession.close();
        }

    }

}
