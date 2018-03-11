package com.waterpc.test.sbootmybatismultipledbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.waterpc.test.sbootmybatismultipledbdemo.dao.sqlserver.ArticleInforEntityMapper;
import com.waterpc.test.sbootmybatismultipledbdemo.entity.ArticleInforEntity;

@Service
public class ArticleInforEntityService {

	@Autowired
	private ArticleInforEntityMapper articleInforMapper;
	
	@Transactional(transactionManager="sqlserverTransactionManager",propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public ArticleInforEntity getOneArticleInfo(int id){
		return articleInforMapper.selectByPrimaryKey(id);
	}
	
}
