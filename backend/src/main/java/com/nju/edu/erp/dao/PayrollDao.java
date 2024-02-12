package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.DetailPO;
import com.nju.edu.erp.model.po.PayrollPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
@Mapper
public interface PayrollDao {
    /**
     * 查询全部工资单
     * @return
     */
    public List<PayrollPO> queryAll();

    /**
     * 查询工资信息
     * @param year
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<DetailPO> getDetail(Integer year, Date beginDate, Date endDate);//连接员工表，职业表，需要悬浮元组

    /**
     * 添加工资单
     * @param payrollPO
     */
    public int creaeSheet(PayrollPO payrollPO);

    /**
     * 更新工资单
     * @param payrollPO
     */
    public int update(PayrollPO payrollPO);

    /**
     * 删除工资单
     * @param id
     */
    public int deleteSheetByid(String id);

    /**
     * 获取最后id
     * @return
     */
    public PayrollPO getLatest();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public PayrollPO getSheetById(String id);




}
