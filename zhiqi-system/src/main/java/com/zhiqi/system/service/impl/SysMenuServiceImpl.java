package com.zhiqi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.zhiqi.common.core.domain.entity.SysMenu;
import com.zhiqi.common.utils.DateUtils;
import com.zhiqi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysMenuMapper;
import com.zhiqi.system.service.SysMenuService;

/**
 * 菜单权限Service业务层处理
 *
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 查询菜单权限
     *
     * @param menuId 菜单权限主键
     * @return 菜单权限
     */
    @Override
    public SysMenu selectSysMenuByMenuId(Long menuId) {
        return menuMapper.selectSysMenuByMenuId(menuId);
    }

    /**
     * 查询菜单权限列表
     *
     * @param sysMenu 菜单权限
     * @return 菜单权限
     */
    @Override
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu) {
        return menuMapper.selectSysMenuList(sysMenu);
    }

    /**
     * 新增菜单权限
     *
     * @param sysMenu 菜单权限
     * @return 结果
     */
    @Override
    public int insertSysMenu(SysMenu sysMenu) {
        sysMenu.setCreateTime(DateUtils.getNowDate());
        return menuMapper.insertSysMenu(sysMenu);
    }

    /**
     * 修改菜单权限
     *
     * @param sysMenu 菜单权限
     * @return 结果
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        sysMenu.setUpdateTime(DateUtils.getNowDate());
        return menuMapper.updateSysMenu(sysMenu);
    }

    /**
     * 批量删除菜单权限
     *
     * @param menuIds 需要删除的菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuIds(Long[] menuIds) {
        return menuMapper.deleteSysMenuByMenuIds(menuIds);
    }

    /**
     * 删除菜单权限信息
     *
     * @param menuId 菜单权限主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByMenuId(Long menuId) {
        return menuMapper.deleteSysMenuByMenuId(menuId);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 用户的菜单权限字段合集
     */
    @Override
    public Set<String> selectMenuListByUserId(Long userId) {
        List<String> menuList = menuMapper.selectMenuListByUserId(userId);

        HashSet<String> result = new HashSet<>();

        for (String perms : menuList) {
            if (StringUtils.isNotEmpty(perms)) {
                result.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }
        return result;
    }
}
