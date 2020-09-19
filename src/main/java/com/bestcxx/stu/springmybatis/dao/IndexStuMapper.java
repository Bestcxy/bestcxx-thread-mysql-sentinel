package com.bestcxx.stu.springmybatis.dao;

import com.bestcxx.stu.springmybatis.model.IndexStu;


public interface IndexStuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IndexStu record);

    int insertSelective(IndexStu record);

    IndexStu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexStu record);

    int updateByPrimaryKey(IndexStu record);
}