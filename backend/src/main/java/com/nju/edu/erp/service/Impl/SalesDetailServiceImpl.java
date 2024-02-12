package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.SalesDetailDao;
import com.nju.edu.erp.model.po.SalesDetailFormPO;
import com.nju.edu.erp.model.vo.SalesDetailFormVO;
import com.nju.edu.erp.service.SalesDetailService;
import com.nju.edu.erp.utils.ExcelGenerator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesDetailServiceImpl implements SalesDetailService {
    private final SalesDetailDao salesDetailDao;

    @Autowired
    public SalesDetailServiceImpl(SalesDetailDao salesDetailDao){
        this.salesDetailDao = salesDetailDao;
    }

    /**
     * 根据指定参数返回明细表
     * @return
     */
    @Override
    public List<SalesDetailFormVO> findSalesDetail(Date beginDate, Date endDate, String salesman, String pid, Integer customerId) {

        List<SalesDetailFormPO> list1 =  salesDetailDao.findSalesDetailInSalesSheet(beginDate, endDate, salesman, pid, customerId);
        ArrayList<SalesDetailFormVO> list = new ArrayList<>();
        for (SalesDetailFormPO salesDetailFormPO : list1) {
            SalesDetailFormVO salesDetailFormVO = new SalesDetailFormVO();
            BeanUtils.copyProperties(salesDetailFormPO, salesDetailFormVO);
            list.add(salesDetailFormVO);
        }
        return list;
    }

    /**
     * 根据指定参数生成Excel表
     * @return
     */
    @Override
    public SXSSFWorkbook salesDetailExcel(Date beginDate, Date endDate, String salesman, String pid, Integer customerId) {
        List<SalesDetailFormVO> salesDetailFormVOList =  findSalesDetail(beginDate, endDate, salesman, pid, customerId);
        String[] title = {"时间", "商品名","型号","数量","单价","总额"};
        String[][] values = new String[salesDetailFormVOList.size()][title.length];
        int i = 0;
        for (SalesDetailFormVO salesDetailFormVO : salesDetailFormVOList) {
            values[i][0] = ExcelGenerator.getString(salesDetailFormVO.getCreateTime());
            values[i][1] = ExcelGenerator.getString(salesDetailFormVO.getName());
            values[i][2] = ExcelGenerator.getString(salesDetailFormVO.getType());
            values[i][3] = ExcelGenerator.getString(salesDetailFormVO.getQuantity());
            values[i][4] = ExcelGenerator.getString(salesDetailFormVO.getUnitPrice());
            values[i][5] = ExcelGenerator.getString(salesDetailFormVO.getTotalPrice());
            i++;
        }
        return ExcelGenerator.getSXSSFWorkbook(title, values);
    }
}
