package com.zhiqi.web.controller.system;

import com.zhiqi.common.config.ZhiQiConfig;
import com.zhiqi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RyuJung
 * @since 2023/4/28-18:15
 */
@RestController
public class SysIndexController {

    @Autowired
    private ZhiQiConfig config;

    /**
     * 访问首页，提示语
     */
    @GetMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", config.getName(), config.getVersion());
    }

}
