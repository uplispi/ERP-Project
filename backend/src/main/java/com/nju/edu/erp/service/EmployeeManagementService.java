package com.nju.edu.erp.service;


import com.nju.edu.erp.model.vo.EmployeeVO;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 22:25 2022/6/28
 * /*@Version:1.0
 */
public interface EmployeeManagementService {
    /**
     * 添加员工
     * @param employeeVO 员工
     * @return
     */
    void createEmployee(EmployeeVO employeeVO);

    /**
     * 删除对应员工id的员工
     * @param empId
     */
    void deleteEmployee(Integer empId);

    /**
     * 更新员工信息
     * @param employeeVO
     */
    EmployeeVO updateEmployee(EmployeeVO employeeVO);

    /**
     * 查询所有员工
     * @return
     */
    List<EmployeeVO> getAllEmployees();

    /**
     * 查询对应员工id的员工
     * @param empId
     * @return
     */
    EmployeeVO findEmployeeById(Integer empId);
}
