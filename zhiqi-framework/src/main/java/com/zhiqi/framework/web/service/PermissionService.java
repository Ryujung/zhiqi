package com.zhiqi.framework.web.service;

import com.zhiqi.common.core.domain.entity.SysRole;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author RyuJung
 * @since 2023/4/25-21:34
 */
@Service("ss")
public class PermissionService {

    private static final String ALL_PERMISSION = "*:*:*";
    private static final String SUPER_ADMIN = "admin";

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPerm(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Set<String> userPermissions = loginUser.getPermissions();
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(userPermissions)) {
            return false;
        }
        boolean result = userPermissions.contains(ALL_PERMISSION) || userPermissions.contains(StringUtils.trim(permission));
        return result;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
            return false;
        }
        List<SysRole> roles = loginUser.getUser().getRoles();
        for (SysRole sysRole : roles) {
            String roleKey = sysRole.getRoleKey();
            if (SUPER_ADMIN.equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
                return true;
            }
        }
        return false;
    }

}
