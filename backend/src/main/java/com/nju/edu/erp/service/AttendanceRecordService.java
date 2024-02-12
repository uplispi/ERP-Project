package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.AttendanceRecordPO;
import com.nju.edu.erp.model.vo.AttendanceRecordVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.Date;
import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 11:07 2022/6/29
 * /*@Version:1.0
 */
public interface AttendanceRecordService {

    /**
     * 查询对应id员工的出勤记录
     * @param id
     * @return
     */
    List<AttendanceRecordVO> findById(Integer id);

    /**
     * 查询某一天的出勤记录
     * @param date
     * @return
     */
    List<AttendanceRecordVO> findByTime(Date date);

    /**
     * 查询一段时间内的缺勤记录
     * @param beginDate
     * @param endDate
     * @return
     */
    List<AttendanceRecordVO> findByTime(Date beginDate, Date endDate);

    /**
     * 创建一条打卡记录（供员工打卡使用）
     * @param attendanceRecordVO
     */
    void createAttendanceRecord(AttendanceRecordVO attendanceRecordVO);
}
