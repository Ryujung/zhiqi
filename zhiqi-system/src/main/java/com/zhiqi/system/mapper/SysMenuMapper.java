package com.zhiqi.system.mapper;

import com.zhiqi.common.core.domain.entity.SysMenu;

import java.util.List;

/**
 * 菜单权限Mapper接口
 *
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysMenuMapper {
    /**
     * 查询菜单权限
     *
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    SysMenu selectSysMenuByMenuId(Long menuId);

    /**
     * 查询菜单权限列表
     *
     * @param sysMenu 菜单权限
     * @return 菜单权限集合
     */
    List<SysMenu> selectSysMenuList(SysMenu sysMenu);

    /**
     * 新增菜单权限
     *
     * @param sysMenu 菜单权限
     * @return 结果
     */
    int insertSysMenu(SysMenu sysMenu);

    /**
     * 修改菜单权限
     *
     * @param sysMenu 菜单权限
     * @return 结果
     */
    int updateSysMenu(SysMenu sysMenu);

    /**
     * 删除菜单权限
     *
     * @param menuId 菜单权限主键
     * @return 结果
     */
    int deleteSysMenuByMenuId(Long menuId);

    /**
     * 批量删除菜单权限
     *
     * @param menuIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysMenuByMenuIds(Long[] menuIds);

    /**
     * 根据用户ID获取对应的菜单集合
     *
     * @param userId 用户ID
     * @return 菜单名称集合
     */
    List<String> selectMenuListByUserId(Long userId);
}
