package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SalesDetailFormPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface SalesDetailDao {
    /**
     * 根据参数在销售单中查询明细表
     * @return 明细表
     */
    List<SalesDetailFormPO> findSalesDetailInSalesSheet(Date beforeTime, Date afterTime, String salesman, String pid, Integer customerId);
}
