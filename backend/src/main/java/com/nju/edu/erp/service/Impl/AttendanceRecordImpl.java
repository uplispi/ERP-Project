package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.AttendanceRecordDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.AttendanceRecordPO;
import com.nju.edu.erp.model.vo.AttendanceRecordVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AttendanceRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 11:14 2022/6/29
 * /*@Version:1.0
 */

@Service
public class AttendanceRecordImpl implements AttendanceRecordService {
    private final AttendanceRecordDao attendanceRecordDao;
    private final UserDao userDao;

    @Autowired
    public AttendanceRecordImpl(AttendanceRecordDao attendanceRecordDao, UserDao userDao){this.attendanceRecordDao = attendanceRecordDao;
        this.userDao = userDao;
    }

    /**
     * 根据员工id查询该员工的所有打卡记录
     * @param id
     * @return
     */
    @Override
    public List<AttendanceRecordVO> findById(Integer id) {
        List<AttendanceRecordPO> tmp = attendanceRecordDao.findById(id);
        List<AttendanceRecordVO> res = new ArrayList<>();
        for(AttendanceRecordPO po : tmp ){
            AttendanceRecordVO vo = new AttendanceRecordVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
        }
        return res;
    }

    /**
     * 查询指定日期所有员工的打卡记录
     * @param date
     * @return
     */
    @Override
    public List<AttendanceRecordVO> findByTime(Date date) {
        List<AttendanceRecordPO> findResult = attendanceRecordDao.findByExactDay(date);
        List<AttendanceRecordVO> res = findResult.stream().map(attendanceRecordPO -> {
            AttendanceRecordVO attendanceRecordVO = new AttendanceRecordVO();
            BeanUtils.copyProperties(attendanceRecordPO, attendanceRecordVO);
            return attendanceRecordVO;
        }).collect(Collectors.toList());
        return res;
    }

    /**
     * 查询指定时间段内所有员工的打卡记录
     * @param beginDate 起始时间
     * @param endDate 结束时间
     * @return
     */
    @Override
    public List<AttendanceRecordVO> findByTime(Date beginDate, Date endDate) {
        List<AttendanceRecordPO> findResult = attendanceRecordDao.findByDate(beginDate, endDate);
        List<AttendanceRecordVO> res = findResult.stream().map(attendanceRecordPO -> {
            AttendanceRecordVO attendanceRecordVO = new AttendanceRecordVO();
            BeanUtils.copyProperties(attendanceRecordPO, attendanceRecordVO);
            return attendanceRecordVO;
        }).collect(Collectors.toList());
        return res;
    }

    /**
     * 创建新的打卡记录
     * @param attendanceRecordVO
     */
    @Override
    public void createAttendanceRecord(AttendanceRecordVO attendanceRecordVO) {
        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO();
        BeanUtils.copyProperties(attendanceRecordVO, attendanceRecordPO);
        attendanceRecordPO.setDate(new Date());
        attendanceRecordDao.createRecord(attendanceRecordPO);
    }
}
