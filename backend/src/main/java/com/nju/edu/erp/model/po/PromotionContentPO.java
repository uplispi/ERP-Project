package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionContentPO {
    //策略id
    private Integer id;
    //策略名
    private String name;
    //折扣
    private Double discount;
    //优惠券
    private Double reduce;
    //等级
    private Integer level;
    //开始时间
    private Date start;
    //截止时间
    private Date end;
    //采用的策略
    private String strategy;
    //赠品
    private String gift;
    //赠品数量
    private Integer giftNum;
}
