package com.zhiqi.web.controller.system;

import com.zhiqi.common.annotation.RepeatSubmit;
import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.controller.BaseController;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.common.core.domain.model.LoginBody;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.framework.web.service.SysLoginService;
import com.zhiqi.framework.web.service.SysPermissionService;
import com.zhiqi.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author RyuJung
 * @since 2023/4/25-13:03
 */
@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(),
                loginBody.getCode(), loginBody.getUuid());

        CommonResult result = CommonResult.success();
        result.put(Constants.TOKEN, token);
        return result;
    }

    @GetMapping("/getInfo")
    @RepeatSubmit
    public CommonResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Set<String> rolePermissionSet = permissionService.getRolePermission(user);
        Set<String> menuPermissionSet = permissionService.getMenuPermission(user);

        CommonResult result = success();
        result.put("user", user);
        result.put("roles", rolePermissionSet);
        result.put("permissions", menuPermissionSet);

        return result;
    }
}
