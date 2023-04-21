package com.zhiqi.generator.controller;

import com.zhiqi.common.core.controller.BaseController;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.core.domain.page.TableDataInfo;
import com.zhiqi.common.core.text.Converter;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.generator.domain.GenTable;
import com.zhiqi.generator.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/16-17:23
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    @Autowired
    private GenTableService genTableService;

    /**
     * 响应请求分页数据
     */
    @GetMapping("/db/list")
    public TableDataInfo tableDataList(GenTable genTable) {
        startPage();
        List<GenTable> tableList = genTableService.selectDbTableList(genTable);
        return getDataTable(tableList);
    }

    /**
     * 生成代码（下载方式）
     */
    @PostMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(data);
    }

    @PostMapping("/importTable")
    public CommonResult importTableSave(String tables) {
        String[] tableNameArr = Converter.toStrArray(tables);
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNameArr);
        genTableService.importGenTable(tableList);
        return CommonResult.success();
    }

    /**
     * generate zip file
     */
    private void genCode(byte[] data) throws IOException {
        HttpServletResponse response = ServletUtils.getResponse();
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"zhiqi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
