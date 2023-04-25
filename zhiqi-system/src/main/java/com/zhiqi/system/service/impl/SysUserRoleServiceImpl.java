package com.zhiqi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysUserRoleMapper;
import com.zhiqi.system.domain.SysUserRole;
import com.zhiqi.system.service.SysUserRoleService;

/**
 * 用户和角色关联Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService
{
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户和角色关联
     * 
     * @param userId 用户和角色关联主键
     * @return 用户和角色关联
     */
    @Override
    public SysUserRole selectSysUserRoleByUserId(Long userId)
    {
        return sysUserRoleMapper.selectSysUserRoleByUserId(userId);
    }

    /**
     * 查询用户和角色关联列表
     * 
     * @param sysUserRole 用户和角色关联
     * @return 用户和角色关联
     */
    @Override
    public List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole)
    {
        return sysUserRoleMapper.selectSysUserRoleList(sysUserRole);
    }

    /**
     * 新增用户和角色关联
     * 
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    @Override
    public int insertSysUserRole(SysUserRole sysUserRole)
    {
        return sysUserRoleMapper.insertSysUserRole(sysUserRole);
    }

    /**
     * 修改用户和角色关联
     * 
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    @Override
    public int updateSysUserRole(SysUserRole sysUserRole)
    {
        return sysUserRoleMapper.updateSysUserRole(sysUserRole);
    }

    /**
     * 批量删除用户和角色关联
     * 
     * @param userIds 需要删除的用户和角色关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserRoleByUserIds(Long[] userIds)
    {
        return sysUserRoleMapper.deleteSysUserRoleByUserIds(userIds);
    }

    /**
     * 删除用户和角色关联信息
     * 
     * @param userId 用户和角色关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserRoleByUserId(Long userId)
    {
        return sysUserRoleMapper.deleteSysUserRoleByUserId(userId);
    }
}
