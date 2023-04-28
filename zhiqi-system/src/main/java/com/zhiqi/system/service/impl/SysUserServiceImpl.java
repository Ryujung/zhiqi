package com.zhiqi.system.service.impl;

import java.util.List;

import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysUserMapper;
import com.zhiqi.system.service.SysUserService;

/**
 * 用户信息Service业务层处理
 *
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public SysUser selectSysUserByUserId(Long userId) {
        return userMapper.selectSysUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     *
     * @param sysUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<SysUser> selectSysUserList(SysUser sysUser) {
        return userMapper.selectSysUserList(sysUser);
    }

    /**
     * 新增用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public boolean insertSysUser(SysUser sysUser) {
        sysUser.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertSysUser(sysUser) > 0;
    }

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        sysUser.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateSysUser(sysUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserIds(Long[] userIds) {
        return userMapper.deleteSysUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserId(Long userId) {
        return userMapper.deleteSysUserByUserId(userId);
    }

    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.updateSysUser(user);
    }

    @Override
    public boolean checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        return count == 0;
    }

    @Override
    public boolean checkPhoneUnique(String phoneNumber) {
        int count = userMapper.checkPhoneUnique(phoneNumber);
        return count == 0;
    }

    @Override
    public boolean checkEmailUnique(String email) {
        int count = userMapper.checkEmailUnique(email);
        return count == 0;
    }

    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectByUserName(username);
    }
}
