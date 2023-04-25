package com.zhiqi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysUserPostMapper;
import com.zhiqi.system.domain.SysUserPost;
import com.zhiqi.system.service.SysUserPostService;

/**
 * 用户与岗位关联Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysUserPostServiceImpl implements SysUserPostService
{
    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     * 查询用户与岗位关联
     * 
     * @param userId 用户与岗位关联主键
     * @return 用户与岗位关联
     */
    @Override
    public SysUserPost selectSysUserPostByUserId(Long userId)
    {
        return sysUserPostMapper.selectSysUserPostByUserId(userId);
    }

    /**
     * 查询用户与岗位关联列表
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 用户与岗位关联
     */
    @Override
    public List<SysUserPost> selectSysUserPostList(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.selectSysUserPostList(sysUserPost);
    }

    /**
     * 新增用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    @Override
    public int insertSysUserPost(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.insertSysUserPost(sysUserPost);
    }

    /**
     * 修改用户与岗位关联
     * 
     * @param sysUserPost 用户与岗位关联
     * @return 结果
     */
    @Override
    public int updateSysUserPost(SysUserPost sysUserPost)
    {
        return sysUserPostMapper.updateSysUserPost(sysUserPost);
    }

    /**
     * 批量删除用户与岗位关联
     * 
     * @param userIds 需要删除的用户与岗位关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserPostByUserIds(Long[] userIds)
    {
        return sysUserPostMapper.deleteSysUserPostByUserIds(userIds);
    }

    /**
     * 删除用户与岗位关联信息
     * 
     * @param userId 用户与岗位关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserPostByUserId(Long userId)
    {
        return sysUserPostMapper.deleteSysUserPostByUserId(userId);
    }
}
