package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SheetState;
import com.nju.edu.erp.model.vo.UserVO;

public interface SheetService {
    /**
     * 审批单据
     * @param user
     * @param id
     * @param state
     */
    public void approval(UserVO user, String id, SheetState state);

    /**
     * 根据id删除工资单
     * @param id
     */
    public void deleteSheet(String id);
}
