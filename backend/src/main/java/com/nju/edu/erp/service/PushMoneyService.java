package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.PushMoneyVO;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 10:11 2022/6/29
 * /*@Version:1.0
 */
public interface PushMoneyService {
    /**
     * 根据id查找员工薪资信息
     * @param id
     * @return
     */
    PushMoneyVO findPushMoneyById(Integer id);

    /**
     * 查看所有员工薪资信息
     * @return
     */
    List<PushMoneyVO> findAllPushMoney();

    /**
     * 更新员工薪资信息
     * @param pushMoneyVO
     */
    PushMoneyVO updatePushMoney(PushMoneyVO pushMoneyVO);

    /**
     * 创建新的员工薪资信息
     * @param pushMoneyVO
     * @return
     */
    PushMoneyVO createPushMoney(PushMoneyVO pushMoneyVO);

    /**
     * 删除员工薪资信息
     * @param id
     */
    void deletePushMoneyById(Integer id);
}
