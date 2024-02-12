package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.WarehouseGiftSheetDao;
import com.nju.edu.erp.enums.sheetState.WarehouseGiftSheetState;
import com.nju.edu.erp.model.po.WarehouseGiftSheetContentPO;
import com.nju.edu.erp.model.po.WarehouseGiftSheetPO;

import com.nju.edu.erp.model.vo.warehouse.WarehouseGiftSheetContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseGiftSheetVO;
import com.nju.edu.erp.service.WarehouseGiftSheetService;
import com.nju.edu.erp.utils.IdGenerator;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WarehouseGiftSheetServiceImpl implements WarehouseGiftSheetService {

    WarehouseGiftSheetDao warehouseGiftSheetDao;

    @Autowired
    public WarehouseGiftSheetServiceImpl(WarehouseGiftSheetDao warehouseGiftSheetDao){
        this.warehouseGiftSheetDao = warehouseGiftSheetDao;
    }

    /**
     * 创建库存赠品单
     * 该方法由制定销售单时，使用了赠品的促销策略时会被调用
     * @param warehouseGiftSheetVO
     * @return
     */
    @Override
    public WarehouseGiftSheetVO create(WarehouseGiftSheetVO warehouseGiftSheetVO) {
        WarehouseGiftSheetPO warehouseGiftSheetPO = new WarehouseGiftSheetPO();
        BeanUtils.copyProperties(warehouseGiftSheetVO, warehouseGiftSheetPO);

        // 生成id
        WarehouseGiftSheetPO latest = warehouseGiftSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "KCZPD");
        warehouseGiftSheetPO.setId(id);

        // 设置时间
        warehouseGiftSheetPO.setCreateTime(new Date());

        // 设置状态
        warehouseGiftSheetPO.setState(WarehouseGiftSheetState.PENDING_LEVEL_1);

        // 复制Content
        List<WarehouseGiftSheetContentPO> poList = new ArrayList<>();
        for(WarehouseGiftSheetContentVO vo: warehouseGiftSheetVO.getContentList()){
            WarehouseGiftSheetContentPO po = new WarehouseGiftSheetContentPO();
            BeanUtils.copyProperties(vo, po);
            // 给po设置关联的库存赠品单id
            po.setWarehouseGiftSheetId(id);

            poList.add(po);
        }

        // 存到数据库
        warehouseGiftSheetDao.saveContent(poList);
        warehouseGiftSheetDao.save(warehouseGiftSheetPO);

        // 返回结果
        WarehouseGiftSheetVO res = new WarehouseGiftSheetVO();
        BeanUtils.copyProperties(warehouseGiftSheetPO, res);
        return res;
    }

    /**
     * 审批库存赠品单
     * @param warehouseGiftSheetId
     * @param state
     */
    @Override
    public void approval(String warehouseGiftSheetId, WarehouseGiftSheetState state) {
        if(state.equals(WarehouseGiftSheetState.FAILURE)){
            // 审批失败
            WarehouseGiftSheetPO po = warehouseGiftSheetDao.findById(warehouseGiftSheetId);
            if(po.getState().equals(WarehouseGiftSheetState.SUCCESS)){
                // 本身是审批成功的
                throw new RuntimeException("状态更新失败");
            }
            warehouseGiftSheetDao.approval(warehouseGiftSheetId, state);
        }else if(state.equals(WarehouseGiftSheetState.SUCCESS)){
            // 审批成功
            WarehouseGiftSheetPO po = warehouseGiftSheetDao.findById(warehouseGiftSheetId);
            if(po.getState().equals(WarehouseGiftSheetState.FAILURE)){
                // 本身是审批失败的
                throw new RuntimeException("状态更新失败");
            }
            warehouseGiftSheetDao.approval(warehouseGiftSheetId, state);

            // 审批成功后，通知库存管理人员出库赠品
            // 因为赠品会包含在销售单里，所以此处不用额外生成出库单
        }
    }

    /**
     * 查询某一状态下的单据 or 所有单据
     * @param state
     * @return
     */
    @Override
    public List<WarehouseGiftSheetVO> findSheetByState(WarehouseGiftSheetState state) {
        List<WarehouseGiftSheetPO> poList;
        List<WarehouseGiftSheetVO> res = new ArrayList<>();
        if(state == null){
            // 查询状态为空时，返回所有单据
            poList = warehouseGiftSheetDao.findAll();
        }else{
            poList = warehouseGiftSheetDao.findAllByState(state);
        }

        // 深度拷贝
        for(WarehouseGiftSheetPO po : poList){
            WarehouseGiftSheetVO vo = new WarehouseGiftSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<WarehouseGiftSheetContentPO> contentPOS = warehouseGiftSheetDao.findContentsById(po.getId());
            List<WarehouseGiftSheetContentVO> contentVOS = new ArrayList<>();

            for(WarehouseGiftSheetContentPO contentPO: contentPOS){
                WarehouseGiftSheetContentVO contentVO = new WarehouseGiftSheetContentVO();
                BeanUtils.copyProperties(contentPO, contentVO);
                contentVOS.add(contentVO);
            }
            vo.setContentList(contentVOS);
            res.add(vo);
        }
        return res;
    }
}
