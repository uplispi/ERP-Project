package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.Payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import com.nju.edu.erp.service.PaymentService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){this.paymentService = paymentService; }

    /**
     * 制定收款单
     * @param userVO
     * @param paymentSheetVO
     * @return
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/create")
    public Response makePaymentSheet(UserVO userVO, @RequestBody PaymentSheetVO paymentSheetVO){
        System.out.println("请求");
        paymentService.makePaymentSheet(userVO, paymentSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 审批收款单
     * @param paymentSheetId
     * @param state 修改后的状态("审批失败/审批完成")
     * @return
     */
    @Authorized(roles = {Role.GM,  Role.ADMIN})
    @GetMapping(value = "/approval")
    public Response approval(@RequestParam("paymentSheetId") String paymentSheetId,
                             @RequestParam("state") PaymentSheetState state) {

        // 防御式编程: 如果state不是FAILURE或者SUCCESS 则不予审批
        if(state.equals(PaymentSheetState.FAILURE) || state.equals(PaymentSheetState.SUCCESS)){
            paymentService.approval(paymentSheetId, state);
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("000000","操作失败");
        }
    }

    /**
     * 根据状态查看收款单
     * @param state 为非必须的参数
     * @return
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) PaymentSheetState state){
        return Response.buildSuccess(paymentService.getPaymentSheetByState(state));
    }

    /**
     * 通过id查询收款单
     * @param id
     * @return
     */
    @GetMapping(value = "find-sheet")
    public Response findBySheetId(@RequestParam(value = "id") String id){
        return Response.buildSuccess(paymentService.getPaymentSheetById(id));
    }
}
