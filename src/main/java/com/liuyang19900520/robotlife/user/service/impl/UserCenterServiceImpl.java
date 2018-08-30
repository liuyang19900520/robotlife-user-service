package com.liuyang19900520.robotlife.user.service.impl;


import com.liuyang19900520.robotlife.user.dao.UserCenterDao;
import com.liuyang19900520.robotlife.user.domain.user.SysUser;
import com.liuyang19900520.robotlife.user.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 16:22
 **/
@Service
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    UserCenterDao userCenterDao;

    @Override
    public Boolean changeUser(SysUser user) {
        return userCenterDao.changeUser(user) > 0;
    }
}
