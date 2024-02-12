package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionContentVO {
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
    //采用的策略
    private String strategy;
    //赠品
    private String gift;
    //开始时间
    private String startStr;
    //截止时间
    private String endStr;
    //赠品数量
    private Integer giftNum;

}