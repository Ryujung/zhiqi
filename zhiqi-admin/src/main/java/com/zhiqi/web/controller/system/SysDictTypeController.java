package com.zhiqi.web.controller.system;

import java.util.List;

import com.zhiqi.common.core.domain.entity.SysDictType;
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
import com.zhiqi.system.service.SysDictTypeService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 字典类型Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/type")
public class SysDictTypeController extends BaseController
{
    @Autowired
    private SysDictTypeService sysDictTypeService;

    /**
     * 查询字典类型列表
     */
    @PreAuthorize("@ss.hasPerm('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType sysDictType)
    {
        startPage();
        List<SysDictType> list = sysDictTypeService.selectSysDictTypeList(sysDictType);
        return getDataTable(list);
    }

    /**
     * 导出字典类型列表
     */
    @PreAuthorize("@ss.hasPerm('system:type:export')")
    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysDictType sysDictType)
    {
        List<SysDictType> list = sysDictTypeService.selectSysDictTypeList(sysDictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "字典类型数据");
    }

    /**
     * 获取字典类型详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:type:query')")
    @GetMapping(value = "/{dictId}")
    public CommonResult getInfo(@PathVariable("dictId") Long dictId)
    {
        return CommonResult.success(sysDictTypeService.selectSysDictTypeByDictId(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPerm('system:type:add')")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysDictType sysDictType)
    {
        return toAjax(sysDictTypeService.insertSysDictType(sysDictType));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("@ss.hasPerm('system:type:edit')")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysDictType sysDictType)
    {
        return toAjax(sysDictTypeService.updateSysDictType(sysDictType));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPerm('system:type:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictIds}")
    public CommonResult remove(@PathVariable Long[] dictIds)
    {
        return toAjax(sysDictTypeService.deleteSysDictTypeByDictIds(dictIds));
    }
}
