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
import com.zhiqi.system.domain.SysRoleMenu;
import com.zhiqi.system.service.SysRoleMenuService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 角色和菜单关联Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/menu")
public class SysRoleMenuController extends BaseController
{
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 查询角色和菜单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRoleMenu sysRoleMenu)
    {
        startPage();
        List<SysRoleMenu> list = sysRoleMenuService.selectSysRoleMenuList(sysRoleMenu);
        return getDataTable(list);
    }

    /**
     * 导出角色和菜单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:export')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysRoleMenu sysRoleMenu)
    {
        List<SysRoleMenu> list = sysRoleMenuService.selectSysRoleMenuList(sysRoleMenu);
        ExcelUtil<SysRoleMenu> util = new ExcelUtil<SysRoleMenu>(SysRoleMenu.class);
        return util.exportExcel(list, "角色和菜单关联数据");
    }

    /**
     * 获取角色和菜单关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{roleId}")
    public CommonResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return CommonResult.success(sysRoleMenuService.selectSysRoleMenuByRoleId(roleId));
    }

    /**
     * 新增角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysRoleMenu sysRoleMenu)
    {
        return toAjax(sysRoleMenuService.insertSysRoleMenu(sysRoleMenu));
    }

    /**
     * 修改角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysRoleMenu sysRoleMenu)
    {
        return toAjax(sysRoleMenuService.updateSysRoleMenu(sysRoleMenu));
    }

    /**
     * 删除角色和菜单关联
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "角色和菜单关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public CommonResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(sysRoleMenuService.deleteSysRoleMenuByRoleIds(roleIds));
    }
}
