package com.liuyang19900520.robotlife.web.controller;

import com.liuyang19900520.robotlife.common.pojo.Messages;
import com.liuyang19900520.robotlife.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.domain.user.SysUser;
import com.liuyang19900520.robotlife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.liuyang19900520.robotlife.common.pojo.Messages.USER_SIGN_IN_FAILED;
import static com.liuyang19900520.robotlife.common.pojo.Messages.USER_SIGN_IN_SUCCESS;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 16:39
 **/
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/signup/email")
    public Object registerEmail(@RequestBody SysUser user) {

        Map<String, Object> result = userService.signUp("email", user);

        return ResultVo.info((Boolean) result.get("result"), (Messages) result.get("msg"), null);
    }


    @PostMapping("/signup/mobile")
    public Object registerMobile(@RequestBody SysUser user) {

        Map<String, Object> result = userService.signUp("mobile", user);

        return ResultVo.info((Boolean) result.get("result"), (Messages) result.get("msg"), null);
    }


    @PostMapping("/signin")
    public Object signIn(@RequestBody SysUser user) {

        SysUser sysUser = userService.signIn(user);

        if (sysUser != null) {
            return sysUser;
        } else {
            return USER_SIGN_IN_FAILED;
        }

    }


}
