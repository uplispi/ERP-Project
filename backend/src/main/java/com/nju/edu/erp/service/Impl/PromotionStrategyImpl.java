package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PromotionDao;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.PromotionContentPO;
import com.nju.edu.erp.model.vo.PromotionContentVO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.PromotionStrategyService;
import com.nju.edu.erp.service.Impl.FactoryProducts.StrategyImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//百分比折扣
@Service
public class PromotionStrategyImpl implements PromotionStrategyService {

    private final PromotionDao promotionDao;
    private final CustomerService customerService;
    private final StrategyFactory strategyFactory;

    @Autowired
    public PromotionStrategyImpl(PromotionDao promotionDao, CustomerService customerService, StrategyFactory strategyFactory){
        this.promotionDao = promotionDao;
        this.customerService = customerService;
        this.strategyFactory= strategyFactory;
    }

    /**
     * 执行促销策略
     * @param saleSheetVO
     * @param strategyId
     * @return
     */
    @Override
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, Integer strategyId) {
        //获取客户
        Integer customerId = saleSheetVO.getSupplier();
        CustomerPO customerPO = customerService.findCustomerById(customerId);
        //获取等级和策略
        Integer level = customerPO.getLevel();
        PromotionContentPO  po = promotionDao.getById(strategyId);
        if (level >= po.getLevel()){
            //获取销售策略
            StrategyImpl strategyImpl = strategyFactory.getImpl(po.getStrategy());
            if (strategyImpl == null){ //类型名与策略名不对应
                throw new RuntimeException("无此类型");
            }else {
                strategyImpl.doStrategy(saleSheetVO, po);
            }
        }
        return saleSheetVO;
    }



    @Override
    public void add(PromotionContentVO promotionContentVO) {
        //将VO转换成PO
        List<PromotionContentPO> poList = new ArrayList<>();
        PromotionContentPO temp = new PromotionContentPO();
        BeanUtils.copyProperties(promotionContentVO, temp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start;
        Date end;
        try{
            start = simpleDateFormat.parse(promotionContentVO.getStartStr());
            end = simpleDateFormat.parse(promotionContentVO.getEndStr());
        }catch (Exception e){ //输入的日期不符合格式，转换失败
            throw new RuntimeException("日期转换失败");
        }
        temp.setStart(start);
        temp.setEnd(end);
        poList.add(temp);
        int result = promotionDao.save(poList);
        if (result == 0)
            throw new RuntimeException("更新失败"); //影响的行数为0，则未更新成功

    }

    @Override
    public void del(Integer id) {
        int result = promotionDao.delete(id);
        if (result == 0)
            throw new RuntimeException("删除失败");
    }

    @Override
    public PromotionVO getAll() {
        List<PromotionContentPO> poList = promotionDao.getAll();
        List<PromotionContentVO> voList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (PromotionContentPO po : poList) {
            PromotionContentVO vo = new PromotionContentVO();
            BeanUtils.copyProperties(po, vo);
            String startStr = simpleDateFormat.format(po.getStart());
            String endStr = simpleDateFormat.format(po.getEnd());
            vo.setStartStr(startStr);
            vo.setEndStr(endStr);
            voList.add(vo);
        }
        PromotionVO result = new PromotionVO();
        result.setList(voList);
        return result;
    }


    @Override
    public PromotionVO getAllValid() {
        List<PromotionContentPO> poList = promotionDao.getAll();
        List<PromotionContentVO> voList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for (PromotionContentPO po : poList) {
            //当前时间与起始截止时间比较
            if (po.getStart().compareTo(now) <= 0 && po.getEnd().compareTo(now) >= 0){
                PromotionContentVO vo = new PromotionContentVO();
                BeanUtils.copyProperties(po, vo);
                String startStr = simpleDateFormat.format(po.getStart());
                String endStr = simpleDateFormat.format(po.getEnd());
                vo.setStartStr(startStr);
                vo.setEndStr(endStr);
                voList.add(vo);
            }
        }
        PromotionVO result = new PromotionVO();
        result.setList(voList);
        return result;
    }
}
