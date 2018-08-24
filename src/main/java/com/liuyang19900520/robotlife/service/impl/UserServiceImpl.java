package com.liuyang19900520.robotlife.service.impl;

import com.google.common.collect.Maps;
import com.liuyang19900520.robotlife.common.pojo.Messages;
import com.liuyang19900520.robotlife.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.common.util.LEmailUtil;
import com.liuyang19900520.robotlife.dao.UserDao;
import com.liuyang19900520.robotlife.domain.SysUser;
import com.liuyang19900520.robotlife.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 16:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao sysUserDao;

    @Autowired
    LEmailUtil emailUtil;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;


    @Override
    public Map<String, Object> signUp(String type, SysUser user) {

        Map<String, Object> result = Maps.newConcurrentMap();

        SysUser sysUser = sysUserDao.checkUser(user);

        //注册的用户已存在
        if (sysUser != null) {
            result.put("result", false);
            result.put("msg", Messages.USER_EXISTED);
            return result;
        }
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        user.setCreateBy(user.getUserId());
        user.setUpdateBy(user.getUserId());


        // register by mobile
        if (StringUtils.equals("mobile", type)) {

            user.setStatus(SysUser.ACTIVE_STATUS);
            int i = sysUserDao.signUp(user);
            if (i > 0) {
                result.put("result", true);
                result.put("msg", Messages.USER_SIGN_UP_SUCCESS_MOBILE);
                return result;
            }
        }

        // register by email
        if (StringUtils.equals("email", type)) {
            String code = UUID.randomUUID().toString().replace("-", "");
            user.setSignature(code);
            user.setStatus(SysUser.UN_ACTIVE_STATUS);
            int i = sysUserDao.signUp(user);
            if (i > 0) {
//                redisTemplate.boundValueOps(code).set(code, 10, TimeUnit.MINUTES);
                String to = user.getEmail();
                String subject = "【東京IAIA】 注册验证";
                String content = "来自【東京IAIA】" + "\n" + "亲爱的" + user.getUserName() + "\n" + "    欢迎您注册我的博客，点击此链接激活，完成注册" + "\n" +
                        " http://localhost:8085/register/email/active?code=" + code + "\n" + "如果不能调转，可将连接复制到浏览器中进行访问";

                Runnable emailRunnable = new Runnable() {
                    @Override
                    public void run() {
                        emailUtil.sendSimpleMessage(to, subject, content);
                    }
                };
                taskExecutor.execute(emailRunnable);

                result.put("result", true);
                result.put("msg", Messages.USER_SIGN_UP_SUCCESS_EMAIL);
                return result;
            }

        }
        return result;
    }

    @Override
    public SysUser signIn(SysUser user) {
        return null;
    }
}
