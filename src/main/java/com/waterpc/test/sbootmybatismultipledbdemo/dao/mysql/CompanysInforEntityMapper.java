package com.waterpc.test.sbootmybatismultipledbdemo.dao.mysql;

import com.waterpc.test.sbootmybatismultipledbdemo.entity.CompanysInforEntity;

public interface CompanysInforEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanysInforEntity record);

    int insertSelective(CompanysInforEntity record);

    CompanysInforEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanysInforEntity record);

    int updateByPrimaryKey(CompanysInforEntity record);
}