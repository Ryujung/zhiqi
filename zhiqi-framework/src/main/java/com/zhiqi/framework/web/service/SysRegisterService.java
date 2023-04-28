package com.zhiqi.framework.web.service;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.contant.UserConstants;
import com.zhiqi.common.core.domain.entity.SysUser;
import com.zhiqi.common.core.domain.model.RegisterBody;
import com.zhiqi.common.core.redis.RedisCache;
import com.zhiqi.common.exception.user.CaptchaException;
import com.zhiqi.common.exception.user.CaptchaExpireException;
import com.zhiqi.common.utils.MessageUtils;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.framework.manager.AsyncManager;
import com.zhiqi.framework.manager.factory.AsyncFactory;
import com.zhiqi.system.service.SysConfigService;
import com.zhiqi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册服务
 *
 * @author RyuJung
 * @since 2023/4/28-13:11
 */
@Service
public class SysRegisterService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysConfigService configService;

    @Autowired
    private SysUserService userService;

    public String register(RegisterBody registerInfo) {
        String msg = "";
        String username = registerInfo.getUsername();
        String password = registerInfo.getPassword();
        String code = registerInfo.getCode();
        boolean captchaOnOff = configService.selectCaptchaOnOff();

        if (captchaOnOff) {
            validateCaptcha(code, registerInfo.getUuid());
        }

        if (StringUtils.isEmpty(username)) {
            msg = "用户姓名不可为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "用户姓名长度必须在" + UserConstants.USERNAME_MIN_LENGTH + "到" + UserConstants.USERNAME_MAX_LENGTH + "个字符之间";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不可为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "用户密码长度必须在" + UserConstants.PASSWORD_MIN_LENGTH + "到" + UserConstants.PASSWORD_MAX_LENGTH + "个字符之间";
        } else if (userService.checkUserNameUnique(username)) {
            msg = "注册用户" + username + "失败，注册账号已存在";
        } else {
            msg = doRegister(username, password);
        }
        return msg;
    }

    private String doRegister(String username, String password) {
        SysUser user = new SysUser();
        user.setUserName(username);
        user.setNickName(username);

        user.setPassword(SecurityUtils.encryptPassword(password));
        boolean isRegisterSuccess = userService.insertSysUser(user);
        if (!isRegisterSuccess) {
            return "注册失败，请联系系统管理人员";
        }
        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.REGISTER,
                MessageUtils.message("user.register.success")));
        return StringUtils.EMPTY;
    }

    private void validateCaptcha(String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY_PREFIX + uuid;
        String captchaValue = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (StringUtils.isEmpty(captchaValue)) {
            throw new CaptchaExpireException();
        }
        if (!captchaValue.equals(code)) {
            throw new CaptchaException();
        }
    }
}
