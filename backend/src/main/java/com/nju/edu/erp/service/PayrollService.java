package com.nju.edu.erp.service;
import com.nju.edu.erp.enums.sheetState.SheetState;
import com.nju.edu.erp.model.vo.PayrollVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PayrollService extends SheetService{
    /**
     * 查询所有工资单
     * @return
     */
    public List<PayrollVO> queryAll();

    /**
     * 生成工资单详情
     * @return
     */
    public List<PayrollVO> getDetail();

    /**
     * 添加工资单
     * @param userVO
     * @param payrollVO
     */
    public void createSheet(UserVO userVO, PayrollVO payrollVO);

    /**
     * 更新工资单
     * @param userVO
     * @param payrollVO
     */
    public void updateSheet(UserVO userVO, PayrollVO payrollVO);

    /**
     * 根据id删除工资单
     * @param id
     */
    public void deleteSheet(String id);

    /**
     * 审批单据
     * @param user
     * @param id
     * @param state
     */
    public void approval(UserVO user, String id, SheetState state);

}
