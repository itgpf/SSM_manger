package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
   public List<UserInfo> findAll();

   public void save(UserInfo userInfo) throws Exception;



    public void addRoleToUser(String userId, String[] roleIds);

    UserInfo findById(String id);

    List<Role> findOtherRole(String userId) throws Exception;
}
