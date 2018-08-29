package com.liuyang19900520.robotlife.service;

import com.liuyang19900520.robotlife.domain.user.SysUser;

import java.util.Map;
import java.util.Set;

public interface UserService {

    Map<String, Object> signUp(String type, SysUser user);

    SysUser signIn(String userName);

    Set<String> listRolesByAccount(String userName);

    Set<String> listPermissionsByAccount(String userName);


}
