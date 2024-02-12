package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDetailFormPO {
    private Date createTime;//时间
    private String name;//商品名
    private String pid;//商品id
    private String salesman;//销售员
    private String customer;//客户姓名
    private Integer customerId;//客户id
    private String type;//型号
    private int quantity;//数量
    private BigDecimal unitPrice;//单价
    private BigDecimal totalPrice;//总额
    private String salesType;//销售还是退货
}