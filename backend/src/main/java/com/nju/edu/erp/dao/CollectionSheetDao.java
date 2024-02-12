package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetContentPO;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectionSheetDao {

    /**
     * 获取最近一条收款单
     * @return
     */
    CollectionSheetPO getLatest();

    /**
     * 存入一条收款单记录
     * @param collectionSheetPO
     * @return
     */
    int save(CollectionSheetPO collectionSheetPO);

    /**
     * 把收款单上的转账列表写入数据库
     * @param collectionSheetContentPOList
     * @return
     */
    int saveContent(List<CollectionSheetContentPO> collectionSheetContentPOList);

    /**
     * 返回所有的收款单
     * @return
     */
    List<CollectionSheetPO> findAll();


    /**
     * 根据state返回收款单
     * @param state
     * @return
     */
    List<CollectionSheetPO> findAllByState(CollectionSheetState state);

    /**
     * 修改收款单状态
     * @param collectionId
     * @param state
     * @return
     */
    int updateState(String collectionId, CollectionSheetState state);

    /**
     * 通过id来返回收款单
     * @param collectionSheetId
     * @return
     */
    CollectionSheetPO findOneById(String collectionSheetId);

    /**
     * 通过收款单的id获取该收款单的相关转账条目
     * @param collectionSheetId
     * @return
     */
    List<CollectionSheetContentPO> findContentByCollectionSheetId(String collectionSheetId);
}
