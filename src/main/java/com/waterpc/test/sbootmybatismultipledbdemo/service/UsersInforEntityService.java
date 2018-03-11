package com.waterpc.test.sbootmybatismultipledbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.waterpc.test.sbootmybatismultipledbdemo.annotation.DataSourceNameSetAnnotation;
import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceNameSetValues;
import com.waterpc.test.sbootmybatismultipledbdemo.dao.mysql.UsersInforEntityMapper;
import com.waterpc.test.sbootmybatismultipledbdemo.entity.UsersInforEntity;

@Service
public class UsersInforEntityService {

	@Autowired
	private UsersInforEntityMapper usersInforMapper;
	
	@DataSourceNameSetAnnotation(value=DataSourceNameSetValues.MYSQL01)
	@Transactional(transactionManager="mysqlTransactionManager",propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public UsersInforEntity getOneUserInfor(Long id){
		return usersInforMapper.selectByPrimaryKey(id);
	}
	
}
