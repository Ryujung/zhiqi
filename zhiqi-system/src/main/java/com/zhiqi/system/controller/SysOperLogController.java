package com.zhiqi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhiqi.common.annotation.Log;
import com.zhiqi.common.core.controller.BaseController;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.enums.BusinessType;
import com.zhiqi.system.domain.SysOperLog;
import com.zhiqi.system.service.SysOperLogService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 操作日志记录Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/log")
public class SysOperLogController extends BaseController
{
    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 查询操作日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog sysOperLog)
    {
        startPage();
        List<SysOperLog> list = sysOperLogService.selectSysOperLogList(sysOperLog);
        return getDataTable(list);
    }

    /**
     * 导出操作日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "操作日志记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysOperLog sysOperLog)
    {
        List<SysOperLog> list = sysOperLogService.selectSysOperLogList(sysOperLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "操作日志记录数据");
    }

    /**
     * 获取操作日志记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{operId}")
    public CommonResult getInfo(@PathVariable("operId") Long operId)
    {
        return CommonResult.success(sysOperLogService.selectSysOperLogByOperId(operId));
    }

    /**
     * 新增操作日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "操作日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysOperLog sysOperLog)
    {
        return toAjax(sysOperLogService.insertSysOperLog(sysOperLog));
    }

    /**
     * 修改操作日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "操作日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysOperLog sysOperLog)
    {
        return toAjax(sysOperLogService.updateSysOperLog(sysOperLog));
    }

    /**
     * 删除操作日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "操作日志记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{operIds}")
    public CommonResult remove(@PathVariable Long[] operIds)
    {
        return toAjax(sysOperLogService.deleteSysOperLogByOperIds(operIds));
    }
}
