package com.zhiqi.system.mapper.test;

import java.util.Collections;
import java.util.Date;

import com.zhiqi.common.core.domain.entity.SysDept;
import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.system.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author RyuJung
 * @since 2023/4/18-16:41
 */
@SpringBootTest
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    public static final String TEST_NICK_NAME = "TestNickName";
    public static final String TEST_USER_NAME = "TestUserName";
    public static final String TEST_USER_EMAIL = "test@ryujung.com";

    @Test
    void insertSysUserTest() {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(1L);
        sysUser.setNickName(TEST_NICK_NAME);
        sysUser.setUserName(TEST_USER_NAME);
        sysUser.setEmail(TEST_USER_EMAIL);
        sysUser.setPhonenumber("");
        sysUser.setSex("");
        sysUser.setAvatar("");
        sysUser.setPassword("");
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setDelFlag("");
        sysUser.setLoginIp("");
        sysUser.setLoginDate(new Date());
        sysUser.setDept(new SysDept());
        sysUser.setRoles(Collections.emptyList());
        sysUser.setRoleIds(new Long[]{});
        sysUser.setPostIds(new Long[]{});
        sysUser.setRoleId(0L);
        sysUser.setSearchValue("");
        sysUser.setCreateBy("");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateBy("");
        sysUser.setUpdateTime(new Date());
        sysUser.setRemark("");
    }
}
