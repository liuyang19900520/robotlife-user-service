package com.liuyang19900520.robotlife.user.service.impl;

import com.google.common.collect.Maps;
import com.liuyang19900520.robotlife.user.common.pojo.Messages;
import com.liuyang19900520.robotlife.user.common.util.Encrypt;
import com.liuyang19900520.robotlife.user.common.util.LEmailUtil;
import com.liuyang19900520.robotlife.user.dao.UserDao;
import com.liuyang19900520.robotlife.user.domain.user.SysUser;
import com.liuyang19900520.robotlife.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Set;
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

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Map<String, Object> signUp(String type, SysUser user) {

        Map<String, Object> result = Maps.newConcurrentMap();

        String newPwd = Encrypt.md5(user.getPassword(), user.getUserName());
        user.setPassword(newPwd);

        SysUser sysUser = sysUserDao.checkUser(user);

        //注册的用户已存在
        if (sysUser != null) {
            result.put("result", false);
            result.put("msg", Messages.SIGN_UP_FAILED_USER_EXISTED);
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
                result.put("msg", Messages.SIGN_UP_SUCCESS_MOBILE);
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
                redisTemplate.boundValueOps(code).set(code, 10, TimeUnit.MINUTES);
                String to = user.getEmail();
                String subject = "【 Robot Life Blog 】 注册验证";
                String content = "来自【 Robot Life Blog 】" + "\n" + "亲爱的" + user.getUserName() + "\n" + "    欢迎您注册我的博客，点击此链接激活，完成注册" + "\n" +
                        " http://liuyang19900520.com/users/users/signup/email/active?signature=" + code + "\n" + "如果不能调转，可将连接复制到浏览器中进行访问";

                Runnable emailRunnable = new Runnable() {
                    @Override
                    public void run() {
                        emailUtil.sendSimpleMessage(to, subject, content);
                    }
                };
                taskExecutor.execute(emailRunnable);

                result.put("result", true);
                result.put("msg", Messages.SIGN_UP_SUCCESS_EMAIL);
                return result;
            }

        }
        return result;
    }

    @Override
    public SysUser signIn(String userName) {
        return sysUserDao.findAccount(userName);
    }


    @Override
    public Set<String> listRolesByAccount(String userName) {
        return sysUserDao.listRolesByAccount(userName);
    }

    @Override
    public Set<String> listPermissionsByAccount(String userName) {
        return sysUserDao.listPermissionsByAccount(userName);
    }

    @Override
    public Boolean active(String code) {
        int result = 0;

        if (redisTemplate.boundValueOps(code).get() != null) {
            result = sysUserDao.activeUser(code);
        }
        return result > 0;
    }
}
