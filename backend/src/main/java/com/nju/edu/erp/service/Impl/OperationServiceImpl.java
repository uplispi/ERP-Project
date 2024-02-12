package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.OperationVO;
import com.nju.edu.erp.service.BusinessHistoryService;
import com.nju.edu.erp.service.OperationService;
import com.nju.edu.erp.utils.ExcelGenerator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final BusinessHistoryService businessHistoryService;


    @Autowired
    public OperationServiceImpl(BusinessHistoryService businessHistoryService){

        this.businessHistoryService = businessHistoryService;

    }

    /**
     * 生成经营情况表
     * @param beginDate
     * @param endDate
     * @return
     */
    public OperationVO getOpertion(Date beginDate, Date endDate){
        //销售
        List<SaleSheetPO> saleSheetPOList =  businessHistoryService.querySaleSheet(beginDate, endDate, null, null);
        List<SaleReturnsSheetPO> saleReturnsSheetPOList = businessHistoryService.querySaleReturnsSheet(beginDate, endDate, null, null);

        //商品
        List<PurchaseSheetPO> purchaseSheetPOList = businessHistoryService.queryPurchaseSheet(beginDate, endDate,null);
        List<PurchaseReturnsSheetPO> purchaseReturnsSheetPOList = businessHistoryService.queryPurchaseReturnsSheet(beginDate, endDate);

        //人力
        List<PayrollPO> payrollPOList = businessHistoryService.queryPayroll(beginDate, endDate);


        BigDecimal totalBeforeDiscount = new BigDecimal("0");//折让前销售收入
        BigDecimal totalAfterDiscount = new BigDecimal("0");//折让后销售收入
        BigDecimal totalReturns = new BigDecimal("0");//销售退货支出
        BigDecimal labourExpenditure = new BigDecimal("0");//人力支出
        BigDecimal purchaseExpenditure = new BigDecimal("0");//进货支出
        BigDecimal purchaseReturnIncome = new BigDecimal("0");//进货收入

        //计算折让前销售收入、折让后销售收入
        for (SaleSheetPO saleSheetPO : saleSheetPOList) {
            totalBeforeDiscount =  totalBeforeDiscount.add(saleSheetPO.getRawTotalAmount());
            totalAfterDiscount =  totalAfterDiscount.add(saleSheetPO.getFinalAmount());
        }

        //销售退货支出
        for (SaleReturnsSheetPO saleReturnsSheetPO : saleReturnsSheetPOList) {
             totalReturns =  totalReturns.add(saleReturnsSheetPO.getFinalAmount());
        }

        //人力支出
        for (PayrollPO payrollPO : payrollPOList) {
            labourExpenditure =  labourExpenditure.add(payrollPO.getActualAmountIssued());
        }

        //进货
        for (PurchaseSheetPO purchaseSheetPO : purchaseSheetPOList) {
            purchaseExpenditure = purchaseExpenditure.add(purchaseSheetPO.getTotalAmount());
        }

        //进货退货
        for (PurchaseReturnsSheetPO purchaseReturnsSheetPO : purchaseReturnsSheetPOList) {
            purchaseReturnIncome = purchaseReturnIncome.add(purchaseReturnsSheetPO.getTotalAmount());
        }

        //汇总
        OperationVO operationVO = OperationVO.builder()
                .totalBeforeDiscount(totalBeforeDiscount)
                .totalAfterDiscount(totalAfterDiscount)
                .totalReturns(totalReturns)
                .labourCosts(labourExpenditure)
                .totalAboutDiscount(totalBeforeDiscount.subtract(totalAfterDiscount))
                .purchaseExpenditure(purchaseExpenditure)
                .purchaseReturnIncome(purchaseReturnIncome)
                .totalRevenue(totalAfterDiscount.add(purchaseReturnIncome))
                .totalExpenditure(labourExpenditure.add(purchaseExpenditure).add(totalReturns)).build();
        operationVO.setTotalAccount(operationVO.getTotalRevenue().subtract(operationVO.getTotalExpenditure()));
        return operationVO;
    }

    /**
     * 导出经营情况excel
     * @param beginDate
     * @param endDate
     * @return
     */
    public SXSSFWorkbook getExcel(Date beginDate, Date endDate){
        OperationVO operationVO = getOpertion(beginDate, endDate);
        String[] title = new String[]{"折让前总额","折让金额","折让后总额","退货总额","总收入","人力成本","进货支出","退货收入","总支出","净利润"};
        String[][] values = new String[1][title.length];
        int i = 0,j = 0;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalBeforeDiscount());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalAboutDiscount());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalAfterDiscount());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalReturns());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalRevenue());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getLabourCosts());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getPurchaseExpenditure());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getPurchaseReturnIncome());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalExpenditure());j++;
        values[i][j] = ExcelGenerator.getString(operationVO.getTotalAccount());j++;
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }



}
