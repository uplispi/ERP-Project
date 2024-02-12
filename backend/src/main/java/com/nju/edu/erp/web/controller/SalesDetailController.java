package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.SalesDetailVO;
import com.nju.edu.erp.service.SalesDetailService;
import com.nju.edu.erp.utils.GetDateFromString;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping(path = "/salesDetail")
public class SalesDetailController {
    private final SalesDetailService salesDetailService;

    @Autowired
    public SalesDetailController(SalesDetailService salesDetailService){
        this.salesDetailService = salesDetailService;
    }

    /**
     * 查询销售明细
     * @param beginDate 初始日期
     * @param endDate 终止日期
     * @param salesman 业务员
     * @param pid 商品id
     * @param customerId 客户id
     * @return
     */
    @GetMapping(value = "/query")
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN, Role.GM})
    public Response query(@RequestParam(value = "beginDate", required = false) String beginDate,
                          @RequestParam(value = "endDate", required = false) String endDate,
                          @RequestParam(value = "salesman", required = false) String salesman,
                          @PathVariable(value = "pid", required = false) String pid ,
                          @RequestParam(value = "customerId", required = false) Integer customerId)
    {
        Date beginDate_ =  GetDateFromString.dateFormat(beginDate);
        Date endDate_ = GetDateFromString.dateFormat(endDate);
        return Response.buildSuccess(salesDetailService.findSalesDetail(beginDate_, endDate_, salesman, pid, customerId));
    }

    /**
     * 导出excel
     * @param response
     * @param salesDetailVO
     * @throws UnsupportedEncodingException
     */
    @PostMapping(value = "/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF,Role.GM})
    public void excel(HttpServletResponse response, @RequestBody SalesDetailVO salesDetailVO) throws UnsupportedEncodingException
    {
        Date beginDate =  salesDetailVO.getBeginDate() == null ? null: GetDateFromString.dateFormat(salesDetailVO.getBeginDate());
        Date endDate = salesDetailVO.getEndDate() == null?  null:GetDateFromString.dateFormat(salesDetailVO.getEndDate());
        String pid = salesDetailVO.getPid();
        Integer customerId = salesDetailVO.getCustomerId();
        String salesman = salesDetailVO.getSalesman();
        Response.buildBlob(response,salesDetailService.salesDetailExcel(beginDate, endDate, salesman, pid, customerId),"销售明细");
    }
}
