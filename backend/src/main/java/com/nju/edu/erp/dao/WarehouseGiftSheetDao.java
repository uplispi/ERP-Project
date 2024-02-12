package com.nju.edu.erp.dao;



import com.nju.edu.erp.enums.sheetState.WarehouseGiftSheetState;
import com.nju.edu.erp.model.po.WarehouseGiftSheetContentPO;
import com.nju.edu.erp.model.po.WarehouseGiftSheetPO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehouseGiftSheetDao {

    // 获取最近一个库存赠品单
    WarehouseGiftSheetPO getLatest();

    // 保存库存赠品单
    int save(WarehouseGiftSheetPO warehouseGiftSheetPO);

    // 保存库存赠品单的商品条目
    int saveContent(List<WarehouseGiftSheetContentPO> contentList);

    // 审批赠品单
    int approval(String WarehouseGiftSheetId, WarehouseGiftSheetState state);

    // 获取全部库存赠品单
    List<WarehouseGiftSheetPO> findAll();

    // 通过id查询单据
    WarehouseGiftSheetPO findById(String WarehouseGiftSheetId);

    // 通过状态获取库存赠品单
    List<WarehouseGiftSheetPO> findAllByState(WarehouseGiftSheetState state);

    // 通过id获取所有contents
    List<WarehouseGiftSheetContentPO> findContentsById(String WarehouseGiftSheetId);

}
