package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.Collection.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface CollectionService {

    /**
     * 制定收款单
     * @param userVO
     * @param collectionSheetVO
     */
    void makeCollectionSheet(UserVO userVO, CollectionSheetVO collectionSheetVO);

    /**
     * 根据单据状态获取收款单
     * @param state
     * @return
     */
    List<CollectionSheetVO> getCollectionSheetByState(CollectionSheetState state);

    /**
     * 根据收款单id进行审批
     * 在controller层进行权限控制
     * @param collectionSheetId 收款单id
     * @param state 修改后的状态
     */
    void approval(String collectionSheetId, CollectionSheetState state);

    /**
     * 根据收款单id搜索收款单
     * @param collectionSheetId
     * @return
     */
    CollectionSheetVO getCollectionSheetById(String collectionSheetId);
}
