package com.bestcxx.stu.springmybatis.dao;

import com.bestcxx.stu.springmybatis.model.IndexStu;

import java.util.List;


public interface IndexStuExtMapper {

    int insertList(List<IndexStu> list);
}