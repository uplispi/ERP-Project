package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailVO {
    private String beginDate;
    private String endDate;
    private String salesman;
    private String pid;
    private Integer customerId;
}
