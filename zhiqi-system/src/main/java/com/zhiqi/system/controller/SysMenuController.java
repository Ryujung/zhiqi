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
import com.zhiqi.system.domain.SysMenu;
import com.zhiqi.system.service.SysMenuService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 菜单权限Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询菜单权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMenu sysMenu)
    {
        startPage();
        List<SysMenu> list = sysMenuService.selectSysMenuList(sysMenu);
        return getDataTable(list);
    }

    /**
     * 导出菜单权限列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:export')")
    @Log(title = "菜单权限", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysMenu sysMenu)
    {
        List<SysMenu> list = sysMenuService.selectSysMenuList(sysMenu);
        ExcelUtil<SysMenu> util = new ExcelUtil<SysMenu>(SysMenu.class);
        return util.exportExcel(list, "菜单权限数据");
    }

    /**
     * 获取菜单权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public CommonResult getInfo(@PathVariable("menuId") Long menuId)
    {
        return CommonResult.success(sysMenuService.selectSysMenuByMenuId(menuId));
    }

    /**
     * 新增菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单权限", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.insertSysMenu(sysMenu));
    }

    /**
     * 修改菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.updateSysMenu(sysMenu));
    }

    /**
     * 删除菜单权限
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuIds}")
    public CommonResult remove(@PathVariable Long[] menuIds)
    {
        return toAjax(sysMenuService.deleteSysMenuByMenuIds(menuIds));
    }
}
