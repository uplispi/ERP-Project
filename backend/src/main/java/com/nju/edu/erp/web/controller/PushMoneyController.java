package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.PushMoneyVO;
import com.nju.edu.erp.service.PushMoneyService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 15:46 2022/6/29
 * /*@Version:1.0
 */

@RestController
@RequestMapping(path = "/pushMoney")
public class PushMoneyController {
    private final PushMoneyService pushMoneyService;

    @Autowired
    public PushMoneyController(PushMoneyService pushMoneyService){this.pushMoneyService = pushMoneyService;}

    /**
     * 查询所有员工提成信息
     * @return
     */
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response findPushMoneyInfo(){
        return Response.buildSuccess(pushMoneyService.findAllPushMoney());
    }

    /**
     * 更新某一员工的提成信息
     * @param pushMoneyVO
     * @return
     */
    @PostMapping("/update")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response updatePushMoneyInfo(@RequestBody PushMoneyVO pushMoneyVO){
        return Response.buildSuccess(pushMoneyService.updatePushMoney(pushMoneyVO));
    }

    /**
     * 创建新的提成信息表项
     * @param pushMoneyVO
     * @return
     */
    @PostMapping("/create")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response createPushMoneyInfo(@RequestBody PushMoneyVO pushMoneyVO){
        return Response.buildSuccess(pushMoneyService.createPushMoney(pushMoneyVO));
    }

    /**
     * 删除某一提成信息表项
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response deletePushMoneyInfo(@RequestParam Integer id){
        pushMoneyService.deletePushMoneyById(id);
        return Response.buildSuccess();
    }

}
