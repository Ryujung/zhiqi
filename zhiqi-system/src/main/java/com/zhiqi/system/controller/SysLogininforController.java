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
import com.zhiqi.system.domain.SysLogininfor;
import com.zhiqi.system.service.SysLogininforService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 系统访问记录Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private SysLogininforService sysLogininforService;

    /**
     * 查询系统访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor sysLogininfor)
    {
        startPage();
        List<SysLogininfor> list = sysLogininforService.selectSysLogininforList(sysLogininfor);
        return getDataTable(list);
    }

    /**
     * 导出系统访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:export')")
    @Log(title = "系统访问记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysLogininfor sysLogininfor)
    {
        List<SysLogininfor> list = sysLogininforService.selectSysLogininforList(sysLogininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "系统访问记录数据");
    }

    /**
     * 获取系统访问记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:query')")
    @GetMapping(value = "/{infoId}")
    public CommonResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return CommonResult.success(sysLogininforService.selectSysLogininforByInfoId(infoId));
    }

    /**
     * 新增系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:add')")
    @Log(title = "系统访问记录", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysLogininfor sysLogininfor)
    {
        return toAjax(sysLogininforService.insertSysLogininfor(sysLogininfor));
    }

    /**
     * 修改系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:edit')")
    @Log(title = "系统访问记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysLogininfor sysLogininfor)
    {
        return toAjax(sysLogininforService.updateSysLogininfor(sysLogininfor));
    }

    /**
     * 删除系统访问记录
     */
    @PreAuthorize("@ss.hasPermi('system:logininfor:remove')")
    @Log(title = "系统访问记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public CommonResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(sysLogininforService.deleteSysLogininforByInfoIds(infoIds));
    }
}
