package com.waterpc.test.sbootmybatismultipledbdemo.configs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceNameSetValues;
import com.waterpc.test.sbootmybatismultipledbdemo.assist.MyDynamicDataSource;

@Configuration
@MapperScan(basePackages = "com.waterpc.test.sbootmybatismultipledbdemo.dao.mysql",sqlSessionFactoryRef="mysqlSqlSessionFactory")
public class MysqlDataSourceConfiguration {

	private static Logger log = LoggerFactory.getLogger(MysqlDataSourceConfiguration.class);

	@Value("${mybatiscustom.mysql.mapperLocations}")
	private String mapperLocations;

	@Value("${dbinfor.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	/**
	 * 有多少个从库就要配置多少个
	 * 
	 * @return
	 */
	@Bean(name = "mysqlDataSource01",autowire=Autowire.BY_NAME)
	@ConfigurationProperties(prefix = "dbinfor.datasource.mysql01")
	public DataSource mysqlDataSource01() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean(name = "mysqlDataSource02",autowire=Autowire.BY_NAME)
	@ConfigurationProperties(prefix = "dbinfor.datasource.mysql02")
	public DataSource mysqlDataSource02() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	/**
	 * 把所有数据库都放在路由中
	 * 
	 * @return
	 */
	@Bean(name = "dynamicDataSouce",autowire=Autowire.BY_NAME)
	public DataSource dynamicDataSouce() {

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
		// 否则切换数据源时找不到正确的数据源
		targetDataSources.put(DataSourceNameSetValues.MYSQL01, mysqlDataSource01());
		targetDataSources.put(DataSourceNameSetValues.MYSQL02, mysqlDataSource02());

		// 路由类，寻找对应的数据源
		MyDynamicDataSource proxy = new MyDynamicDataSource();

		proxy.setDefaultTargetDataSource(mysqlDataSource01());// 默认库
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}

	@Bean(name = "mysqlSqlSessionFactory",autowire=Autowire.BY_NAME)
	@Primary
	public SqlSessionFactory sqlSessionFactorys() throws Exception {
		try {
			SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
			// sessionFactoryBean.setDataSource(roundRobinDataSouce);
			sessionFactoryBean.setDataSource(dynamicDataSouce());

			// 读取配置
			// sessionFactoryBean.setTypeAliasesPackage("com.fei.springboot.domain");

			// 设置mapper.xml文件所在位置
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
			sessionFactoryBean.setMapperLocations(resources);
			// 设置mybatis-config.xml配置文件位置
			// sessionFactoryBean.setConfigLocation(new
			// DefaultResourceLoader().getResource(configLocation));

			// 添加分页插件、打印sql插件
			// Interceptor[] plugins = new Interceptor[] { pageHelper(), new
			// SqlPrintInterceptor() };
			// sessionFactoryBean.setPlugins(plugins);

			return sessionFactoryBean.getObject();
		} catch (IOException e) {
			log.error("mybatis resolver mapper*xml is error", e);
			return null;
		} catch (Exception e) {
			log.error("mybatis sqlSessionFactoryBean create error", e);
			return null;
		}
	}
	
	@Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dynamicDataSouce") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
//	@Bean(name = "mysqlSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

}
