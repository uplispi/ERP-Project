package com.nju.edu.erp.service.payStrategy;

import com.nju.edu.erp.model.po.DetailPO;
import com.nju.edu.erp.model.po.PayrollPO;
import com.nju.edu.erp.model.vo.PayrollVO;

import java.math.BigDecimal;

public interface PayStrategy {
    /**
     * 生成应的工资
     * @param detailPO
     * @return
     */
    BigDecimal payableWages(DetailPO detailPO);

    /**
     * 计算税收，住房公积金，失业保险，实发工资
     * @param payrollVO
     * @return
     */
    PayrollVO countTax(PayrollVO payrollVO);
}
