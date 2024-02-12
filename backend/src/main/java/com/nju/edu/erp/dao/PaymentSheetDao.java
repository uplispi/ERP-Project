package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentSheetDao {
    /**
     * 获取最近一条收款单
     * @return
     */
    PaymentSheetPO getLatest();

    /**
     * 存入一条收款单记录
     * @param collectionSheetPO
     * @return
     */
    int save(PaymentSheetPO collectionSheetPO);

    /**
     * 把收款单上的转账列表写入数据库
     * @param collectionSheetContentPOList
     * @return
     */
    int saveContent(List<PaymentSheetContentPO> collectionSheetContentPOList);

    /**
     * 返回所有的收款单
     * @return
     */
    List<PaymentSheetPO> findAll();


    /**
     * 根据state返回收款单
     * @param state
     * @return
     */
    List<PaymentSheetPO> findAllByState(PaymentSheetState state);

    /**
     * 修改收款单状态
     * @param paymentId
     * @param state
     * @return
     */
    int updateState(String paymentId, PaymentSheetState state);

    /**
     * 通过id来返回收款单
     * @param paymentSheetId
     * @return
     */
    PaymentSheetPO findOneById(String paymentSheetId);

    List<PaymentSheetContentPO> findContentByPaymentSheetId(String collectionSheetId);

}
