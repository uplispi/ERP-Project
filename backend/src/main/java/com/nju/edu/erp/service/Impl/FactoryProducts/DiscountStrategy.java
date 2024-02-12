package com.nju.edu.erp.service.Impl.FactoryProducts;

import com.nju.edu.erp.model.po.PromotionContentPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountStrategy implements StrategyImpl {

    @Autowired
    public DiscountStrategy(){}

    @Override
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, PromotionContentPO promotionContentPO) {
        Double discount = promotionContentPO.getDiscount();
        saleSheetVO.setDiscount(new BigDecimal(discount));
        return saleSheetVO;
    }
}
