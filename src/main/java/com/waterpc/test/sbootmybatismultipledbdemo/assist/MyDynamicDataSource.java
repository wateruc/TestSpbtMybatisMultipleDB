package com.waterpc.test.sbootmybatismultipledbdemo.assist;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyDynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String dbName = DataSourceContextHolder.getDataSource();

		if (dbName == null) {
			// System.err.println("使用数据库write.............");
			// return DataSourceType.write.getType();
			throw new NullPointerException("数据库路由时，决定使用哪个数据库源类型不能为空...");
		}

		return dbName;
	}

}
