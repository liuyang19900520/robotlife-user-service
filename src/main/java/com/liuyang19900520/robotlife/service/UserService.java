package com.liuyang19900520.robotlife.service;

import com.liuyang19900520.robotlife.domain.user.SysUser;

import java.util.Map;

public interface UserService {

    Map<String, Object> signUp(String type, SysUser user);

    SysUser signIn(SysUser user);

}
