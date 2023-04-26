package com.zhiqi.system.service;

import com.zhiqi.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysUserService {
    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public SysUser selectSysUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合
     */
    public List<SysUser> selectSysUserList(SysUser sysUser);

    /**
     * 新增用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    public int insertSysUser(SysUser sysUser);

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    public int updateSysUser(SysUser sysUser);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUserProfile(SysUser user);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return true unique ,false not unique
     */
    boolean checkUserNameUnique(String userName);

    /**
     * 校验注册电话号码是否唯一
     *
     * @param phoneNumber 电话号码
     * @return true unique ,false not unique
     */
    boolean checkPhoneUnique(String phoneNumber);

    /**
     * 校验注册邮箱地址是否唯一
     *
     * @param email 邮箱地址
     * @return true unique ,false not unique
     */
    boolean checkEmailUnique(String email);

    /**
     * 查询用户信息列表
     *
     * @param username 用户名
     * @return 用户信息集合
     */
    SysUser selectUserByUserName(String username);
}
