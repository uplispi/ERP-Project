package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.DownloadVO;

import com.nju.edu.erp.service.OperationService;
import com.nju.edu.erp.utils.GetDateFromString;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping(path = "/operation")
public class OperationController {
    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService){
        this.operationService = operationService;
    }

    @GetMapping(value="/get")
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN,Role.GM})
    public Response get(@RequestParam(value = "beginDate", required = false) String beginDate, @RequestParam(value="endDate", required = false) String endDate){
        Date beginDate_ =  GetDateFromString.dateFormat(beginDate);
        Date endDate_ =  GetDateFromString.dateFormat(endDate);
        return Response.buildSuccess(operationService.getOpertion(beginDate_, endDate_));
    }

    @PostMapping(value="/excel")
    @Authorized(roles={Role.FINANCIAL_STAFF, Role.ADMIN, Role.GM})
    public void excel(HttpServletResponse response, @RequestBody DownloadVO downloadVO) throws UnsupportedEncodingException {
        Date beginDate =downloadVO.getBeginDate() == null? null: GetDateFromString.dateFormat(downloadVO.getBeginDate());
        Date endDate = downloadVO.getEndDate()==null?null: GetDateFromString.dateFormat(downloadVO.getEndDate());
        Response.buildBlob(response, operationService.getExcel(beginDate, endDate), "经营情况表");
    }
}
