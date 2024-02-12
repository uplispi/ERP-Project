package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.enums.sheetState.WarehouseGiftSheetState;
import com.nju.edu.erp.service.WarehouseGiftSheetService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/warehouseGift")
public class WarehouseGiftSheetController {

    WarehouseGiftSheetService warehouseGiftSheetService;

    @Autowired
    public WarehouseGiftSheetController(WarehouseGiftSheetService warehouseGiftSheetService){
        this.warehouseGiftSheetService = warehouseGiftSheetService;
    }

    /**
     * 通过状态返回库存赠品单
     * @param state 要查询的单据状态，非必须
     * @return
     */
    @GetMapping("/getSheetsByState")
    public Response getSheetsByState(@RequestParam(value = "state", required = false) WarehouseGiftSheetState state){
        return Response.buildSuccess(warehouseGiftSheetService.findSheetByState(state));
    }

    /**.
     * 审批单据
     * @param warehouseGiftSheetId
     * @param state 审批后单据的状态
     * @return
     */
    @GetMapping("/approval")
    public Response approval(@RequestParam("warehouseGiftSheetId") String warehouseGiftSheetId,
                             @RequestParam("state") WarehouseGiftSheetState state){

        // 防御式编程
        if(state.equals(WarehouseGiftSheetState.SUCCESS) || state.equals(WarehouseGiftSheetState.FAILURE)) {
            warehouseGiftSheetService.approval(warehouseGiftSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败");
        }
    }

}
