package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CreateProductVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findByType")
    public Response findByType(@RequestParam CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

    @GetMapping("/queryAll")
    public Response findAll(){
        return  Response.buildSuccess(customerService.getAllCustomers());
    }


    /**
     * TODO 添加客户
     * @param userVO
     * @param customerVO
     * @return
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER})
    @PostMapping(value="/create")
    public Response createCustomer(UserVO userVO, @RequestBody CustomerVO customerVO){
        customerService.createCustomer(userVO, customerVO);

        return Response.buildSuccess();
    }

    /**
     * TODO
     * 修改客户信息
     * @param customerPO
     * @return
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER})
    @PostMapping("/update")
    public Response updateCustomer(@RequestBody CustomerPO customerPO){
        customerService.updateCustomer(customerPO);
        return Response.buildSuccess();
    }

    /**
     * TODO 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Response deleteCustomer(@RequestParam(value = "id") int id){
        customerService.deleteCustomerById(id);
        return Response.buildSuccess();
    }



}
