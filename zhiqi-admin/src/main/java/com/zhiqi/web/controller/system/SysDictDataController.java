package com.zhiqi.web.controller.system;

import java.util.List;

import com.zhiqi.common.core.domain.entity.SysDictData;
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
import com.zhiqi.system.service.SysDictDataService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 字典数据Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 查询字典数据列表
     */
    @PreAuthorize("@ss.hasPerm('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData sysDictData)
    {
        startPage();
        List<SysDictData> list = sysDictDataService.selectSysDictDataList(sysDictData);
        return getDataTable(list);
    }

    /**
     * 导出字典数据列表
     */
    @PreAuthorize("@ss.hasPerm('system:data:export')")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysDictData sysDictData)
    {
        List<SysDictData> list = sysDictDataService.selectSysDictDataList(sysDictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据数据");
    }

    /**
     * 获取字典数据详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:data:query')")
    @GetMapping(value = "/{dictCode}")
    public CommonResult getInfo(@PathVariable("dictCode") Long dictCode)
    {
        return CommonResult.success(sysDictDataService.selectSysDictDataByDictCode(dictCode));
    }

    /**
     * 新增字典数据
     */
    @PreAuthorize("@ss.hasPerm('system:data:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysDictData sysDictData)
    {
        return toAjax(sysDictDataService.insertSysDictData(sysDictData));
    }

    /**
     * 修改字典数据
     */
    @PreAuthorize("@ss.hasPerm('system:data:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysDictData sysDictData)
    {
        return toAjax(sysDictDataService.updateSysDictData(sysDictData));
    }

    /**
     * 删除字典数据
     */
    @PreAuthorize("@ss.hasPerm('system:data:remove')")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictCodes}")
    public CommonResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(sysDictDataService.deleteSysDictDataByDictCodes(dictCodes));
    }
}
