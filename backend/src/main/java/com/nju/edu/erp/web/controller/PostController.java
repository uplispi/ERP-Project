package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.PostVO;
import com.nju.edu.erp.service.PostService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 15:42 2022/6/29
 * /*@Version:1.0
 */

@RestController
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService = postService;}

    /**
     * 查询所有岗位信息
     * @return
     */
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response queryAllPost(){
        return Response.buildSuccess(postService.findAllPosts());
    }

    /**
     * 创建一个新的岗位
     * @param postVO 新的岗位信息
     * @return
     */
    @PostMapping("/create")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response createPost(@RequestBody PostVO postVO){
        postService.createPost(postVO);
        return Response.buildSuccess();
    }

    /**
     * 更新某一岗位信息
     * @param postVO
     * @return
     */
    @PostMapping("/update")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response updatePost(@RequestBody PostVO postVO){
        return Response.buildSuccess(postService.updatePost(postVO));
    }

    /**
     *根据岗位id删除某一岗位
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @Authorized(roles = {Role.HR, Role.ADMIN})
    public Response deletePost(@RequestParam Integer id){
        postService.deletePost(id);
        return Response.buildSuccess();
    }

}
