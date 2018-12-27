package com.yh.springcloudauthserver.dao;


import com.yh.springcloudauthserver.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangyibo on 17/1/20.
 */
@Repository
public interface PermissionDao {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
