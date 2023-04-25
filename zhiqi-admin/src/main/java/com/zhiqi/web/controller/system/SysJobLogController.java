package com.zhiqi.web.controller.system;

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
import com.zhiqi.system.domain.SysJobLog;
import com.zhiqi.system.service.SysJobLogService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 定时任务调度日志Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController
{
    @Autowired
    private SysJobLogService sysJobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPerm('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJobLog sysJobLog)
    {
        startPage();
        List<SysJobLog> list = sysJobLogService.selectSysJobLogList(sysJobLog);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPerm('system:log:export')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysJobLog sysJobLog)
    {
        List<SysJobLog> list = sysJobLogService.selectSysJobLogList(sysJobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "定时任务调度日志数据");
    }

    /**
     * 获取定时任务调度日志详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:log:query')")
    @GetMapping(value = "/{jobLogId}")
    public CommonResult getInfo(@PathVariable("jobLogId") Long jobLogId)
    {
        return CommonResult.success(sysJobLogService.selectSysJobLogByJobLogId(jobLogId));
    }

    /**
     * 新增定时任务调度日志
     */
    @PreAuthorize("@ss.hasPerm('system:log:add')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysJobLog sysJobLog)
    {
        return toAjax(sysJobLogService.insertSysJobLog(sysJobLog));
    }

    /**
     * 修改定时任务调度日志
     */
    @PreAuthorize("@ss.hasPerm('system:log:edit')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysJobLog sysJobLog)
    {
        return toAjax(sysJobLogService.updateSysJobLog(sysJobLog));
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("@ss.hasPerm('system:log:remove')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobLogIds}")
    public CommonResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(sysJobLogService.deleteSysJobLogByJobLogIds(jobLogIds));
    }
}
