package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface AccountManagementService {

    /**
     * 添加账户
     * @param accountVO
     * @return
     */
    AccountVO createAccount(AccountVO accountVO);

    /**
     * 修改账户
     * @param accountVO
     * @return
     */
    void updateAccount(AccountVO accountVO);

    /**
     * 删除账户
     * @param id
     */
    void deleteAccount(Integer id);

    /**
     * 查询账户
     * @return
     */
    List<AccountVO> find();

    /**
     * 获取账户
     * @param id
     * @return
     */
    AccountVO getAccountById(Integer id);
}
