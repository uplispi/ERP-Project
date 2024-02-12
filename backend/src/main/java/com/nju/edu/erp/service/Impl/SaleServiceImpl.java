package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleSheetDao saleSheetDao;

    private final ProductDao productDao;

    private final CustomerDao customerDao;

    private final ProductService productService;

    private final CustomerService customerService;

    private final WarehouseService warehouseService;

    private final PromotionStrategyService promotionStrategyService;

    @Autowired
    public SaleServiceImpl(SaleSheetDao saleSheetDao, ProductDao productDao, CustomerDao customerDao, ProductService productService, CustomerService customerService, WarehouseService warehouseService, PromotionStrategyService promotionStrategyService) {
        this.saleSheetDao = saleSheetDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.productService = productService;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
        this.promotionStrategyService = promotionStrategyService;
    }

    @Override
    @Transactional
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO, Integer promotionStrategyId) {
        // TODO
        // 需要持久化销售单（SaleSheet）和销售单content（SaleSheetContent），其中总价或者折后价格的计算需要在后端进行
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍

        // 设置ID
        SaleSheetPO latest = saleSheetDao.getLatestSheet();  // 上一个单号
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSD");
        saleSheetVO.setId(id);

        // 设置促销策略
        promotionStrategyService.doStrategy(saleSheetVO, promotionStrategyId);

        // 先生成一个销售单PO 把VO中的值拷贝到PO

        SaleSheetPO saleSheetPO = new SaleSheetPO();
        BeanUtils.copyProperties(saleSheetVO, saleSheetPO);

        // 设置PO的其他值
        // 1.根据制定单据人员确定操作员
        saleSheetPO.setOperator(userVO.getName());
        // 2.设置创建时间
        saleSheetPO.setCreateTime(new Date());
        // 3.设置id
        saleSheetPO.setId(id);
        // 4.设置状态
        saleSheetPO.setState(SaleSheetState.PENDING_LEVEL_1);  // 待一级审批

        // 从销售单VO中获取content 拷贝到ContentPO 并计算总额
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal rawTotal = BigDecimal.ZERO;
        List<SaleSheetContentPO> pContentPOList = new ArrayList<>();

        for(SaleSheetContentVO content : saleSheetVO.getSaleSheetContent()){
            SaleSheetContentPO pContentPO = new SaleSheetContentPO();
            BeanUtils.copyProperties(content, pContentPO);
            pContentPO.setSaleSheetId(id);
            BigDecimal unitPrice = pContentPO.getUnitPrice();
            if(unitPrice == null){
                // 如果没输入金额 就通过pid到ProductPO中拿
                ProductPO product = productDao.findById(content.getPid());
                unitPrice = product.getPurchasePrice();
                pContentPO.setUnitPrice(unitPrice);
            }
            // 总额等于单价*数量
            BigDecimal before = unitPrice.multiply(BigDecimal.valueOf(pContentPO.getQuantity()));
            rawTotal = rawTotal.add(before);
            pContentPO.setTotalPrice(before);
            pContentPOList.add(pContentPO);
            // 记入总金额
            totalAmount = totalAmount.add(pContentPO.getTotalPrice());
        }

        // 将ContentPO存入数据库
        saleSheetDao.saveBatchSheetContent(pContentPOList);
        // 设置销售单原始总额
        saleSheetPO.setRawTotalAmount(rawTotal);
        // 设置销售单打折后总额:rawTotal * discount - 代金券voucher
        BigDecimal finalAmount = totalAmount.multiply(saleSheetPO.getDiscount()).divide(new BigDecimal(10));
        finalAmount=finalAmount.subtract(saleSheetPO.getVoucherAmount());
        saleSheetPO.setFinalAmount(finalAmount);

        // 将销售单写入数据库
        saleSheetDao.saveSheet(saleSheetPO);
    }

    @Override
    @Transactional
    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state) {
        // TODO
        // 根据单据状态获取销售单（注意：VO包含SaleSheetContent）
        // 依赖的dao层部分方法未提供，需要自己实现

        // 返回结果 应返回SaleSheetVO
        List<SaleSheetVO> res = new ArrayList<>();
        // 所有销售单PO
        List<SaleSheetPO> all = new ArrayList<>();

        if(state==null){
            // 获得所有的销售单
            all = saleSheetDao.findAllSheet();
        }else{
            all = saleSheetDao.findAllByState(state);
        }

        for(SaleSheetPO sheetPO : all){
            // 对每一张销售单VO 获取其所有contentPO 生成一张销售单vo 并把contentVO填上
            SaleSheetVO sheetVO = new SaleSheetVO();
            BeanUtils.copyProperties(sheetPO, sheetVO);
            // 通过id获得content
            List<SaleSheetContentPO> allContentPO = saleSheetDao.findContentBySheetId(sheetPO.getId());
            // 把 contentPO 拷贝到 contentVO
            List<SaleSheetContentVO> allContentVO = new ArrayList<>();
            for(SaleSheetContentPO p: allContentPO){
                SaleSheetContentVO v = new SaleSheetContentVO();
                BeanUtils.copyProperties(p, v);
                allContentVO.add(v);
            }
            // 把contentVO 添加到 销售单VO里
            sheetVO.setSaleSheetContent(allContentVO);
            // 把销售单VO添加到返回列表中
            res.add(sheetVO);
        }

        return res;
    }

    /**
     * 根据销售单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleSheetId 销售单id
     * @param state       销售单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleSheetId, SaleSheetState state) {
        // TODO
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍
        /* 一些注意点：
            1. 二级审批成功之后需要进行
                 1. 修改单据状态
                 2. 更新商品表
                 3. 更新客户表
                 4. 新建出库草稿
            2. 一级审批状态不能直接到审批完成状态； 二级审批状态不能回到一级审批状态
         */

        // 1.更新销售单状态
        if(state.equals(SaleSheetState.FAILURE)){
            // 审批失败
            SaleSheetPO saleSheet = saleSheetDao.findSheetById(saleSheetId);
            if(saleSheet.getState() == SaleSheetState.SUCCESS)
                throw new RuntimeException("状态更新失败");
            int effectLines = saleSheetDao.updateSheetState(saleSheetId, state);
            if(effectLines == 0)
                throw new RuntimeException("状态更新失败");
        }else{
            // 记录销售单之前的状态
            SaleSheetState preState;
            if(state.equals(SaleSheetState.SUCCESS)){
                // 改为审批成功
                preState = SaleSheetState.PENDING_LEVEL_2;
            }else if(state.equals(SaleSheetState.PENDING_LEVEL_2)){
                preState = SaleSheetState.PENDING_LEVEL_1;
            }else{
                throw new RuntimeException("状态更新失败");
            }
            // 根据销售单当前状态修改
            int effectLines = saleSheetDao.updateSheetStateOnPrev(saleSheetId, preState, state);
            if(effectLines == 0)
                throw new RuntimeException("状态更新失败");

            if(state.equals(SaleSheetState.SUCCESS)){
                // 审批完成，修改一系列状态
                    // 2.更新商品表
                    // 根据saleSheetId查找到对应的content
                    // 得到商品id和单价后更新商品最近零售价recentRp
                List<SaleSheetContentPO> saleSheetContentPOList = saleSheetDao.findContentBySheetId(saleSheetId);
                List<WarehouseOutputFormContentVO> warehouseOutputFormContentVOS = new ArrayList<>();

                for(SaleSheetContentPO contentPO : saleSheetContentPOList){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setId(contentPO.getPid());
                    productInfoVO.setRecentRp(contentPO.getUnitPrice());
                    productService.updateProduct(productInfoVO);

                    WarehouseOutputFormContentVO woContentVO = new WarehouseOutputFormContentVO();
                    woContentVO.setSalePrice(contentPO.getUnitPrice());
                    woContentVO.setQuantity(contentPO.getQuantity());
                    woContentVO.setRemark(contentPO.getRemark());
                    woContentVO.setPid(contentPO.getPid());
                    warehouseOutputFormContentVOS.add(woContentVO);
                }

                // 3.更新客户表(更新应付字段)
                    // 更新应收 receivable 销售=>客户应该付给本公司的前
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
                CustomerPO customerPO = customerService.findCustomerById(saleSheetPO.getSupplier());
                customerPO.setReceivable(customerPO.getReceivable().add(saleSheetPO.getFinalAmount()));
                customerService.updateCustomer(customerPO);

                // 4.制定出库单草稿
                    // 调用创建出库单的方法
                WarehouseOutputFormVO warehouseOutputFormVO = new WarehouseOutputFormVO();
                warehouseOutputFormVO.setOperator(null); //暂不填写
                warehouseOutputFormVO.setSaleSheetId(saleSheetId);
                warehouseOutputFormVO.setList(warehouseOutputFormContentVOS);
                warehouseService.productOutOfWarehouse(warehouseOutputFormVO);
            }
        }
    }

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return
     */

    public CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date beginTime =dateFormat.parse(beginDateStr);
            Date endTime=dateFormat.parse(endDateStr);
            if(beginTime.compareTo(endTime)>0){
                // 时间输入有误
                return null;
            }else{
                return saleSheetDao.getMaxAmountCustomerOfSalesmanByTime(salesman,beginTime,endTime);
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据销售单Id搜索销售单信息
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    @Override
    public SaleSheetVO getSaleSheetById(String saleSheetId) {
        SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
        if(saleSheetPO == null) return null;
        List<SaleSheetContentPO> contentPO = saleSheetDao.findContentBySheetId(saleSheetId);
        SaleSheetVO sVO = new SaleSheetVO();
        BeanUtils.copyProperties(saleSheetPO, sVO);
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        for (SaleSheetContentPO content:
                contentPO) {
            SaleSheetContentVO sContentVO = new SaleSheetContentVO();
            BeanUtils.copyProperties(content, sContentVO);
            saleSheetContentVOList.add(sContentVO);
        }
        sVO.setSaleSheetContent(saleSheetContentVOList);
        return sVO;
    }
}
