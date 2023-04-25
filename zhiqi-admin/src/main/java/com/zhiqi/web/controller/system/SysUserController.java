package com.zhiqi.web.controller.system;

import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.system.service.SysPostService;
import com.zhiqi.system.service.SysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.zhiqi.system.service.SysUserService;
import com.zhiqi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 用户信息Controller
 *
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPostService postService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPerm('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = userService.selectSysUserList(sysUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPerm('system:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysUser sysUser) {
        List<SysUser> list = userService.selectSysUserList(sysUser);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:user:query')")
    @GetMapping(value = "/{userId}")
    public CommonResult getInfo(@PathVariable("userId") Long userId) {
        return CommonResult.success(userService.selectSysUserByUserId(userId));
    }

    /**
     * 新增用户信息
     */
//    @PreAuthorize("@ss.hasPerm('system:user:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysUser user) {
        if (!userService.checkUserNameUnique(user.getUserName())) {
            return CommonResult.error("新增用户'" + user.getUserName() + "'失败，账号已存在");
        }
        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user.getPhonenumber())) {
            return CommonResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkEmailUnique(user.getPhonenumber())) {
            return CommonResult.error("新增用户'" + user.getUserName() + "'失败，邮箱已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertSysUser(user));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPerm('system:user:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysUser sysUser) {
        return toAjax(userService.updateSysUser(sysUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPerm('system:user:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public CommonResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteSysUserByUserIds(userIds));
    }
}
