package com.zhiqi.system.mapper;
import org.apache.ibatis.annotations.Param;

import com.zhiqi.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysUserMapper {
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
     * 删除用户信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

    /**
     * 检查当前用户名是否已存在
     *
     * @param userName 用户名
     * @return 查询到的记录数
     */
    int checkUserNameUnique(String userName);

    /**
     * 根据电话号码查询，检查当前电话号码是否已存在
     *
     * @param phoneNumber 电话号码
     * @return 查询到的记录数
     */
    int checkPhoneUnique(String phoneNumber);

    /**
     * 根据电话号码查询，检查当前邮箱是否已存在
     *
     * @param email 邮箱
     * @return 查询到的记录数
     */
    int checkEmailUnique(String email);

    /**
     * 根据用户名查询
     *
     * @param userName 用户名
     * @return 用户数据
     */
    SysUser selectByUserName(String userName);
}
