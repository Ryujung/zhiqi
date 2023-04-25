package com.zhiqi.system.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.zhiqi.common.core.domain.entity.SysRole;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysRoleMapper;
import com.zhiqi.system.service.SysRoleService;

/**
 * 角色信息Service业务层处理
 *
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询角色信息
     *
     * @param roleId 角色信息主键
     * @return 角色信息
     */
    @Override
    public SysRole selectSysRoleByRoleId(Long roleId) {
        return roleMapper.selectSysRoleByRoleId(roleId);
    }

    /**
     * 查询角色信息列表
     *
     * @param sysRole 角色信息
     * @return 角色信息
     */
    @Override
    public List<SysRole> selectSysRoleList(SysRole sysRole) {
        return roleMapper.selectSysRoleList(sysRole);
    }

    /**
     * 新增角色信息
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public int insertSysRole(SysRole sysRole) {
        sysRole.setCreateTime(DateUtils.getNowDate());
        return roleMapper.insertSysRole(sysRole);
    }

    /**
     * 修改角色信息
     *
     * @param sysRole 角色信息
     * @return 结果
     */
    @Override
    public int updateSysRole(SysRole sysRole) {
        sysRole.setUpdateTime(DateUtils.getNowDate());
        return roleMapper.updateSysRole(sysRole);
    }

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色信息主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleByRoleIds(Long[] roleIds) {
        return roleMapper.deleteSysRoleByRoleIds(roleIds);
    }

    /**
     * 删除角色信息信息
     *
     * @param roleId 角色信息主键
     * @return 结果
     */
    @Override
    public int deleteSysRoleByRoleId(Long roleId) {
        return roleMapper.deleteSysRoleByRoleId(roleId);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Collection<String> selectRolePermissionByUserId(Long userId) {
        HashSet<String> result = new HashSet<>();
        List<SysRole> roleList = roleMapper.selectRolePermissionByUserId(userId);
        for (SysRole role : roleList) {
            result.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
        }
        return result;
    }

}
