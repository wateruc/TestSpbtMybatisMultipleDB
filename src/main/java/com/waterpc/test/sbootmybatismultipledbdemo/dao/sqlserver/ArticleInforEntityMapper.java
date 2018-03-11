package com.waterpc.test.sbootmybatismultipledbdemo.dao.sqlserver;

import com.waterpc.test.sbootmybatismultipledbdemo.entity.ArticleInforEntity;

public interface ArticleInforEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleInforEntity record);

    int insertSelective(ArticleInforEntity record);

    ArticleInforEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleInforEntity record);

    int updateByPrimaryKey(ArticleInforEntity record);
}