package com.zhiqi.system.domain;

import java.io.Serializable;

/**
 * 角色和部门关联表
 * @TableName sys_role_dept
 */
public class SysRoleDept implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}