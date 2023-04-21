package com.zhiqi.generator.service.impl;

import com.zhiqi.common.contant.GenConstants;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.domain.GenTableColumn;
import com.zhiqi.generator.mapper.GenTableMapper;
import com.zhiqi.generator.service.GenTableService;
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

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Override
    public int importGenTable(List<GenTable> tableList) {
        String username = SecurityUtils.getUserName();
        for (GenTable genTable : tableList) {
            String tableName = genTable.getTableName();


        }
        return 0;
    }

    @Override
    public int batchInsert(List<GenTable> tableList) {
        // 调用 Mapper 方法执行批量插入操作
        int insertNum = genTableMapper.insertBatch(tableList);

        return insertNum;
    }

    /**
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
     * 要注意设置合理的batch size，batch size过大可能造成性能下降。
     *
     * 如无特殊需求，建议batch size不超过1000。
     *
     * 当前设置的batch size 为5000
     *
     * @param tableList
     * @return
     */
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
