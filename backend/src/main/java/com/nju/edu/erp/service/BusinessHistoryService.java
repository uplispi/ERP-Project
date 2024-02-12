package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Date;
import java.util.List;

public interface BusinessHistoryService {
    /**
     * 查询销售单
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

    /**
     * 导出销售单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    SXSSFWorkbook downloadSaleSheet(Date beginDate, Date endDate, Integer customer, String salesMan);

    /**
     * 导出销售退货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    SXSSFWorkbook downloadSaleReturnsSheet(Date beginDate, Date endDate, Integer customer, String salesMan);

    /**
     * 导出进货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @return
     */
    SXSSFWorkbook downloadPurchaseSheet(Date beginDate, Date endDate, Integer customer);

    /**
     * 导出销售退货单
     * @param beginDate
     * @param endDate
     * @return
     */
    SXSSFWorkbook downloadPurchaseReturnsSheet(Date beginDate, Date endDate);

    /**
     * 导出付款单
     * @param beginDate
     * @param endDate
     * @return
     */
    SXSSFWorkbook downloadPaymentSheet(Date beginDate, Date endDate);

    /**
     * 导出收款单
     * @param beginDate
     * @param endDate
     * @return
     */
    SXSSFWorkbook downloadCollectionSheet(Date beginDate, Date endDate);

    /**
     * 导出工资单
     * @param beginDate
     * @param endDate
     * @return
     */
    SXSSFWorkbook downloadPayroll(Date beginDate, Date endDate);
}
