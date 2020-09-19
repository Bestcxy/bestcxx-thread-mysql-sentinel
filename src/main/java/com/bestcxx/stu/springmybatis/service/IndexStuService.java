package com.bestcxx.stu.springmybatis.service;

import com.bestcxx.stu.springmybatis.model.IndexStu;

import java.util.List;

public interface IndexStuService {
    void insert(IndexStu indexStu);
    void insertList(List<IndexStu> list);
}
