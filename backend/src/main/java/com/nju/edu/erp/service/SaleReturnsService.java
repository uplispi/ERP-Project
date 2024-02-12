package com.nju.edu.erp.service;


import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface SaleReturnsService {
    /**
     * 制定进货退货单
     * @param saleReturnsSheetVO 进货退货单
     */
    void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO);

    /**
     * 根据状态获取进货退货单(state == null 则获取所有进货退货单)
     * @param state 进货退货单状态
     * @return 进货退货单
     */
    List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state);

    /**
     * 根据进货退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     * @param saleReturnsSheetId 进货退货单id
     * @param state 进货退货单修改后的状态
     */
    void approval(String saleReturnsSheetId, SaleReturnsSheetState state);


}
