package com.liuyang19900520.robotlife.user.dao;

import com.liuyang19900520.robotlife.user.domain.user.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserCenterDao {

    Integer changeUser(SysUser user);


}
