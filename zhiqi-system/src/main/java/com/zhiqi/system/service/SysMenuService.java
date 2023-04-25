package com.zhiqi.system.service;

import java.util.List;
import com.zhiqi.system.domain.SysMenu;

/**
 * 菜单权限Service接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysMenuService
{
    /**
     * 查询菜单权限
     * 
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    public SysMenu selectSysMenuByMenuId(Long menuId);

    /**
     * 查询菜单权限列表
     * 
     * @param sysMenu 菜单权限
     * @return 菜单权限集合
     */
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu);

    /**
     * 新增菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    public int insertSysMenu(SysMenu sysMenu);

    /**
     * 修改菜单权限
     * 
     * @param sysMenu 菜单权限
     * @return 结果
     */
    public int updateSysMenu(SysMenu sysMenu);

    /**
     * 批量删除菜单权限
     * 
     * @param menuIds 需要删除的菜单权限主键集合
     * @return 结果
     */
    public int deleteSysMenuByMenuIds(Long[] menuIds);

    /**
     * 删除菜单权限信息
     * 
     * @param menuId 菜单权限主键
     * @return 结果
     */
    public int deleteSysMenuByMenuId(Long menuId);
}
