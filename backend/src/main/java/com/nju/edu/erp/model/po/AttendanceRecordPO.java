package com.nju.edu.erp.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 11:02 2022/6/29
 * /*@Version:1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRecordPO {
    /**
     * 员工id
     */
    private Integer empId;
    /**
     * 打卡日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
}
