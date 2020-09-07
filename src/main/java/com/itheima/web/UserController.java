package com.itheima.web;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

/**
 * @program: ssm_authority
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-06-23 20:33
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

   @RequestMapping("/findById.do")
   public ModelAndView findById(String id){
       ModelAndView mv = new ModelAndView();
       UserInfo userInfo = userService.findById(id);
       mv.addObject("user",userInfo);
       mv.setViewName("user-show");
       return mv;
   }

    //用户添加
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }
    //查询用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo=userService.findById(userId);
        List<Role> otherRole = userService.findOtherRole(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRole);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] RoleIds){
       userService.addRoleToUser(userId,RoleIds);
       return "redirect:findAll.do";
    }
}