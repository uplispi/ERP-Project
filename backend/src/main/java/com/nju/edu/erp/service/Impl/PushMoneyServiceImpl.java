package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PushMoneyDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.PushMoneyPO;
import com.nju.edu.erp.model.vo.PushMoneyVO;
import com.nju.edu.erp.service.PushMoneyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 10:17 2022/6/29
 * /*@Version:1.0
 */

@Service
public class PushMoneyServiceImpl implements PushMoneyService {

    private final PushMoneyDao pushMoneyDao;

    @Autowired
    public PushMoneyServiceImpl(PushMoneyDao pushMoneyDao){this.pushMoneyDao = pushMoneyDao;}

    /**
     * 根据员工id查询该员工的提成
     * @param id 员工id
     * @return
     */
    @Override
    public PushMoneyVO findPushMoneyById(Integer id) {
        PushMoneyVO pushMoneyVO = new PushMoneyVO();
        PushMoneyPO pushMoneyPO = pushMoneyDao.findById(id);
        BeanUtils.copyProperties(pushMoneyPO, pushMoneyVO);

        return pushMoneyVO;
    }

    /**
     * 查询所有员工的提成信息
     * @return
     */
    @Override
    public List<PushMoneyVO> findAllPushMoney() {
        List<PushMoneyPO> queryAns = pushMoneyDao.findAll();
        List<PushMoneyVO> responseVO = queryAns.stream().map(pushMoneyPO -> {
            PushMoneyVO pushMoneyVO = new PushMoneyVO();
            BeanUtils.copyProperties(pushMoneyPO, pushMoneyVO);
            return pushMoneyVO;
        }).collect(Collectors.toList());

        return responseVO;
    }

    /**
     * 更新某员工的提成信息
     * @param pushMoneyVO
     * @return
     */
    @Override
    public PushMoneyVO updatePushMoney(PushMoneyVO pushMoneyVO) {
        PushMoneyPO pushMoneyPO = new PushMoneyPO();
        BeanUtils.copyProperties(pushMoneyVO, pushMoneyPO);
        int ans = pushMoneyDao.update(pushMoneyPO);
        if(ans == 0){
            throw new MyServiceException("B0003", "更新失败！");
        }

        pushMoneyPO = pushMoneyDao.findById(pushMoneyPO.getEmpId());
        PushMoneyVO res = new PushMoneyVO();
        BeanUtils.copyProperties(pushMoneyPO, res);
        return res;
    }

    /**
     * 创建新的员工提成表项（当且仅当有带提成的员工入职）
     * @param pushMoneyVO
     * @return
     */
    @Override
    public PushMoneyVO createPushMoney(PushMoneyVO pushMoneyVO) {
        PushMoneyPO pushMoneyPO = new PushMoneyPO();
        BeanUtils.copyProperties(pushMoneyVO, pushMoneyPO);
        pushMoneyDao.createPushMoney(pushMoneyPO);

        return pushMoneyVO;
    }

    /**
     * 根据员工id删除该员工的提成表项（当且仅当该员工离职）
     * @param id 员工id
     */
    @Override
    public void deletePushMoneyById(Integer id) {
        int ans = pushMoneyDao.deleteById(id);
        if (ans == 0) {
            throw new MyServiceException("B0004", "删除失败");
        }
    }
}
