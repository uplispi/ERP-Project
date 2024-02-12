package com.nju.edu.erp.service.Impl.FactoryProducts;

import com.nju.edu.erp.model.po.PromotionContentPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseGiftSheetContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseGiftSheetVO;
import com.nju.edu.erp.service.WarehouseGiftSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GiftStrategy implements StrategyImpl {

   @Autowired
   WarehouseGiftSheetService warehouseGiftSheetService;

    @Override
    public SaleSheetVO doStrategy(SaleSheetVO saleSheetVO, PromotionContentPO promotionContentPO) {
        String pid = promotionContentPO.getGift();
        Integer num = promotionContentPO.getGiftNum();
        SaleSheetContentVO temp = new SaleSheetContentVO();
        temp.setPid(pid);
        temp.setQuantity(num);
        temp.setRemark("赠品");
        temp.setTotalPrice(new BigDecimal(0));
        temp.setUnitPrice(new BigDecimal(0));
        saleSheetVO.getSaleSheetContent().add(temp);


        WarehouseGiftSheetVO warehouseGiftSheetVO = new WarehouseGiftSheetVO();
        warehouseGiftSheetVO.setSaleSheetId(saleSheetVO.getId());
        warehouseGiftSheetVO.setCustomerId(saleSheetVO.getSupplier());
        warehouseGiftSheetVO.setOperator(saleSheetVO.getOperator());

        List<WarehouseGiftSheetContentVO> list = new ArrayList<>();
        WarehouseGiftSheetContentVO contentVO = new WarehouseGiftSheetContentVO();
        contentVO.setPid(pid);
        contentVO.setQuantity(num);
        contentVO.setUnitPrice(new BigDecimal(0));
        contentVO.setRemark("赠品");
        list.add(contentVO);

        warehouseGiftSheetVO.setContentList(list);

        // 系统生成库存赠品单
        warehouseGiftSheetService.create(warehouseGiftSheetVO);

        return saleSheetVO;
    }
}
