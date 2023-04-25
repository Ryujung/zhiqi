package com.zhiqi.system.service;

import java.util.List;
import com.zhiqi.system.domain.SysUserRole;

/**
 * 用户和角色关联Service接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysUserRoleService
{
    /**
     * 查询用户和角色关联
     * 
     * @param userId 用户和角色关联主键
     * @return 用户和角色关联
     */
    public SysUserRole selectSysUserRoleByUserId(Long userId);

    /**
     * 查询用户和角色关联列表
     * 
     * @param sysUserRole 用户和角色关联
     * @return 用户和角色关联集合
     */
    public List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole);

    /**
     * 新增用户和角色关联
     * 
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    public int insertSysUserRole(SysUserRole sysUserRole);

    /**
     * 修改用户和角色关联
     * 
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    public int updateSysUserRole(SysUserRole sysUserRole);

    /**
     * 批量删除用户和角色关联
     * 
     * @param userIds 需要删除的用户和角色关联主键集合
     * @return 结果
     */
    public int deleteSysUserRoleByUserIds(Long[] userIds);

    /**
     * 删除用户和角色关联信息
     * 
     * @param userId 用户和角色关联主键
     * @return 结果
     */
    public int deleteSysUserRoleByUserId(Long userId);
}
