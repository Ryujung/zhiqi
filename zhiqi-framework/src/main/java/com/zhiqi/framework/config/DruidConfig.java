package com.zhiqi.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ryujung.zhiqi.common.enums.DataSourceType;
import com.zhiqi.framework.config.properties.DruidProperties;
import com.zhiqi.framework.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author RyuJung
 * @since 2023/4/16-18:55
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource datasource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(datasource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource datasource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(datasource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                 @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        HashMap<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        dataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, dataSources);
    }
}
