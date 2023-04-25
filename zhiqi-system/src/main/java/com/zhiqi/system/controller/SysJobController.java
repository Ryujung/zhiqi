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
import com.zhiqi.system.domain.SysJob;
import com.zhiqi.system.service.SysJobService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 定时任务调度Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/job")
public class SysJobController extends BaseController
{
    @Autowired
    private SysJobService sysJobService;

    /**
     * 查询定时任务调度列表
     */
    @PreAuthorize("@ss.hasPermi('system:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob)
    {
        startPage();
        List<SysJob> list = sysJobService.selectSysJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度列表
     */
    @PreAuthorize("@ss.hasPermi('system:job:export')")
    @Log(title = "定时任务调度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysJob sysJob)
    {
        List<SysJob> list = sysJobService.selectSysJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务调度数据");
    }

    /**
     * 获取定时任务调度详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:job:query')")
    @GetMapping(value = "/{jobId}")
    public CommonResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return CommonResult.success(sysJobService.selectSysJobByJobId(jobId));
    }

    /**
     * 新增定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('system:job:add')")
    @Log(title = "定时任务调度", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysJob sysJob)
    {
        return toAjax(sysJobService.insertSysJob(sysJob));
    }

    /**
     * 修改定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('system:job:edit')")
    @Log(title = "定时任务调度", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysJob sysJob)
    {
        return toAjax(sysJobService.updateSysJob(sysJob));
    }

    /**
     * 删除定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('system:job:remove')")
    @Log(title = "定时任务调度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public CommonResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(sysJobService.deleteSysJobByJobIds(jobIds));
    }
}
