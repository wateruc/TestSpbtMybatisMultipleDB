package com.waterpc.test.sbootmybatismultipledbdemo.assist;

public class DataSourceContextHolder {
	
	// 线程本地环境变量
	private static final ThreadLocal<String> local = new ThreadLocal<String>();

	public static ThreadLocal<String> getLocal() {
		return local;
	}

	public static void setDataSource(String dbName) {
		local.set(dbName);
	}

	public static String getDataSource() {
		return local.get();
	}

	public static void clear() {
		local.remove();
	}

}
