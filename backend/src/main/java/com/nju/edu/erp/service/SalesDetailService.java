package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.SalesDetailFormVO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Date;
import java.util.List;

public interface SalesDetailService {
    /**
     * 根据指定参数返回明细表
     * @return
     */
    List<SalesDetailFormVO> findSalesDetail(Date beginDate, Date endDate, String salesman, String pid, Integer customerId);

    /**
     * 根据指定参数生成Excel表
     * @return
     */
    SXSSFWorkbook salesDetailExcel(Date beginDate, Date endDate, String salesman, String pid, Integer customerId);
}
