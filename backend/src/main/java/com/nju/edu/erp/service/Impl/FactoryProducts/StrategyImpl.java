package com.nju.edu.erp.service.Impl.FactoryProducts;

import com.nju.edu.erp.model.po.PromotionContentPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;

public interface StrategyImpl {

    //执行策略
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, PromotionContentPO promotionContentPO);
}
