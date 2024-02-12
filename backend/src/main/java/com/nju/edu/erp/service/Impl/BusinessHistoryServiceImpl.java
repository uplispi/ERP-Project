package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BusinessHistoryDao;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.service.BusinessHistoryService;
import com.nju.edu.erp.utils.ExcelGenerator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class BusinessHistoryServiceImpl implements BusinessHistoryService {
    private final BusinessHistoryDao businessHistoryDao;


    @Autowired
    public BusinessHistoryServiceImpl(BusinessHistoryDao businessHistoryDao) {
        this.businessHistoryDao = businessHistoryDao;
    }

    /**
     * 查询销售单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    @Override
    public List<SaleSheetPO> querySaleSheet(Date beginDate, Date endDate, Integer customer, String salesMan) {
        return businessHistoryDao.querySaleSheet(beginDate, endDate, customer, salesMan);
    }

    /**
     * 查询销售退货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    @Override
    public List<SaleReturnsSheetPO> querySaleReturnsSheet(Date beginDate, Date endDate, Integer customer, String salesMan) {
        return businessHistoryDao.querySaleReturnsSheet(beginDate, endDate, customer, salesMan);
    }

    /**
     * 查询进货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @return
     */
    @Override
    public List<PurchaseSheetPO> queryPurchaseSheet(Date beginDate, Date endDate, Integer customer) {
        return businessHistoryDao.queryPurchaseSheet(beginDate, endDate, customer);
    }

    /**
     * 查询进货退货单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public List<PurchaseReturnsSheetPO> queryPurchaseReturnsSheet(Date beginDate, Date endDate) {
        return businessHistoryDao.queryPurchaseReturnsSheet(beginDate, endDate);
    }

    /**
     * 查询付款单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public List<PaymentSheetPO> queryPaymentSheet(Date beginDate, Date endDate) {
        return businessHistoryDao.queryPaymentSheet(beginDate, endDate);
    }

    /**
     * 查询收款单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public List<CollectionSheetPO> queryCollectionSheet(Date beginDate, Date endDate) {
        return businessHistoryDao.queryCollectionSheet(beginDate, endDate);
    }

    /**
     * 查询工资单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public List<PayrollPO> queryPayroll(Date beginDate, Date endDate) {
        return businessHistoryDao.queryPayroll(beginDate, endDate);
    }

    /**
     * 导出销售单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    @Override
    public SXSSFWorkbook downloadSaleSheet(Date beginDate, Date endDate, Integer customer, String salesMan) {
        List<SaleSheetPO> saleSheetPOList = querySaleSheet(beginDate, endDate, customer, salesMan);
        String[] title = new String[]{"单据编号","客户id","业务员","操作员","备注","折让前总额","折让","使用代金券总额","折让后总额","单据状态","创建时间"};
        String[][] values = new String[saleSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (SaleSheetPO item : saleSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getSupplier());j++;
            values[i][j] = ExcelGenerator.getString(item.getSalesman());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getRemark());j++;
            values[i][j] = ExcelGenerator.getString(item.getRawTotalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getDiscount());j++;
            values[i][j] = ExcelGenerator.getString(item.getVoucherAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getFinalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出销售退货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @param salesMan
     * @return
     */
    @Override
    public SXSSFWorkbook downloadSaleReturnsSheet(Date beginDate, Date endDate, Integer customer, String salesMan) {
        List<SaleReturnsSheetPO> saleReturnsSheetPOList = querySaleReturnsSheet(beginDate, endDate, customer, salesMan);
        String[] title = new String[]{"单据编号","关联的销售单id","操作员","供应商"," 销售员","备注","退货的总额合计","单据状态","创建时间","折让前总额","折让", "使用代金券总额", "折让后总额"};
        String[][] values = new String[saleReturnsSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (SaleReturnsSheetPO item : saleReturnsSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getSalesman());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getSupplier());j++;
            values[i][j] = ExcelGenerator.getString(item.getSalesman());j++;
            values[i][j] = ExcelGenerator.getString(item.getRemark());j++;
            values[i][j] = ExcelGenerator.getString(item.getTotalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            values[i][j] = ExcelGenerator.getString(item.getRawTotalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getDiscount());j++;
            values[i][j] = ExcelGenerator.getString(item.getVoucherAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getFinalAmount());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出进货单
     * @param beginDate
     * @param endDate
     * @param customer
     * @return
     */
    @Override
    public SXSSFWorkbook downloadPurchaseSheet(Date beginDate, Date endDate, Integer customer) {
        List<PurchaseSheetPO> purchaseSheetPOList = queryPurchaseSheet(beginDate, endDate, customer);
        String[] title = new String[]{"单据编号","供应商id","操作员","备注","总额合计","单据状态","创建时间"};
        String[][] values = new String[purchaseSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (PurchaseSheetPO item : purchaseSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getSupplier());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getRemark());j++;
            values[i][j] = ExcelGenerator.getString(item.getTotalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出销售退货单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public SXSSFWorkbook downloadPurchaseReturnsSheet(Date beginDate, Date endDate) {
        List<PurchaseReturnsSheetPO> purchaseReturnsSheetPOList = queryPurchaseReturnsSheet(beginDate, endDate);
        String[] title = new String[]{"单据编号","关联的进货单id","操作员","备注","退货的总额合计","单据状态","创建时间"};
        String[][] values = new String[purchaseReturnsSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (PurchaseReturnsSheetPO item : purchaseReturnsSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getPurchaseSheetId());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getRemark());j++;
            values[i][j] = ExcelGenerator.getString(item.getTotalAmount());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出付款单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public SXSSFWorkbook downloadPaymentSheet(Date beginDate, Date endDate) {
        List<PaymentSheetPO> paymentSheetPOList =  queryPaymentSheet(beginDate, endDate);
        String[] title = new String[]{"单据编号","客户","操作员","总额","状态","创建时间"};
        String[][] values = new String[paymentSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (PaymentSheetPO item : paymentSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getCustomer());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getTotal());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出收款单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public SXSSFWorkbook downloadCollectionSheet(Date beginDate, Date endDate) {
        List<CollectionSheetPO> collectionSheetPOList = queryCollectionSheet(beginDate, endDate);
        String[] title = new String[]{"单据编号","客户id","操作员","状态","总额","创建时间"};
        String[][] values = new String[collectionSheetPOList.size()][title.length];
        int i = 0, j = 0;
        for (CollectionSheetPO item : collectionSheetPOList) {
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getCustomer());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            values[i][j] = ExcelGenerator.getString(item.getTotal());j++;

            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }

    /**
     * 导出工资单
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public SXSSFWorkbook downloadPayroll(Date beginDate, Date endDate) {
        List<PayrollPO> payrollPOList = queryPayroll(beginDate, endDate);
        String[] title = new String[]{"创建日期","操作员","单据编号","员工名","员工id","银行卡","应发工资","失业保险","个人所得税","住房公积金","实发金额","状态"};
        String[][] values = new String[payrollPOList.size()][title.length];
        int i = 0, j = 0;
        for (PayrollPO item : payrollPOList) {
            values[i][j] = ExcelGenerator.getString(item.getCreateTime());j++;
            values[i][j] = ExcelGenerator.getString(item.getOperator());j++;
            values[i][j] = ExcelGenerator.getString(item.getId());j++;
            values[i][j] = ExcelGenerator.getString(item.getName());j++;
            values[i][j] = ExcelGenerator.getString(item.getEmployeeId());j++;
            values[i][j] = ExcelGenerator.getString(item.getBankCard());j++;
            values[i][j] = ExcelGenerator.getString(item.getPayableWages());j++;
            values[i][j] = ExcelGenerator.getString(item.getUnemploymentInsurance());j++;
            values[i][j] = ExcelGenerator.getString(item.getPersonalIncomeTax());j++;
            values[i][j] = ExcelGenerator.getString(item.getHousingProvidentFund());j++;
            values[i][j] = ExcelGenerator.getString(item.getActualAmountIssued());j++;
            values[i][j] = ExcelGenerator.getString(item.getState());j++;
            j = 0;
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }


}
