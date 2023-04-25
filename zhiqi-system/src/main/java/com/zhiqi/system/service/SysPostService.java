package com.zhiqi.system.service;

import java.util.List;
import com.zhiqi.system.domain.SysPost;

/**
 * 岗位信息Service接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysPostService
{
    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 岗位信息
     */
    public SysPost selectSysPostByPostId(Long postId);

    /**
     * 查询岗位信息列表
     * 
     * @param sysPost 岗位信息
     * @return 岗位信息集合
     */
    public List<SysPost> selectSysPostList(SysPost sysPost);

    /**
     * 新增岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int insertSysPost(SysPost sysPost);

    /**
     * 修改岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int updateSysPost(SysPost sysPost);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位信息主键集合
     * @return 结果
     */
    public int deleteSysPostByPostIds(Long[] postIds);

    /**
     * 删除岗位信息信息
     * 
     * @param postId 岗位信息主键
     * @return 结果
     */
    public int deleteSysPostByPostId(Long postId);
}
