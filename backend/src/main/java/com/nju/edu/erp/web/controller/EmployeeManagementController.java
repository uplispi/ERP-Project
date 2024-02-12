package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.EmployeeVO;
import com.nju.edu.erp.service.EmployeeManagementService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 22:15 2022/6/28
 * /*@Version:1.0
 */

@RestController
@RequestMapping(path = "/employee")
public class EmployeeManagementController {
    private EmployeeManagementService employeeManagementService;

    @Autowired
    public EmployeeManagementController(EmployeeManagementService employeeManagementService){this.employeeManagementService = employeeManagementService;}

    /**
     * 创建一个新的员工
     * @param employeeVO 新的员工信息
     * @return
     */
    @PostMapping("/create")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response createEmployee(@RequestBody EmployeeVO employeeVO) {
        employeeManagementService.createEmployee(employeeVO);
        return Response.buildSuccess();
    }

    /**
     * 更新某一员工的信息
     * @param employeeVO
     * @return
     */
    @PostMapping("/update")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response updateEmployee(@RequestBody EmployeeVO employeeVO) {
        return Response.buildSuccess(employeeManagementService.updateEmployee(employeeVO));
    }

    /**
     * 根据员工id删除某一员工
     * @param id 员工id
     * @return
     */
    @DeleteMapping("/delete")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response deleteEmployee(@RequestParam Integer id){
        employeeManagementService.deleteEmployee(id);
        return Response.buildSuccess();
    }

    /**
     * 查询所有员工的信息
     * @return
     */
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response queryAllEmployee(){
        return Response.buildSuccess(employeeManagementService.getAllEmployees());
    }

    /**
     * 根据员工id查询指定员工的信息
     * @param id 员工id
     * @return
     */
    @GetMapping("/show")
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN})
    public Response find(@RequestParam Integer id){
        return Response.buildSuccess(employeeManagementService.findEmployeeById(id));
    }

}





