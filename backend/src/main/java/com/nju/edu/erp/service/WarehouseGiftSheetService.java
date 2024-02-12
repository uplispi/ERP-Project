package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.WarehouseGiftSheetState;
import com.nju.edu.erp.model.vo.warehouse.WarehouseGiftSheetVO;

import java.util.List;

public interface WarehouseGiftSheetService {

    // 制定库存赠品单
    WarehouseGiftSheetVO create(WarehouseGiftSheetVO warehouseGiftSheetVO);

    // 审批库存赠品单
    void approval(String warehouseGiftSheetId, WarehouseGiftSheetState state);

    // 通过state返回赠品单
    List<WarehouseGiftSheetVO> findSheetByState(WarehouseGiftSheetState state);

}
