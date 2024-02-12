package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.service.Impl.FactoryProducts.DiscountStrategy;
import com.nju.edu.erp.service.Impl.FactoryProducts.GiftStrategy;
import com.nju.edu.erp.service.Impl.FactoryProducts.ReduceStrategy;
import com.nju.edu.erp.service.Impl.FactoryProducts.StrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyFactory {

    @Autowired
    DiscountStrategy discountStrategy;//折扣

    @Autowired
    ReduceStrategy reduceStrategy;//满减

    @Autowired
    GiftStrategy giftStrategy;//礼物

//    public static StrategyImpl getImpl(String type){
//        if (type.equals("discount")){
//            return new DiscountStrategy();
//        }else if (type.equals("reduce")){
//            return new ReduceStrategy();
//        }else if(type.equals("gift")){
//            return new GiftStrategy();
//        }else {
//            return null;
//        }
//    }

    public  StrategyImpl getImpl(String type){
        if (type.equals("discount")){
            return this.discountStrategy;
        }else if (type.equals("reduce")){
            return this.reduceStrategy;
        }else if(type.equals("gift")){
            return this.giftStrategy;
        }else {
            return null;
        }
    }
}
