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
import com.zhiqi.system.domain.SysRoleDept;
import com.zhiqi.system.service.SysRoleDeptService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 角色和部门关联Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/dept")
public class SysRoleDeptController extends BaseController
{
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 查询角色和部门关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRoleDept sysRoleDept)
    {
        startPage();
        List<SysRoleDept> list = sysRoleDeptService.selectSysRoleDeptList(sysRoleDept);
        return getDataTable(list);
    }

    /**
     * 导出角色和部门关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:export')")
    @Log(title = "角色和部门关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysRoleDept sysRoleDept)
    {
        List<SysRoleDept> list = sysRoleDeptService.selectSysRoleDeptList(sysRoleDept);
        ExcelUtil<SysRoleDept> util = new ExcelUtil<SysRoleDept>(SysRoleDept.class);
        return util.exportExcel(list, "角色和部门关联数据");
    }

    /**
     * 获取角色和部门关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{roleId}")
    public CommonResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return CommonResult.success(sysRoleDeptService.selectSysRoleDeptByRoleId(roleId));
    }

    /**
     * 新增角色和部门关联
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "角色和部门关联", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysRoleDept sysRoleDept)
    {
        return toAjax(sysRoleDeptService.insertSysRoleDept(sysRoleDept));
    }

    /**
     * 修改角色和部门关联
     */
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "角色和部门关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysRoleDept sysRoleDept)
    {
        return toAjax(sysRoleDeptService.updateSysRoleDept(sysRoleDept));
    }

    /**
     * 删除角色和部门关联
     */
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "角色和部门关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public CommonResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(sysRoleDeptService.deleteSysRoleDeptByRoleIds(roleIds));
    }
}
