package com.nju.edu.erp.service;
import com.nju.edu.erp.model.vo.OperationVO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Date;
public interface OperationService {
    /**
     * 生成经营情况表
     * @param beginDate
     * @param endDate
     * @return
     */
    public SXSSFWorkbook getExcel(Date beginDate, Date endDate);
    /**
     * 导出经营情况excel
     * @param beginDate
     * @param endDate
     * @return
     */
    public OperationVO getOpertion(Date beginDate, Date endDate);
}
