package com.zhiqi.generator.util;

import com.zhiqi.common.contant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * VelocityEngine工厂
 * 
 * @author ruoyi
 */
public class VelocityInitializer
{
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties properties = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            properties.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            properties.setProperty(Velocity.OUTPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(properties);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
