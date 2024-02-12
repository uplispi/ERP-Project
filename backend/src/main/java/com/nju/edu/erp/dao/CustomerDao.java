package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerDao {

    // 添加用户
    int createCustomer(CustomerPO customerPO);

    // 更新用户
    int updateOne(CustomerPO customerPO);

    // 删除客户
    int deleteById(Integer id);

    // 通过id查询客户
    CustomerPO findOneById(Integer supplier);

    // 通过类型(type)查询客户
    List<CustomerPO> findAllByType(CustomerType customerType);

    // 查询所有客户
    List<CustomerPO> findAll();
}
