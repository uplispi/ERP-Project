package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.AccountPO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountManagementDao {

    // 创建账户
    int createAccount(AccountPO accountPO);

    // 修改账户
    int updateAccount(AccountPO accountPO);

    // 删除账户
    int deleteById(Integer id);

    // 查询账户
    List<AccountPO> find();

    // 通过id查询账户
    AccountPO findById(Integer id);

}
