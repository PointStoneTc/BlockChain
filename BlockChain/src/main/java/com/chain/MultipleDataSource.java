package com.chain;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.chain.DataSourceContextHolder;

public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}
