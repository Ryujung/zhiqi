package com.zhiqi.web.controller.system;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.controller.BaseController;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.core.domain.model.RegisterBody;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.framework.web.service.SysRegisterService;
import com.zhiqi.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新用户注册接口
 *
 * @author RyuJung
 * @since 2023/4/28-13:00
 */
@RestController
public class SysRegisterController extends BaseController {

    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private SysConfigService configService;

    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterBody registerInfo) {
        String isAllowedRegister = configService.selectSysConfigByKey(Constants.SYS_CONFIG_KEY_REGISTER);
        if (!"true".equals(isAllowedRegister)) {
            return error("系统尚未开启注册功能！");
        }
        String msg = registerService.register(registerInfo);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

}
