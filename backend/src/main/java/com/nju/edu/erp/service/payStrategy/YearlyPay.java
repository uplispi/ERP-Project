package com.nju.edu.erp.service.payStrategy;

import com.nju.edu.erp.model.po.DetailPO;
import com.nju.edu.erp.model.po.PayrollPO;
import com.nju.edu.erp.model.vo.PayrollVO;

import java.math.BigDecimal;
import java.util.Date;

public class YearlyPay implements PayStrategy{
    private String[] boundaries = { "5000", "8000", "17000", "30000"};
    private String[] taxPercent = { "0.03", "0.1",  "0.2",   "0.25"};

    /**
     * 生成年薪应发工资
     *
     * @param detailPO
     * @return
     */
    @Override
    public BigDecimal payableWages(DetailPO detailPO) {
        Date now = new Date();
        if (now.getMonth() == 11){
            return detailPO.getBasic();
        }
        return null;
    }

    /**
     * 计算税收，住房公积金，失业保险，实发工资
     *
     * @param payrollVO
     * @return
     */
    @Override
    public PayrollVO countTax(PayrollVO payrollVO) {
        BigDecimal payable = payrollVO.getPayableWages();
        BigDecimal tax = new BigDecimal("0");
        payrollVO.setHousingProvidentFund(payable.multiply(new BigDecimal("0.01")));
        payrollVO.setUnemploymentInsurance(payable.multiply(new BigDecimal("0.01")));
        for (int i = 0; i < boundaries.length; i++) {
            if (payable.compareTo(new BigDecimal(boundaries[i]))<=0)break;
            BigDecimal upBound;
            if (i == boundaries.length-1)upBound = payable;
            else upBound = payable.compareTo(new BigDecimal(boundaries[i+1]))>=0?new BigDecimal(boundaries[i+1]):payable;
            tax = tax.add(upBound.subtract(new BigDecimal(boundaries[i])).multiply(new BigDecimal(taxPercent[i])));
        }
        payrollVO.setPersonalIncomeTax(tax);
        payrollVO.setActualAmountIssued(payable.subtract(payrollVO.getHousingProvidentFund()).subtract(payrollVO.getUnemploymentInsurance()).subtract(payrollVO.getPersonalIncomeTax()));
        return payrollVO;
    }


}
