package com.nju.edu.erp.dao;


import com.nju.edu.erp.model.po.EmployeePO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 22:27 2022/6/28
 * /*@Version:1.0
 */

@Repository
@Mapper
public interface EmployeeManagementDao {
    //添加员工
    int createEmployee(EmployeePO employeePO);
    //删除员工
    int deleteEmployeeById(Integer empId);
    //更新员工信息
    int updateEmployee(EmployeePO employeePO);
    //查询所有员工
    List<EmployeePO> findAllEmployees();
    //查询对应员工id的员工
    EmployeePO findEmployeeById(Integer empId);
    //查询对应岗位编号的所有员工
    List<EmployeePO> findEmployeeByPostId(Integer postId);
    //获取最近添加的员工id
    Integer getLatestId();
}
