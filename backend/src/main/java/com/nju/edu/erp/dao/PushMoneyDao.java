package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.PushMoneyPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 10:18 2022/6/29
 * /*@Version:1.0
 */

@Repository
@Mapper
public interface PushMoneyDao {
    //根据员工id查询该员工的提成信息
    PushMoneyPO findById(Integer id);
    //查询所有员工的提成信息
    List<PushMoneyPO> findAll();
    //更新某一提成信息
    int update(PushMoneyPO pushMoneyPO);
    //创建新的提成信息表项
    int createPushMoney(PushMoneyPO pushMoneyPO);
    //根据员工id删除该员工的提成信息表项
    int deleteById(Integer id);
}
