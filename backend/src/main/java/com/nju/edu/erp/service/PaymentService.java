package com.nju.edu.erp.service;


import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.Payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PaymentService {

    /**
     * 制定收款单
     * @param userVO
     * @param paymentSheetVO
     */
    void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO);

    /**
     * 根据单据状态获取收款单
     * @param state
     * @return
     */
    List<PaymentSheetVO> getPaymentSheetByState(PaymentSheetState state);

    /**
     * 根据收款单id进行审批
     * 在controller层进行权限控制
     * @param paymentSheetId 收款单id
     * @param state 修改后的状态
     */
    void approval(String paymentSheetId, PaymentSheetState state);

    /**
     * 根据收款单id搜索收款单
     * @param paymentSheetId
     * @return
     */
    PaymentSheetVO getPaymentSheetById(String paymentSheetId);
}
