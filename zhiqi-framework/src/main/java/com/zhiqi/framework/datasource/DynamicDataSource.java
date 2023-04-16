package com.zhiqi.framework.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author RyuJung
 * @since 2023/4/16-19:26
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultDataSource, Map<Object,Object> dataSourceMap) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(dataSourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
