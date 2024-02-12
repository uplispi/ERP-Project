package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeManagementDao;
import com.nju.edu.erp.dao.PayrollDao;

import com.nju.edu.erp.enums.sheetState.SheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.PayrollVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountManagementService;
import com.nju.edu.erp.service.PayrollService;
import com.nju.edu.erp.service.payStrategy.PayStrategy;
import com.nju.edu.erp.service.payStrategy.PayableFactory;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class PayrollServiceImpl implements PayrollService {
    private final PayrollDao payrollDao;
    private final AccountManagementService accountManagementService;

    @Autowired
    public PayrollServiceImpl(PayrollDao payrollDao, EmployeeManagementDao employeeManagementDao, AccountManagementService accountManagementService){
        this.payrollDao = payrollDao;
        this.accountManagementService = accountManagementService;
    }

    /**
     * 查询所有工资单
     * @return
     */
    @Override
    public List<PayrollVO> queryAll() {
        List<PayrollPO> payrollPOList =  payrollDao.queryAll();
        ArrayList<PayrollVO> payrollVOArrayList = new ArrayList<>();
        for (PayrollPO payrollPO : payrollPOList) {
            PayrollVO payrollVO = new PayrollVO();
            BeanUtils.copyProperties(payrollPO, payrollVO);
            payrollVOArrayList.add(payrollVO);
        }
        return payrollVOArrayList;
    }


    /**
     * 生成工资单详情
     * @return
     */
    @Override
    public List<PayrollVO> getDetail() {

        //获取本月第一天和最后一天
        Date now =  new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        Integer year = now.getYear();
        Integer month = now.getMonth();
        Date beginDate = new Date(year, month, 1,0,0,0);
        Date endDate = new Date(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59);

        //获取生成工资单需要的细节
        List<DetailPO> detailPOS = payrollDao.getDetail(year, beginDate, endDate);


        //为每个员工计算工资
        ArrayList<PayrollVO> payrollVOArrayList = new ArrayList<>();
        for (DetailPO detailPO : detailPOS) {
            //可能没有打卡
            //防御式编程
            if(detailPO.getTimes() == null) detailPO.setTimes(0);

            //设置基本信息
            PayrollVO payrollVO = new PayrollVO();
            payrollVO.setTimes(detailPO.getTimes());
            payrollVO.setName(detailPO.getName());
            payrollVO.setEmployeeId(detailPO.getId());
            payrollVO.setBankCard(detailPO.getBankCard());

            //使用简单工厂的策略模式
            PayStrategy payStrategy = PayableFactory.getPayable(detailPO.getType());
            if (payStrategy == null)continue;

            //计算应发工资
            BigDecimal payable = payStrategy.payableWages(detailPO);
            //不符合发工资的条件
            if (payable == null)continue;

            //计算税收等
            payrollVO.setPayableWages(payStrategy.payableWages(detailPO));
            payrollVO = payStrategy.countTax(payrollVO);

            //计算年终奖，约定12月发年终奖
            if (month == 11){
                payrollVO.setYearEndBonus(detailPO.getBonus());
                payrollVO.setActualAmountIssued(payrollVO.getActualAmountIssued().add(detailPO.getBonus()));
            }
            payrollVOArrayList.add(payrollVO);
        }



        return payrollVOArrayList;
    }

    /**
     * 审批单据
     * @param user
     * @param id
     * @param state
     */
    public void approval(UserVO user, String id, SheetState state) {
        //更新状态
        PayrollPO payrollPO = payrollDao.getSheetById(id);

        //契约式编程
        if (state  == SheetState.PENDING_LEVEL_2 && payrollPO.getState() != SheetState.PENDING_LEVEL_1){
            throw new RuntimeException("只能从待一级审批更新到待二级审批");
        }
        if (state == SheetState.SUCCESS && payrollPO.getState() != SheetState.PENDING_LEVEL_2) {
            throw new RuntimeException("只能从待二级审批转台更新到审批完成状态");
        }

        payrollPO.setOperator(user.getName());
        payrollPO.setState(state);
        payrollDao.update(payrollPO);

        //审批完成后修改账户余额
        if (state == SheetState.SUCCESS){
            updateAccount(id);
        }
    }

    /**
     * 更新账户余额
     * @param id
     */
    private void updateAccount(String id){
        PayrollPO payrollPO1 =  payrollDao.getSheetById(id);
        AccountVO accountVO = accountManagementService.getAccountById(1);
        accountVO.setBalance(accountVO.getBalance().subtract(payrollPO1.getActualAmountIssued()));
        accountManagementService.updateAccount(accountVO);
    }

    /**
     * 添加工资单
     * @param userVO
     * @param payrollVO
     */
    @Override
    public void createSheet(UserVO userVO, PayrollVO payrollVO) {
        payrollVO.setState(SheetState.PENDING_LEVEL_1);
        payrollVO.setOperator(userVO.getName());
        payrollVO.setCreateTime(new Date());
        PayrollPO lastest =  payrollDao.getLatest();
        payrollVO.setId(IdGenerator.generateSheetId(lastest == null?null: lastest.getId(),"GZD"));
        System.out.println(payrollVO);
        PayrollPO payrollPO = new PayrollPO();
        BeanUtils.copyProperties(payrollVO, payrollPO);
        payrollDao.creaeSheet(payrollPO);
    }

    /**
     * 更新工资单
     * @param userVO
     * @param payrollVO
     */
    @Override
    public void updateSheet(UserVO userVO, PayrollVO payrollVO) {
        payrollVO.setOperator(userVO.getName());
        payrollVO.setOperator(userVO.getName());
        PayrollPO payrollPO = new PayrollPO();

        //审批完成
        if(payrollVO.getState() == SheetState.SUCCESS){
           PayrollPO payrollPO1 =  payrollDao.getSheetById(payrollVO.getId());
            AccountVO accountVO = accountManagementService.getAccountById(1);
            accountVO.setBalance(accountVO.getBalance().subtract(payrollPO1.getActualAmountIssued()));
            accountManagementService.updateAccount(accountVO);
        }

        BeanUtils.copyProperties(payrollVO, payrollPO);
        payrollDao.update(payrollPO);
    }


    /**
     * 根据id删除工资单
     * @param id
     */
    @Override
    public void deleteSheet(String id) {

        //契约式编程
        if (id == null){
            throw new RuntimeException("该工资当id不存在");
        }
        payrollDao.deleteSheetByid(id);
    }



}
