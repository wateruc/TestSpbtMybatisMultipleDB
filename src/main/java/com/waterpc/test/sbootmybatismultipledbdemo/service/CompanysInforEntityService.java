package com.waterpc.test.sbootmybatismultipledbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.waterpc.test.sbootmybatismultipledbdemo.annotation.DataSourceNameSetAnnotation;
import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceNameSetValues;
import com.waterpc.test.sbootmybatismultipledbdemo.dao.mysql.CompanysInforEntityMapper;
import com.waterpc.test.sbootmybatismultipledbdemo.entity.CompanysInforEntity;

@Service
public class CompanysInforEntityService {

	@Autowired
	private CompanysInforEntityMapper companysInforMapper;
	
	@DataSourceNameSetAnnotation(value=DataSourceNameSetValues.MYSQL02)
	@Transactional(transactionManager="mysqlTransactionManager",propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public CompanysInforEntity getOneCompanyInfor(long id){
		return companysInforMapper.selectByPrimaryKey(id);
	}
	
//	private UserService getService(){   
//        // 采取这种方式的话，   
//        //@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)   
//        //必须设置为true   
//    /*  if(AopContext.currentProxy() != null){  
//            return (UserService)AopContext.currentProxy();  
//        }else{  
//            return this;  
//        }  
//        */   
//        return SpringContextUtil.getBean(this.getClass());   
//    }   

	
}
