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

    public Set<String> getRolePermission(SysUser user) {
        HashSet<String> roleSet = new HashSet<>();
        if (user.isAdmin()) {
            roleSet.add("admin");
        } else {
            roleSet.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }

        return roleSet;
    }
}
