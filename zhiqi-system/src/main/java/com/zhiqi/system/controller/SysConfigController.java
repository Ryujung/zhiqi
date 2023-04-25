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
import com.zhiqi.system.domain.SysConfig;
import com.zhiqi.system.service.SysConfigService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 参数配置Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 查询参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig sysConfig)
    {
        startPage();
        List<SysConfig> list = sysConfigService.selectSysConfigList(sysConfig);
        return getDataTable(list);
    }

    /**
     * 导出参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:export')")
    @Log(title = "参数配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysConfig sysConfig)
    {
        List<SysConfig> list = sysConfigService.selectSysConfigList(sysConfig);
        ExcelUtil<SysConfig> util = new ExcelUtil<>(SysConfig.class);
        return util.exportExcel(list, "参数配置数据");
    }

    /**
     * 获取参数配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{configId}")
    public CommonResult getInfo(@PathVariable("configId") Integer configId)
    {
        return CommonResult.success(sysConfigService.selectSysConfigByConfigId(configId));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "参数配置", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysConfig sysConfig)
    {
        return toAjax(sysConfigService.insertSysConfig(sysConfig));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "参数配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysConfig sysConfig)
    {
        return toAjax(sysConfigService.updateSysConfig(sysConfig));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{configIds}")
    public CommonResult remove(@PathVariable Integer[] configIds)
    {
        return toAjax(sysConfigService.deleteSysConfigByConfigIds(configIds));
    }
}
