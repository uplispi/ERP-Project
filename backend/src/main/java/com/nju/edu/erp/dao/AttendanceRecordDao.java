package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.AttendanceRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 11:15 2022/6/29
 * /*@Version:1.0
 */

@Repository
@Mapper
public interface AttendanceRecordDao {

    //查询对应id员工的所有出勤记录
    List<AttendanceRecordPO> findById(Integer id);
    //查询对应日期当天的所有缺勤记录
    List<AttendanceRecordPO> findByExactDay(Date date);
    //查询对应日期范围内的所有缺勤记录
    List<AttendanceRecordPO> findByDate(Date beginDate, Date endDate);
    //创建一条打卡记录
    int createRecord(AttendanceRecordPO attendanceRecordPO);

}
