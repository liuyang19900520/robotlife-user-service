package com.liuyang19900520.robotlife.user.web.controller;


import com.liuyang19900520.robotlife.user.common.pojo.Messages;
import com.liuyang19900520.robotlife.user.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.user.domain.user.SysUser;
import com.liuyang19900520.robotlife.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

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

    @GetMapping("/signup/email/active")
    public Object registerEmailActive(HttpServletRequest request) {

        String code = request.getParameter("signature");

        Boolean isActive = userService.active(code);

        return isActive ? ResultVo.success(Messages.SIGN_UP_ACTIVE_SUCCESS, null) :
                ResultVo.success(Messages.SIGN_UP_ACTIVE_FAILED, null);

    }


    @PostMapping("/signup/mobile")
    public Object registerMobile(@RequestBody SysUser user) {

        Map<String, Object> result = userService.signUp("mobile", user);

        return ResultVo.info((Boolean) result.get("result"), (Messages) result.get("msg"), null);
    }


    @PostMapping("/signin")
    public SysUser findAccount(@RequestBody String userNmae) {

        SysUser sysUser = userService.signIn(userNmae);

        return sysUser;
    }

    @PostMapping("/roles")
    public Set<String> listRolesByAccount(@RequestBody String userName) {

        Set<String> roles = userService.listRolesByAccount(userName);

        return roles;
    }

    @PostMapping("/permissions")
    public Set<String> listPermissionsByAccount(@RequestBody String userName) {

        Set<String> permissions = userService.listPermissionsByAccount(userName);

        return permissions;
    }


}
