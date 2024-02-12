package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.model.vo.PromotionContentVO;
import com.nju.edu.erp.service.PromotionStrategyService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/promotion")
public class PromotionController{

    private final PromotionStrategyService promotionStrategyService;

    @Autowired
    public PromotionController( PromotionStrategyService promotionStrategyService){
        this.promotionStrategyService = promotionStrategyService;
    }

    /**
     * 添加促销策略
     */
    //@Authorized(roles = {Role.GM})
    @PostMapping(value = "/create")
    public Response add(@RequestBody PromotionContentVO promotionContentVO)  {
        System.out.println(promotionContentVO);
        promotionStrategyService.add(promotionContentVO);
        return Response.buildSuccess();
    }

    /**
     * 删除促销策略
     */
    //@Authorized(roles = {Role.GM})
    @DeleteMapping(value = "/delete")
    public Response delete(@RequestParam(value = "id") Integer id)  {
        promotionStrategyService.del(id);
        return Response.buildSuccess();
    }

    /**
     * 查看所有
     */
    @GetMapping(value = "/queryAll")
    public Response show()  {
        return Response.buildSuccess(promotionStrategyService.getAll().getList());
    }

    /**
     * 查看所有有效的
     */
    @GetMapping(value = "/queryValid")
    public Response showValid()  {
        return Response.buildSuccess(promotionStrategyService.getAllValid().getList());
    }

}
