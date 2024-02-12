package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.Payment.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.Payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountManagementService;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.PaymentService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    PaymentSheetDao paymentSheetDao;

    CustomerService customerService;

    AccountManagementService accountManagementService;

    @Autowired
    public PaymentServiceImpl(PaymentSheetDao paymentSheetDao, CustomerService customerService, AccountManagementService accountManagementService){
        this.paymentSheetDao = paymentSheetDao;
        this.customerService = customerService;
        this.accountManagementService = accountManagementService;
    }

    /**
     * 制定付款单
     * @param userVO
     * @param paymentSheetVO
     */
    @Override
    public void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO) {
        PaymentSheetPO paymentSheetPO = new PaymentSheetPO();
        BeanUtils.copyProperties(paymentSheetVO,paymentSheetPO);
        // 添加操作人员
        paymentSheetPO.setOperator(userVO.getName());
        // 生成id
        PaymentSheetPO latest = paymentSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null :latest.getId(), "FKD");
        paymentSheetPO.setId(id);
        // 设置状态
        paymentSheetPO.setState(PaymentSheetState.PENDING_LEVEL_1);
        // 设置时间
        paymentSheetPO.setCreateTime(new Date());

        // 计算总额：转账列表中每一条转账记录的转账金额加进去
        // 并且把每一个contentVO转化为contentVO写入数据库
        BigDecimal total = BigDecimal.ZERO;
        List<PaymentSheetContentPO> contentPOS = new ArrayList<>();

        for(PaymentSheetContentVO contentVO: paymentSheetVO.getPaymentSheetContentList()){
            PaymentSheetContentPO contentPO = new PaymentSheetContentPO();
            BeanUtils.copyProperties(contentVO, contentPO);
            // 设置该content对应的sheetId
            contentPO.setPaymentSheetId(id);

            BigDecimal eachTotal = contentPO.getTotal();
            total = total.add(eachTotal);
            contentPOS.add(contentPO);

            //减少该账户的余额 因为该账户把钱转给用户了
            Integer accountId = contentPO.getAccountId();
            AccountVO accountVO = accountManagementService.getAccountById(accountId);
            accountVO.setBalance(accountVO.getBalance().subtract(eachTotal));
            accountManagementService.updateAccount(accountVO);
        }
        paymentSheetPO.setTotal(total);

        // 写入数据库
        paymentSheetDao.saveContent(contentPOS);
        paymentSheetDao.save(paymentSheetPO);
    }

    @Override
    public List<PaymentSheetVO> getPaymentSheetByState(PaymentSheetState state) {
        List<PaymentSheetVO> res = new ArrayList<>();
        List<PaymentSheetPO> all = new ArrayList<>();
        if(state == null){
            // 状态为空返回全部
            all = paymentSheetDao.findAll();
        }else{
            all = paymentSheetDao.findAllByState(state);
        }

        // 深度拷贝
        for(PaymentSheetPO po: all) {
            PaymentSheetVO vo = new PaymentSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<PaymentSheetContentPO> alll = paymentSheetDao.findContentByPaymentSheetId(po.getId());
            List<PaymentSheetContentVO> vos = new ArrayList<>();

            for (PaymentSheetContentPO p : alll) {
                PaymentSheetContentVO v = new PaymentSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setPaymentSheetContentList(vos);
            res.add(vo);
        }
        return res;
    }

    @Override
    public void approval(String paymentSheetId, PaymentSheetState state) {
        if(state.equals(PaymentSheetState.FAILURE)){
            // 审批失败
            PaymentSheetPO paymentSheetPO = paymentSheetDao.findOneById(paymentSheetId);
            if(paymentSheetPO.getState() == PaymentSheetState.SUCCESS)
                // 收款单为本是审批成功的
                throw  new RuntimeException("状态更新失败");
            int effectLines = paymentSheetDao.updateState(paymentSheetId, state);
            if(effectLines == 0 ) throw new  RuntimeException("状态更新失败");
        }else{
            // 审批成功
            PaymentSheetPO paymentSheetPO = paymentSheetDao.findOneById(paymentSheetId);
            if(paymentSheetPO.getState() == PaymentSheetState.FAILURE)
                // 收款单本来是审批失败的
                throw  new RuntimeException("状态更新失败");
            int effectLines = paymentSheetDao.updateState(paymentSheetId, state);
            if(effectLines == 0 ) throw new  RuntimeException("状态更新失败");

            // 更新成功后
            // 减少客户的payable（公司还没还给客户钱）
            // 通过PaymentSheetVO中的customerId来修改客户的payable -= total
            Integer customerId = paymentSheetPO.getCustomer();
            BigDecimal total = paymentSheetPO.getTotal();
            CustomerPO customerPO = customerService.findCustomerById(customerId);
            customerPO.setPayable(customerPO.getPayable().subtract(total));
            customerService.updateCustomer(customerPO);
        }
    }

    @Override
    public PaymentSheetVO getPaymentSheetById(String paymentSheetId) {
        PaymentSheetPO paymentSheetPO = paymentSheetDao.findOneById(paymentSheetId);
        // 契约式编程
        if(paymentSheetPO==null)
            // 该id不存在
            throw new RuntimeException("该付款单id不存在");

        List<PaymentSheetContentPO> contentPOS = paymentSheetDao.findContentByPaymentSheetId(paymentSheetId);
        PaymentSheetVO res = new PaymentSheetVO();
        BeanUtils.copyProperties(paymentSheetPO, res);
        // 深度拷贝
        List<PaymentSheetContentVO> contentVOS = new ArrayList<>();
        for(PaymentSheetContentPO po : contentPOS){
            PaymentSheetContentVO vo =new PaymentSheetContentVO();
            BeanUtils.copyProperties(po, vo);
            contentVOS.add(vo);
        }

        res.setPaymentSheetContentList(contentVOS);

        return res;
    }
}
