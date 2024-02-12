package com.nju.edu.erp.service.Impl;


import com.nju.edu.erp.dao.EmployeeManagementDao;
import com.nju.edu.erp.dao.PostDao;
import com.nju.edu.erp.enums.PostType;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.vo.EmployeeVO;

import com.nju.edu.erp.model.vo.PushMoneyVO;
import com.nju.edu.erp.service.EmployeeManagementService;
import com.nju.edu.erp.service.PostService;
import com.nju.edu.erp.service.PushMoneyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 22:26 2022/6/28
 * /*@Version:1.0
 */

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    private final EmployeeManagementDao employeeManagementDao;
    private final PostService postService;
    private final PushMoneyService pushMoneyService;

    @Autowired
    public EmployeeManagementServiceImpl(EmployeeManagementDao employeeManagementDao, PostService postService, PushMoneyService pushMoneyService) {
        this.employeeManagementDao = employeeManagementDao;
        this.postService = postService;
        this.pushMoneyService = pushMoneyService;
    }


    /**
     * 创建一个员工
     * @param employeeVO 员工
     */
    @Override
    public void createEmployee(EmployeeVO employeeVO) {
        String postName = postService.getNameById(employeeVO.getPostId());
        employeeVO.setPostName(postName);
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        employeeManagementDao.createEmployee(employeePO);
        //根据薪资类型判断是否需要创建PushMoney表项
        if(postService.getPostTypeById(employeeVO.getPostId()) == PostType.MONTHLY_WITH_PUSH ||
                postService.getPostTypeById(employeeVO.getPostId()) == PostType.YEARLY_WITH_PUSH){
            PushMoneyVO pushMoneyVO = PushMoneyVO.builder()
                    .empId(employeeManagementDao.getLatestId())
                    .pushMoney(new BigDecimal("0"))
                    .build();
            pushMoneyService.createPushMoney(pushMoneyVO);
        }
    }

    /**
     * 根据员工id删除员工
     * @param empId 员工id
     */
    @Override
    public void deleteEmployee(Integer empId) {
        EmployeePO employeePO = employeeManagementDao.findEmployeeById(empId);
        if(employeePO == null)
            throw new MyServiceException("B0004", "删除失败！");

        //根据薪资类型判断是否需要级联删除提成信息
        boolean needDelete = false;
        if(postService.getPostTypeById(employeePO.getPostId()) == PostType.MONTHLY_WITH_PUSH ||
                postService.getPostTypeById(employeePO.getPostId()) == PostType.YEARLY_WITH_PUSH){
            needDelete = true;
        }

        // 删除员工
        int ans = employeeManagementDao.deleteEmployeeById(empId);
        if (ans == 0) {
            throw new MyServiceException("B0003", "删除失败！");
        }

        //删除提成信息
        if(needDelete){
            pushMoneyService.deletePushMoneyById(empId);
        }
    }

    /**
     * 更新员工信息
     * @param employeeVO
     * @return
     */
    @Override
    public EmployeeVO updateEmployee(EmployeeVO employeeVO) {
        String postName = postService.getNameById(employeeVO.getPostId());
        employeeVO.setPostName(postName);
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        int ans = employeeManagementDao.updateEmployee(employeePO);
        if(ans == 0){
            throw new MyServiceException("B0003", "更新失败！");
        }
        employeePO = employeeManagementDao.findEmployeeById(employeePO.getId());
        EmployeeVO res = new EmployeeVO();
        BeanUtils.copyProperties(employeePO, res);
        return res;
    }

    /**
     * 获取所有员工信息
     * @return
     */
    @Override
    public List<EmployeeVO> getAllEmployees() {
        List<EmployeePO> queryAns = employeeManagementDao.findAllEmployees();
        List<EmployeeVO> responseVO = queryAns.stream().map(employeePO -> {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employeePO, employeeVO);
            return  employeeVO;
        }).collect(Collectors.toList());
        return responseVO;
    }

    /**
     * 根据员工id查询员工
     * @param empId 员工id
     * @return
     */
    @Override
    public EmployeeVO findEmployeeById(Integer empId) {
        EmployeePO employeePO = employeeManagementDao.findEmployeeById(empId);
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employeePO, employeeVO);
        return employeeVO;
    }
}
