package com.liuyang19900520.robotlife.web;

import com.liuyang19900520.robotlife.common.pojo.Messages;
import com.liuyang19900520.robotlife.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.common.util.LStringUtils;
import com.liuyang19900520.robotlife.domain.SysUser;
import com.liuyang19900520.robotlife.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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


}
