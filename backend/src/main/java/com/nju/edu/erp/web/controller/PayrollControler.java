package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SheetState;
import com.nju.edu.erp.model.vo.PayrollVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PayrollService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payroll")
public class PayrollControler {
    private PayrollService payrollService;

    @Autowired
    public PayrollControler(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    /**
     * 查询全部工资单
     * @return
     */
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.ADMIN,  Role.FINANCIAL_STAFF, Role.HR, Role.GM})
    public Response queryAll(){
        return Response.buildSuccess(payrollService.queryAll());
    }


    /**
     * 获取工资情况
     * @return
     */
    @GetMapping("/getDetail")
    @Authorized(roles = {Role.ADMIN,  Role.FINANCIAL_STAFF})
    public Response getDetail(){
        return Response.buildSuccess(payrollService.getDetail());
    }


    /**
     * 添加工资单
     * @param userVO
     * @param payrollVO
     * @return
     */
    @PostMapping("/add")
    @Authorized(roles = {Role.ADMIN,  Role.FINANCIAL_STAFF})
    public Response add(UserVO userVO, @RequestBody PayrollVO payrollVO){
        payrollService.createSheet(userVO ,payrollVO);
        return Response.buildSuccess();
    }

    /**
     * 更新工资单
     * @param userVO
     * @param payrollVO
     * @return
     */
    @PostMapping("/update")
    @Authorized(roles = {Role.ADMIN,  Role.FINANCIAL_STAFF, Role.HR, Role.GM})
    public Response update(UserVO userVO ,@RequestBody PayrollVO payrollVO){
        payrollService.updateSheet(userVO ,payrollVO);
        return Response.buildSuccess();
    }

    /**
     * 删除工资单，弃用
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Response deleteById(@RequestParam(value = "id") String id){
        payrollService.deleteSheet(id);
        return Response.buildSuccess();
    }


    @GetMapping("/approval")
    public Response approval(UserVO userVO, @RequestParam(value = "id") String id, SheetState state){
        payrollService.approval(userVO, id, state);
        return Response.buildSuccess();
    }


}
