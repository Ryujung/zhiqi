package com.zhiqi.framework.web.service;

import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.enums.UserStatus;
import com.zhiqi.common.exception.ServiceException;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author RyuJung
 * @since 2023/4/26-10:29
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            log.info("登录用户：{} 不存在", username);
            throw new ServiceException(StringUtils.format("登录用户：{} 不存在", username));
        }else  if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除", username);
            throw new ServiceException(StringUtils.format("对不起，您的账号：{} 已被删除", username));
        }else if(UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用", username);
            throw new ServiceException(StringUtils.format("对不起，您的账号：{} 已被停用", username));
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user.getDeptId(), permissionService.getRolePermission(user), user);
    }

}
