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
import com.zhiqi.system.domain.SysRole;
import com.zhiqi.system.service.SysRoleService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 角色信息Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController
{
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询角色信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRole sysRole)
    {
        startPage();
        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
        return getDataTable(list);
    }

    /**
     * 导出角色信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:export')")
    @Log(title = "角色信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysRole sysRole)
    {
        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        return util.exportExcel(list, "角色信息数据");
    }

    /**
     * 获取角色信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public CommonResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return CommonResult.success(sysRoleService.selectSysRoleByRoleId(roleId));
    }

    /**
     * 新增角色信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @Log(title = "角色信息", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysRole sysRole)
    {
        return toAjax(sysRoleService.insertSysRole(sysRole));
    }

    /**
     * 修改角色信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysRole sysRole)
    {
        return toAjax(sysRoleService.updateSysRole(sysRole));
    }

    /**
     * 删除角色信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @Log(title = "角色信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public CommonResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(sysRoleService.deleteSysRoleByRoleIds(roleIds));
    }
}
