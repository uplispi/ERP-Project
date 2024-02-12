package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeManagementDao;
import com.nju.edu.erp.dao.PostDao;
import com.nju.edu.erp.enums.PostType;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CategoryPO;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.PostPO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.EmployeeVO;
import com.nju.edu.erp.model.vo.PostVO;
import com.nju.edu.erp.service.PostService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 9:43 2022/6/29
 * /*@Version:1.0
 */

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao){
        this.postDao = postDao;
    }

    @Override
    public String getNameById(Integer id) {
        return postDao.getNameById(id);
    }

    /**
     * 查询所有岗位信息
     * @return
     */
    @Override
    public List<PostVO> findAllPosts() {
        List<PostPO> queryAns = postDao.findAllPosts();
        List<PostVO> responseVO = queryAns.stream().map(postPO -> {
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(postPO, postVO);
            return postVO;
        }).collect(Collectors.toList());
        return responseVO;
    }

    /**
     * 创建一个新的岗位
     * @param postVO 新岗位的信息
     */
    @Override
    public void createPost(PostVO postVO) {
        PostPO postPO = new PostPO();
        BeanUtils.copyProperties(postVO, postPO);
        postDao.createPost(postPO);
    }

    /**
     * 根据岗位id删除一个岗位
     * @param id 岗位id
     */
    @Override
    public void deletePost(Integer id) {
        PostPO postPO = postDao.findPostById(id);
        if(postPO == null){
            throw new MyServiceException("B0004", "没有对应岗位号的岗位，删除失败！");
        }

        int ans = postDao.deletePostById(id);

        if(ans == 0){
            throw new MyServiceException("B0003", "该岗位仍有员工，删除失败！");
        }
    }

    /**
     * 更新岗位信息
     * @param postVO
     * @return
     */
    @Override
    public PostVO updatePost(PostVO postVO) {
        PostPO postPO = new PostPO();
        BeanUtils.copyProperties(postVO, postPO);

        int ans = postDao.updatePost(postPO);
        if(ans == 0){
            throw new MyServiceException("B0003", "更新失败！");
        }

        postPO = postDao.findPostById(postPO.getId());
        PostVO res = new PostVO();
        BeanUtils.copyProperties(postPO, postVO);

        return res;
    }

    /**
     * 根据岗位id获取岗位薪资类型
     * @param id 岗位id
     * @return
     */
    @Override
    public PostType getPostTypeById(Integer id) {
        return postDao.getPostTypeById(id);
    }
}
