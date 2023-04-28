package com.zhiqi.framework.web.service;

import com.zhiqi.common.core.domain.entity.SysUser;
//import com.zhiqi.system.service.SysMenuService;
//import com.zhiqi.system.service.SysRoleService;
import com.zhiqi.system.service.SysMenuService;
import com.zhiqi.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RyuJung
 * @since 2023/4/25-14:40
 */
@Service
public class SysPermissionService {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysMenuService menuService;
    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        HashSet<String> roleSet = new HashSet<>();
        if (user.isAdmin()) {
            roleSet.add("admin");
        } else {
            roleSet.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }

        return roleSet;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        HashSet<String> permissionSet = new HashSet<>();
        if (user.isAdmin()) {
            permissionSet.add("*.*.*");
        }else{
            permissionSet.addAll(menuService.selectMenuListByUserId(user.getUserId()));
        }
        return permissionSet;
    }
}
