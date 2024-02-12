package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface BusinessHistoryDao {
    /**
     * 查询销售 单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    List<SaleSheetPO> querySaleSheet(Date beginDate, Date endDate, Integer customer, String salesMan);

    /**
     * 查询销售退货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    List<SaleReturnsSheetPO> querySaleReturnsSheet(Date beginDate, Date endDate, Integer customer, String salesMan);

    /**
     * 查询进货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @return
     */
    List<PurchaseSheetPO> queryPurchaseSheet(Date beginDate, Date endDate, Integer customer);

    /**
     * 查询进货退货单
     * @param beginDate
     * @param endDate
     * @return
     */
    List<PurchaseReturnsSheetPO> queryPurchaseReturnsSheet(Date beginDate, Date endDate);

    /**
     * 查询付款单
     * @param beginDate
     * @param endDate
     * @return
     */
    List<PaymentSheetPO> queryPaymentSheet(Date beginDate, Date endDate);

    /**
     * 查询收款单
     * @param beginDate
     * @param endDate
     * @return
     */
    List<CollectionSheetPO> queryCollectionSheet(Date beginDate, Date endDate);

    /**
     * 查询工资单
     * @param beginDate
     * @param endDate
     * @return
     */
    List<PayrollPO> queryPayroll(Date beginDate, Date endDate);
}
