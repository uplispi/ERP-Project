package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.PostType;
import com.nju.edu.erp.model.po.PostPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 9:41 2022/6/29
 * /*@Version:1.0
 */

@Repository
@Mapper
public interface PostDao {
    //查询所有岗位信息
    List<PostPO> findAllPosts();
    //根据id查询岗位名称
    String getNameById(Integer id);
    //创建新的岗位信息
    int createPost(PostPO postPO);
    //删除一个岗位
    int deletePostById(Integer id);
    //更新岗位信息
    int updatePost(PostPO postPO);
    //根据岗位id查找岗位
    PostPO findPostById(Integer id);
    //根据岗位id查询岗位薪资类型
    PostType getPostTypeById(Integer id);
}
