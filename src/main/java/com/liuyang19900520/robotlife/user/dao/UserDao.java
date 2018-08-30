package com.liuyang19900520.robotlife.user.dao;


import com.liuyang19900520.robotlife.user.domain.user.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDao {

    Integer signUp(SysUser user);

    SysUser checkUser(SysUser user);

    SysUser activeUser();

    SysUser findAccount(@Param("userName") String userName);

    Set<String> listRolesByAccount(@Param("userName") String userName);

    Set<String> listPermissionsByAccount(@Param("userName") String userName);


}
