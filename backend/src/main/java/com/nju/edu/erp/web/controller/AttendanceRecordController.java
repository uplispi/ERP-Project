package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.AttendanceRecordVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AttendanceRecordService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 15:30 2022/6/29
 * /*@Version:1.0
 */

@RestController
@RequestMapping(path = "/attendanceRecord")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService){this.attendanceRecordService = attendanceRecordService;}


    /**
     * 根据员工id查询该员工的所有打卡记录
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN, Role.INVENTORY_MANAGER, Role.SALE_MANAGER, Role.FINANCIAL_STAFF, Role.SALE_STAFF})
    public Response queryById(@RequestParam Integer id){
        System.out.println("查询打卡记录");
        List<AttendanceRecordVO> res = attendanceRecordService.findById(id);
        System.out.println(Arrays.toString(res.toArray()));
        return Response.buildSuccess(res);
    }

    /**
     * 查询指定日期的所有员工打卡记录
     * @param date 指定日期
     * @return
     */
    @GetMapping("/queryByDay")
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN, Role.INVENTORY_MANAGER, Role.SALE_MANAGER, Role.FINANCIAL_STAFF, Role.SALE_STAFF})
    public Response queryByDay(@RequestParam Date date){
        return Response.buildSuccess(attendanceRecordService.findByTime(date));
    }

    /**
     * 查询某一时间段内所有员工的所有打卡记录
     * @param beginDate 起始时间
     * @param endDate 结束时间
     * @return
     */
    @GetMapping("/queryByTime")
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN, Role.INVENTORY_MANAGER, Role.SALE_MANAGER, Role.FINANCIAL_STAFF, Role.SALE_STAFF})
    public Response queryByTime(@RequestParam Date beginDate, @RequestParam Date endDate){
        return Response.buildSuccess(attendanceRecordService.findByTime(beginDate, endDate));
    }

    /**
     * 创建一天新的打卡记录
     * @param attendanceRecordVO 新的打卡记录
     * @return
     */
    @PostMapping("/createRecord")
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN, Role.INVENTORY_MANAGER, Role.SALE_MANAGER, Role.FINANCIAL_STAFF, Role.SALE_STAFF})
    public Response createRecord(@RequestBody AttendanceRecordVO attendanceRecordVO){
        attendanceRecordService.createAttendanceRecord(attendanceRecordVO);
        return Response.buildSuccess();
    }

}








