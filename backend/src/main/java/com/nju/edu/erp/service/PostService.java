package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.PostType;
import com.nju.edu.erp.model.po.PostPO;
import com.nju.edu.erp.model.vo.PostVO;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 9:42 2022/6/29
 * /*@Version:1.0
 */
public interface PostService {

    /**
     * 根据id获取岗位名称
     * @return 岗位名称
     */
    String getNameById(Integer id);
    /**
     * 查询所有岗位信息
     * @return 所有岗位信息
     */
    List<PostVO> findAllPosts();

    /**
     * 创建新的post
     * @param postVO
     */
    void createPost(PostVO postVO);

    /**
     * 删除一个岗位
     * @param id
     */
    void deletePost(Integer id);

    /**
     * 更新一个岗位信息
     * @param postVO
     * @return
     */
    PostVO updatePost(PostVO postVO);

    /**
     * 根据id查询岗位薪资类型
     * @param id
     * @return
     */
    PostType getPostTypeById(Integer id);
}
