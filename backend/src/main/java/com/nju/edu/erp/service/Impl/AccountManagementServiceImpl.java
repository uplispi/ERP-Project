package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AccountManagementDao;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountManagementServiceImpl implements AccountManagementService {

    private final AccountManagementDao accountManagementDao;

    @Autowired
    public AccountManagementServiceImpl(AccountManagementDao accountManagementDao) {
        this.accountManagementDao = accountManagementDao;
    }

    /**
     * 创建账户
     * @param accountVO
     * @return
     */
    @Override
    public AccountVO createAccount(AccountVO accountVO) {
        // 账户PO
        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(accountVO, accountPO);

        // 调用Dao存到数据库中
        accountManagementDao.createAccount(accountPO);
        return accountVO;
    }

    /**
     * 更新账户信息
     * @param accountVO
     */
    @Override
    public void updateAccount(AccountVO accountVO) {
        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(accountVO, accountPO);

        accountManagementDao.updateAccount(accountPO);
    }

    /**
     * 删除账户
     * @param id
     */
    @Override
    public void deleteAccount(Integer id) {
        accountManagementDao.deleteById(id);
    }

    /**
     * 返回所有账户
     * @return
     */
    @Override
    public List<AccountVO> find() {
        List<AccountPO> poList = accountManagementDao.find();
        // 将获取的POs转为VOs
        List<AccountVO> voList = new ArrayList<>();
        for(AccountPO po: poList){
            AccountVO vo = new AccountVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 通过id查询账户
     * @param id
     * @return
     */
    @Override
    public AccountVO getAccountById(Integer id){
        AccountVO accountVO = new AccountVO();
        AccountPO accountPO = accountManagementDao.findById(id);

        BeanUtils.copyProperties(accountPO, accountVO);

        return accountVO;
    }
}
