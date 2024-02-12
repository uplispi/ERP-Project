package com.nju.edu.erp.service.Impl;


import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.service.SaleReturnsService;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {
    SaleReturnsSheetDao saleReturnsSheetDao;

    ProductService productService;

    ProductDao productDao;

    SaleSheetDao saleSheetDao;

    CustomerService customerService;

    WarehouseService warehouseService;

    WarehouseDao warehouseDao;

    WarehouseOutputSheetDao warehouseOutputDao;

    @Autowired
    public SaleReturnsServiceImpl(SaleReturnsSheetDao saleReturnsSheetDao, ProductService productService, CustomerService customerService, WarehouseService warehouseService, ProductDao productDao,SaleSheetDao saleSheetDao,WarehouseDao warehouseDao, WarehouseOutputSheetDao warehouseOutputDao) {
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.productService = productService;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
        this.productDao = productDao;
        this.saleSheetDao = saleSheetDao;
        this.warehouseDao =  warehouseDao;
        this.warehouseOutputDao = warehouseOutputDao;
    }

    /**
     * 制定销售退货单
     *
     * @param saleReturnsSheetVO 销售退货单
     */
    @Override
    @Transactional
    public void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO) {
        SaleReturnsSheetPO saleReturnsSheetPO = new SaleReturnsSheetPO();
        BeanUtils.copyProperties(saleReturnsSheetVO, saleReturnsSheetPO);
        // 此处根据制定单据人员确定操作员
        saleReturnsSheetPO.setOperator(userVO.getName());
        saleReturnsSheetPO.setCreateTime(new Date());
        SaleReturnsSheetPO latest = saleReturnsSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSTHD");
        saleReturnsSheetPO.setId(id);
        saleReturnsSheetPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContent = saleSheetDao.findContentBySheetId(saleReturnsSheetPO.getSaleSheetId());
        Map<String, SaleSheetContentPO> map = new HashMap<>();
        for(SaleSheetContentPO item : saleSheetContent) {
            map.put(item.getPid(), item);
        }
        System.out.println(saleReturnsSheetVO);
        List<SaleReturnsSheetContentPO> pContentPOList = new ArrayList<>();
        for(SaleReturnsSheetContentVO content : saleReturnsSheetVO.getSaleReturnsSheetContent()) {
            SaleReturnsSheetContentPO pContentPO = new SaleReturnsSheetContentPO();
            BeanUtils.copyProperties(content,pContentPO);
            pContentPO.setSaleReturnsSheetId(id);
            SaleSheetContentPO item = map.get(pContentPO.getPid());
            pContentPO.setUnitPrice(item.getUnitPrice());

            BigDecimal unitPrice = pContentPO.getUnitPrice();
            pContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(pContentPO.getQuantity())));
            pContentPOList.add(pContentPO);
            totalAmount = totalAmount.add(pContentPO.getTotalPrice());
        }
        saleReturnsSheetDao.saveBatch(pContentPOList);
        saleReturnsSheetPO.setTotalAmount(totalAmount);
        saleReturnsSheetDao.save(saleReturnsSheetPO);
    }

    /**
     * 根据状态获取销售退货单[不包括content信息](state == null 则获取所有销售退货单)
     *
     * @param state 销售退货单状态
     * @return 销售退货单
     */
    @Override
    public List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state) {
        List<SaleReturnsSheetVO> res = new ArrayList<>();
        List<SaleReturnsSheetPO> all;
        if(state == null) {
            all = saleReturnsSheetDao.findAll();
        } else {
            all = saleReturnsSheetDao.findAllByState(state);
        }
        System.out.println(all);
        for(SaleReturnsSheetPO po: all) {
            SaleReturnsSheetVO vo = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleReturnsSheetContentPO> alll = saleReturnsSheetDao.findContentBySaleReturnsSheetId(po.getId());
            List<SaleReturnsSheetContentVO> vos = new ArrayList<>();
            for (SaleReturnsSheetContentPO p : alll) {
                SaleReturnsSheetContentVO v = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setSaleReturnsSheetContent(vos);
            res.add(vo);
        }
        return res;
    }

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单id
     * @param state           销售退货单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleReturnsSheetId, SaleReturnsSheetState state) { // TODO
        SaleReturnsSheetPO saleReturnsSheet = saleReturnsSheetDao.findOneById(saleReturnsSheetId);//得到销售退货单
        if(state.equals(SaleReturnsSheetState.FAILURE)) {
            if(saleReturnsSheet.getState() == SaleReturnsSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = saleReturnsSheetDao.updateState(saleReturnsSheetId, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleReturnsSheetState prevState;
            if(state.equals(SaleReturnsSheetState.SUCCESS)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_2;
            } else if(state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleReturnsSheetDao.updateStateV2(saleReturnsSheetId, prevState, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
            if(state.equals(SaleReturnsSheetState.SUCCESS)) {
                // TODO 审批完成, 修改一系列状态
                // 销售退货单id， 关联的销售单id 【   销售退货单id->销售单id->出库单id->批次id】
                //Integer batchId = saleReturnsSheetDao.findBatchId(saleReturnsSheetId);

                //- 销售退货单id-pid， quantity 【批次id+pid -> 定位到库存的一个条目->库存(减去)加上quantity】
                //- 【 pid -> 定位到单位(进价)售价->Σ单位(进价)售价*quantity=要(收回)退回的钱->客户payable(减去)加上要收回的钱】
                List<SaleReturnsSheetContentPO> contents = saleReturnsSheetDao.findContentBySaleReturnsSheetId(saleReturnsSheetId);
                BigDecimal payableToDeduct = BigDecimal.ZERO;
                for (SaleReturnsSheetContentPO content:
                        contents) {
                    String pid = content.getPid();
                    String saleSheetId = saleReturnsSheet.getSaleSheetId();
                    //获得批次号
                    Integer batchId = saleReturnsSheetDao.findBatchId(pid, saleSheetId);
                    Integer quantity = content.getQuantity();
                    WarehousePO warehousePO = warehouseDao.findOneByPidAndBatchId(pid, batchId);
                    if(warehousePO == null) throw new RuntimeException("单据发生错误！请联系管理员！");
//                    if(warehousePO.getQuantity() >= quantity) {
                        warehousePO.setQuantity(-quantity);//减少负数
                        warehouseDao.deductQuantity(warehousePO);
                        ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                        productInfoVO.setQuantity(productInfoVO.getQuantity()+quantity);//增加
                        productService.updateProduct(productInfoVO);
                        payableToDeduct = payableToDeduct.subtract(content.getUnitPrice().multiply(BigDecimal.valueOf(quantity))) ;
//                    } else {
//                        saleReturnsSheetDao.updateState(saleReturnsSheetId, SaleReturnsSheetState.FAILURE);
//                        throw new RuntimeException("商品数量不足！审批失败！");
//                    }
                }

                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleReturnsSheet.getSaleSheetId());
                Integer supplier = saleSheetPO.getSupplier();
                CustomerPO customer = customerService.findCustomerById(supplier);

                customer.setPayable(customer.getPayable().subtract(payableToDeduct));
                customerService.updateCustomer(customer);
            }
        }
    }
}
