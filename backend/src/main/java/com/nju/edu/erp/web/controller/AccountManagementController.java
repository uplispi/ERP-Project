package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountManagementService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountManagementController {
    private AccountManagementService accountManagementService;

    @Autowired
    public AccountManagementController(AccountManagementService accountManagementService){this.accountManagementService = accountManagementService; }

    /**
     * 创建账户
     * @param userVO 操作员
     * @param accountVO 账户信息
     * @return
     */
    @Authorized (roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping("/create")
    public Response createAccount(UserVO userVO,@RequestBody AccountVO accountVO){
        System.out.println("创建账户");
        return Response.buildSuccess(accountManagementService.createAccount(accountVO));
    }

    /**
     * 更新账户信息
     * @param accountVO
     * @return
     */
    @PostMapping("/update")
    public Response updateAccount(@RequestBody AccountVO accountVO){
        accountManagementService.updateAccount(accountVO);
        return Response.buildSuccess();
    }

    /**
     * 删除账户
     * @param id 要删除的账户的id
     * @return
     */
    @DeleteMapping("/delete")
    public Response deleteById(@RequestParam(value = "id") int id){
        accountManagementService.deleteAccount(id);
        return Response.buildSuccess();
    }

    /**
     * 获取所有账户
     * @return
     */
    @GetMapping("/show")
    public Response find(){
        return Response.buildSuccess(accountManagementService.find());
    }


}
