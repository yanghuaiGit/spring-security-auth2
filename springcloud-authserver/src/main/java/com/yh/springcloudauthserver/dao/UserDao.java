package com.yh.springcloudauthserver.dao;


import com.yh.springcloudauthserver.domain.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public SysUser findByUserName(String username);
    int create (SysUser sysUser);
}
