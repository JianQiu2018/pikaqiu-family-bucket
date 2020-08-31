package com.pikaqiu.familybucket.dynamicsource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceHolder.getDataSource();
        if (LOGGER.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("当前数据源名称为 [").append(dataSourceName).append("] ");
            if (Objects.isNull(dataSourceName)) {
                sb.append(",系统将使用默认数据源 [").append("master").append("]");
            }
            LOGGER.debug(sb.toString());
        }
        return Objects.isNull(dataSourceName) ? "master" : dataSourceName;
    }



}
