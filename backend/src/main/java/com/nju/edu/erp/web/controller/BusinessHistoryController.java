package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SheetType;
import com.nju.edu.erp.model.vo.DownloadVO;
import com.nju.edu.erp.service.BusinessHistoryService;
import com.nju.edu.erp.utils.GetDateFromString;
import com.nju.edu.erp.web.Response;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping(path = "/businessHistory")
public class BusinessHistoryController {
    private final BusinessHistoryService businessHistoryService;
    @Autowired
    public BusinessHistoryController(BusinessHistoryService businessHistoryService){
        this.businessHistoryService = businessHistoryService;
    }

    /**
     * 查询表单，已弃用
     * @param beginDate
     * @param endDate
     * @param sheetType
     * @return
     */
    @GetMapping("/querySheet")
    @Authorized(roles = {Role.ADMIN,  Role.FINANCIAL_STAFF, Role.GM})
    public Response querySheet(@RequestParam(value = "beginDate", required = false) Date beginDate,
                               @RequestParam(value = "endDate", required = false) Date endDate,
                               @RequestParam(value = "sheetType", required = false)SheetType sheetType)
//                                @RequestParam(value = "test", required = false)Integer test)

    {
        switch (sheetType){
            case SALE_SHEET:return Response.buildSuccess(businessHistoryService.querySaleSheet(beginDate, endDate, null, null));
            case PAYMENT_SHEET:return Response.buildSuccess(businessHistoryService.queryPaymentSheet(beginDate, endDate));
            case PAYROLL_SHEET:return Response.buildSuccess(businessHistoryService.queryPayroll(beginDate, endDate));
            case PURCHASE_SHEET:return Response.buildSuccess(businessHistoryService.queryPurchaseSheet(beginDate, endDate, null));
            case COLLECTION_SHEET:return Response.buildSuccess(businessHistoryService.queryCollectionSheet(beginDate, endDate));
            case SALE_RETURNS_SHEET:return Response.buildSuccess(businessHistoryService.querySaleReturnsSheet(beginDate, endDate,null, null));
            case PURCHASE_RETURNS_SHEET:return Response.buildSuccess(businessHistoryService.queryPurchaseReturnsSheet(beginDate, endDate));
        }
        return Response.buildSuccess();
    }

    /**
     * 导出表单
     * @param response
     * @param downloadVO
     * @throws UnsupportedEncodingException
     */
    @PostMapping(value = "/downloadSheet", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF,Role.GM})
    public void downloadSheetExcel(HttpServletResponse response, @RequestBody DownloadVO downloadVO) throws UnsupportedEncodingException {
        SXSSFWorkbook wb;
        Date beginDate = GetDateFromString.dateFormat(downloadVO.getBeginDate());
        Date endDate = GetDateFromString.dateFormat(downloadVO.getEndDate());
        Integer customer = downloadVO.getCustomer();
        String salesMan = downloadVO.getSalesMan();
        switch (downloadVO.getSheetType()){
            case SALE_SHEET:{
                wb = businessHistoryService.downloadSaleSheet(beginDate, endDate, customer, salesMan);
                Response.buildBlob(response, wb, "销售单");break;
            }
            case PAYMENT_SHEET:{
                wb = businessHistoryService.downloadPaymentSheet(beginDate, endDate);
                Response.buildBlob(response, wb, "付款单");break;
            }
            case PAYROLL_SHEET:{
                wb = businessHistoryService.downloadPayroll(beginDate, endDate);
                Response.buildBlob(response, wb, "工资单");break;
            }
            case PURCHASE_SHEET:{
                wb = businessHistoryService.downloadPurchaseSheet(beginDate, endDate, customer);
                Response.buildBlob(response, wb, "进货单");break;
            }
            case COLLECTION_SHEET:{
                wb = businessHistoryService.downloadCollectionSheet(beginDate, endDate);
                Response.buildBlob(response, wb, "收款单");break;
            }
            case SALE_RETURNS_SHEET:{
                wb = businessHistoryService.downloadSaleReturnsSheet(beginDate, endDate, customer, salesMan);
                Response.buildBlob(response, wb, "销售退货单");break;
            }
            case PURCHASE_RETURNS_SHEET:{
                wb = businessHistoryService.downloadPurchaseReturnsSheet(beginDate, endDate);
                Response.buildBlob(response, wb, "进货退货单");break;
            }
        }
    }
}
