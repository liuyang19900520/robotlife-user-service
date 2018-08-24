package com.liuyang19900520.robotlife.dao;

import com.liuyang19900520.robotlife.domain.user.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    Integer signUp(SysUser user);

    SysUser signIn(SysUser user);

    SysUser checkUser(SysUser user);

    SysUser activeUser();


}
