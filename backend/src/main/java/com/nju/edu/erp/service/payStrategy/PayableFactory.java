package com.nju.edu.erp.service.payStrategy;

import com.nju.edu.erp.enums.PostType;

public class PayableFactory {
    /**
     * 简单工厂，生成薪资策略
     * @param type 薪资类型
     * @return
     */
    public static PayStrategy getPayable(PostType type){
        switch (type){
            case MONTHLY:return new MonthPay();
            case MONTHLY_WITH_PUSH:return new MonthWithPushPay();
            case YEARLY:return new YearlyPay();
            case YEARLY_WITH_PUSH:return new YearlyWithPushPay();
        }
        return null;
    }
}
