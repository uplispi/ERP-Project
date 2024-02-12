package com.nju.edu.erp.service;
import com.nju.edu.erp.model.vo.PromotionContentVO;
import com.nju.edu.erp.model.vo.PromotionVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;


public interface PromotionStrategyService {



    //执行促销策略
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, Integer strategyId);

    //增加促销折扣
    //public void add(PromotionVO promotionVO);

    public void add(PromotionContentVO promotionContentVO);

    //删除促销折扣
    public void del(Integer id);

    //获得所有
    public PromotionVO getAll();

    //获得所有在有效时间内的
    public PromotionVO getAllValid();
}
