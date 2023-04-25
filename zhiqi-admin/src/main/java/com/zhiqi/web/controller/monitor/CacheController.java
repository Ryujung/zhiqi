package com.zhiqi.web.controller.monitor;

import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author RyuJung
 * @since 2023/4/25-12:41
 */
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public CommonResult getInfo() {
        Properties info = redisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Properties commandStats = redisTemplate.execute((RedisCallback<Properties>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::dbSize);

        HashMap<String, Object> result = new HashMap<>(16);
        result.put("info", info);
        result.put("dbSize", dbSize);

        // 封装commandStats相关的信息
        List<Map<String, String>> list = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            HashMap<String, String> map = new HashMap<>();
            String property = commandStats.getProperty(key);
            map.put("name", StringUtils.removeStart(key, "cmdstat_"));
            map.put("value", StringUtils.substringBetween(property, "calls=", ",usec="));
            list.add(map);
        });
        result.put("commandStats", list);

        return CommonResult.success(result);
    }
}
