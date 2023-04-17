package com.zhiqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author RyuJung
 * @since 2023/4/16-23:10
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZhiQiApplication {

    // FIXME not start, because of the datasource problem

    /**
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(ZhiQiApplication.class, args);
    }

}
