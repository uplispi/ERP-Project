package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    // TODO 添加一个用户
    @Override
    public CustomerVO createCustomer(UserVO userVO, CustomerVO customerVO) {
        // 将VO转换为PO CustomerVO和CustomerPO的属性都是一样的
        CustomerPO customerPO = new CustomerPO();
        BeanUtils.copyProperties(customerVO, customerPO);


        // BUG:有的字段没填写好 看前端怎么处理
            // 添加操作员
        System.out.println(userVO.getName());
        customerPO.setOperator(userVO.getName());

        // 写进数据库
        customerDao.createCustomer(customerPO);

        return customerVO;
    }


    // TODO 删除一个用户
    @Override
    public void deleteCustomerById(Integer cid) {
        // 先通过id查找该客户
        CustomerPO customerPO = customerDao.findOneById(cid);
        if(customerPO == null)
            throw new MyServiceException("B0004", "删除失败！");

        // 删除顾客
        int ans = customerDao.deleteById(cid);
        if (ans == 0) {
            throw new MyServiceException("B0004", "删除失败！");
        }

        // 问题：是否要级联删除该客户对应的单据
    }

    /**
     * 根据id更新客户信息
     *
     * @param customerPO 客户信息
     */
    @Override
    public void updateCustomer(CustomerPO customerPO) {
        customerDao.updateOne(customerPO);
    }

    /**
     * 根据type查找对应类型的客户
     *
     * @param type 客户类型
     * @return 客户列表
     */
    @Override
    public List<CustomerPO> getCustomersByType(CustomerType type) {

        return customerDao.findAllByType(type);
    }

    /**
     * 查询所有客户
     * @return
     */
    public List<CustomerPO> getAllCustomers(){
        return customerDao.findAll();
    }

    /**
     * 通过id查找客户
     * @param supplier
     * @return 客户
     */
    @Override
    public CustomerPO findCustomerById(Integer supplier) {
        return customerDao.findOneById(supplier);
    }


}
