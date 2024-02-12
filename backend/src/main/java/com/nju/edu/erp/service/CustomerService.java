package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface CustomerService {

    // TODO 补充添加和编辑客户

    /**
     *  添加客户
     * @param userVO 操作员
     * @param customerVO 客户
     * @return
     */
    CustomerVO createCustomer(UserVO userVO, CustomerVO customerVO);

    /**
     * 删除该id对应的客户
     * @param cid
     * @return
     */
    void deleteCustomerById(Integer cid);

    /**
     * 根据id更新客户信息
     * @param customerPO 客户信息
     */
    void updateCustomer(CustomerPO customerPO);

    /**
     * 根据type查找对应类型的客户
     * @param type 客户类型
     * @return 客户列表
     */
    List<CustomerPO> getCustomersByType(CustomerType type);

    /**
     * 查询所有客户
     * @return
     */
    List<CustomerPO> getAllCustomers();
    /**
     * 体通过id查找用户
     * @param supplier
     * @return
     */
    CustomerPO findCustomerById(Integer supplier);





}
