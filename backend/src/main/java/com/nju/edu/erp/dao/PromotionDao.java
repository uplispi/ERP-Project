package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.PromotionContentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PromotionDao {
    //存入促销方式
    int save(List<PromotionContentPO> poList);

    //根据id删除
    int delete(int id);

    //查找所有
    List<PromotionContentPO> getAll();

    //根据id查找
    PromotionContentPO getById(int id);
}
