package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CollectionSheetDao;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetContentPO;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.Collection.CollectionSheetContentVO;
import com.nju.edu.erp.model.vo.Collection.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountManagementService;
import com.nju.edu.erp.service.CollectionService;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.utils.IdGenerator;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    CollectionSheetDao collectionSheetDao;

    CustomerService customerService;

    AccountManagementService accountManagementService;

    @Autowired
    public CollectionServiceImpl(CollectionSheetDao collectionSheetDao, CustomerService customerService, AccountManagementService accountManagementService){
        this.collectionSheetDao = collectionSheetDao;
        this.customerService = customerService;
        this.accountManagementService = accountManagementService;
    }

    /**
     * 制定收款单
     * @param userVO
     * @param collectionSheetVO
     */
    @Override
    public void makeCollectionSheet(UserVO userVO, CollectionSheetVO collectionSheetVO) {
        CollectionSheetPO collectionSheetPO = new CollectionSheetPO();
        BeanUtils.copyProperties(collectionSheetVO, collectionSheetPO);
        // 添加操作员
        collectionSheetPO.setOperator(userVO.getName());

        // 生成id
        CollectionSheetPO latest = collectionSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null :latest.getId(), "SKD");
        collectionSheetPO.setId(id);

        // 设置状态
        collectionSheetPO.setState(CollectionSheetState.PENDING_LEVEL_1);

        // 设置时间
        collectionSheetPO.setCreateTime(new Date());

        // 计算总额： 转账列表中每一条转账记录的转账金额加进去
        // 并且把每一个contentVO转化为contentVO写入数据库
        BigDecimal total = BigDecimal.ZERO;
        List<CollectionSheetContentPO> contentPoList = new ArrayList<>();

        for(CollectionSheetContentVO contentVO: collectionSheetVO.getCollectionSheetContentList()){
            CollectionSheetContentPO contentPO = new CollectionSheetContentPO();
            BeanUtils.copyProperties(contentVO, contentPO);
            // 设置该content对应的sheetId
            contentPO.setCollectionSheetId(id);
            // 此转账记录的总额
            BigDecimal eachTotal = contentPO.getTotal();
            // 加入该收款单中
            total = total.add(eachTotal);
            contentPoList.add(contentPO);

            // 增加该账户的钱（因为客户把钱转到该账户中）
            Integer accountId = contentPO.getAccountId();
            AccountVO accountVO = accountManagementService.getAccountById(accountId);
            accountVO.setBalance(accountVO.getBalance().add(eachTotal));
            accountManagementService.updateAccount(accountVO);
        }
        // 设置总额
        collectionSheetPO.setTotal(total);
        // 存到数据库中
        collectionSheetDao.saveContent(contentPoList);
        collectionSheetDao.save(collectionSheetPO);

    }

    /**
     * 根据单据状态获取收款单
     * @param state （state为空返回全部）
     * @return
     */
    @Override
    public List<CollectionSheetVO> getCollectionSheetByState(CollectionSheetState state) {
        List<CollectionSheetVO> res = new ArrayList<>();
        List<CollectionSheetPO> all = new ArrayList<>();
        if(state == null){
            // 状态为空返回全部
            all = collectionSheetDao.findAll();
        }else{
            // 否则通过state查询
            all = collectionSheetDao.findAllByState(state);
        }

        // 深度拷贝Contents
        for(CollectionSheetPO po: all) {
            CollectionSheetVO vo = new CollectionSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<CollectionSheetContentPO> alll = collectionSheetDao.findContentByCollectionSheetId(po.getId());
            List<CollectionSheetContentVO> vos = new ArrayList<>();

            for (CollectionSheetContentPO p : alll) {
                CollectionSheetContentVO v = new CollectionSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setCollectionSheetContentList(vos);
            res.add(vo);
        }
        return res;
    }


    /**
     * 根据收款单id进行审批
     * @param collectionSheetId 收款单id
     * @param state 修改后的状态 (state == "审批成功/审批失败")
     */
    @Override
    public void approval(String collectionSheetId, CollectionSheetState state) {
        if(state.equals(CollectionSheetState.FAILURE)){
            // 审批失败
            CollectionSheetPO collectionSheetPO = collectionSheetDao.findOneById(collectionSheetId);
            if(collectionSheetPO.getState() == CollectionSheetState.SUCCESS)
                // 收款单为本是审批成功的
                throw  new RuntimeException("状态更新失败");
            int effectLines = collectionSheetDao.updateState(collectionSheetId, state);
            if(effectLines == 0 ) throw new  RuntimeException("状态更新失败");
        }else{
            // 审批成功
            CollectionSheetPO collectionSheetPO = collectionSheetDao.findOneById(collectionSheetId);
            if(collectionSheetPO.getState() == CollectionSheetState.FAILURE)
                // 收款单本来是审批失败的
                throw  new RuntimeException("状态更新失败");
            int effectLines = collectionSheetDao.updateState(collectionSheetId, state);
            if(effectLines == 0 ) throw new  RuntimeException("状态更新失败");

            // 更新成功后 修改客户的receivable (客户还没付给公司的钱)
                // 通过CollectionSheetVO中的customerId来修改客户的receivalbe -= total
            Integer customerId = collectionSheetPO.getCustomer();
            BigDecimal total = collectionSheetPO.getTotal();
            CustomerPO customerPO = customerService.findCustomerById(customerId);
            customerPO.setReceivable(customerPO.getReceivable().subtract(total));
            customerService.updateCustomer(customerPO);
        }
    }

    /**
     * 根据收款单id搜索收款单
     * @param collectionSheetId
     * @return
     */
    @Override
    public CollectionSheetVO getCollectionSheetById(String collectionSheetId) {
        CollectionSheetPO collectionSheetPO = collectionSheetDao.findOneById(collectionSheetId);

        // 契约式编程
        if(collectionSheetPO==null)
            // 该id不存在
            throw new RuntimeException("该收款单id不存在");

        List<CollectionSheetContentPO> contentPOS = collectionSheetDao.findContentByCollectionSheetId(collectionSheetId);
        CollectionSheetVO res = new CollectionSheetVO();
        BeanUtils.copyProperties(collectionSheetPO, res);

        // 深度拷贝Contents
        List<CollectionSheetContentVO> contentVOS = new ArrayList<>();
        for(CollectionSheetContentPO po : contentPOS){
            CollectionSheetContentVO vo =new CollectionSheetContentVO();
            BeanUtils.copyProperties(po, vo);
            contentVOS.add(vo);
        }

        res.setCollectionSheetContentList(contentVOS);

        return res;
    }
}
