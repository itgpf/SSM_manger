package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> finaAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(Integer roleId) throws Exception;

    List<Permission> findOtherPermission(Integer roleId) throws Exception;

    void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception;
}
