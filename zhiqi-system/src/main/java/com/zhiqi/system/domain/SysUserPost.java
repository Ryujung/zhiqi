package com.zhiqi.system.domain;

import java.io.Serializable;

/**
 * 用户与岗位关联表
 * @TableName sys_user_post
 */
public class SysUserPost implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 岗位ID
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 岗位ID
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }
}