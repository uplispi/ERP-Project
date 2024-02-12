package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.vo.Collection.CollectionSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CollectionService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/collection")
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService){this.collectionService = collectionService; }

    /**
     * 制定收款单
     * @param userVO
     * @param collectionSheetVO
     * @return
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/create")
    public Response makeCollectionSheet(UserVO userVO, @RequestBody CollectionSheetVO collectionSheetVO){
        collectionService.makeCollectionSheet(userVO, collectionSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 审批收款单
     * @param collectionSheetId
     * @param state 修改后的状态("审批失败/审批完成")
     * @return
     */
    @Authorized(roles = {Role.GM,  Role.ADMIN})
    @GetMapping(value = "/approval")
    public Response approval(@RequestParam("collectionSheetId") String collectionSheetId,
                             @RequestParam("state") CollectionSheetState state) {
        if(state.equals(CollectionSheetState.FAILURE) || state.equals(CollectionSheetState.SUCCESS)){
            collectionService.approval(collectionSheetId, state);
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("000000","操作失败");
        }
    }

    /**
     * 根据状态查看收款单
     * @param state
     * @return
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) CollectionSheetState state){
        return Response.buildSuccess(collectionService.getCollectionSheetByState(state));
    }

    /**
     * 通过id查询收款单
     * @param id
     * @return
     */
    @GetMapping(value = "find-sheet")
    public Response findBySheetId(@RequestParam(value = "id") String id){
        return Response.buildSuccess(collectionService.getCollectionSheetById(id));
    }
}
