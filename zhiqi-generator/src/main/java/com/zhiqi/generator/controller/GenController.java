package com.zhiqi.generator.controller;

import com.ryujung.zhiqi.common.core.controller.BaseController;
import com.ryujung.zhiqi.common.core.domain.page.TableDataInfo;
import com.zhiqi.generator.model.GenTable;
import com.zhiqi.generator.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/gb/list")
    public TableDataInfo tableDataList(GenTable genTable) {
        startPage();
        List<GenTable> tableList = genTableService.selectDbTableList(genTable);
        return getTableData(tableList);
    }

    @PostMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * generate zip file
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"zhiqi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
