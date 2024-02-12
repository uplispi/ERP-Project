package com.nju.edu.erp.service.Impl.FactoryProducts;

import com.nju.edu.erp.model.po.PromotionContentPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReduceStrategy implements StrategyImpl {
    @Override
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, PromotionContentPO promotionContentPO) {
        Double reduce = promotionContentPO.getReduce();
        saleSheetVO.setVoucherAmount(new BigDecimal(reduce));
        return saleSheetVO;
    }
}
