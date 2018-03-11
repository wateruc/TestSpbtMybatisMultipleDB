package com.waterpc.test.sbootmybatismultipledbdemo.configs;

import java.io.IOException;

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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.waterpc.test.sbootmybatismultipledbdemo.dao.sqlserver",sqlSessionFactoryRef="sqlserverSqlSessionFactory")
public class SqlServerDataSourceConfiguration {

	private static Logger log = LoggerFactory.getLogger(SqlServerDataSourceConfiguration.class);
	
	@Value("${mybatiscustom.sqlserver.mapperLocations}")
	private String mapperLocations;

	@Value("${dbinfor.datasource.type}")
	private Class<? extends DataSource> dataSourceType;
	
	@Bean(name = "sqlserverDataSource01",autowire=Autowire.BY_NAME)
	@ConfigurationProperties(prefix = "dbinfor.datasource.sqlserver")
	public DataSource sqlserverDataSource01() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	@Bean(name = "sqlserverSqlSessionFactory",autowire=Autowire.BY_NAME)
	public SqlSessionFactory sqlSessionFactorys() throws Exception {
		try {
			SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
			// sessionFactoryBean.setDataSource(roundRobinDataSouce);
			sessionFactoryBean.setDataSource(sqlserverDataSource01());

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
	
//	@Bean(name = "sqlserverSqlSessionTemplate",autowire=Autowire.BY_NAME)
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlserverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {   
//        return new SqlSessionTemplate(sqlSessionFactory);   
//    }   
       
    //事务管理   
//    @Bean   
//    public PlatformTransactionManager annotationDrivenTransactionManager() {   
//        return new DataSourceTransactionManager((DataSource)SpringContextUtil.getBean("roundRobinDataSouceProxy"));   
//    }   
	
	@Bean(name = "sqlserverTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("sqlserverDataSource01") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

	
}
