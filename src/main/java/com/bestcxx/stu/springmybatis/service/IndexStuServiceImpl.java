package com.bestcxx.stu.springmybatis.service;

import com.bestcxx.stu.springmybatis.dao.IndexStuExtMapper;
import com.bestcxx.stu.springmybatis.dao.IndexStuMapper;
import com.bestcxx.stu.springmybatis.model.IndexStu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class IndexStuServiceImpl implements IndexStuService {

    @Autowired
    private IndexStuMapper indexStuMapper;
    @Autowired
    private IndexStuExtMapper indexStuExtMapper;


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public void insert(IndexStu indexStu) {
        indexStuMapper.insert(indexStu);
        if(indexStu.getStatus() == 1){
            int num = indexStu.getStatus()/0;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public void insertList(List<IndexStu> list) {
        indexStuExtMapper.insertList(list);
    }
}
