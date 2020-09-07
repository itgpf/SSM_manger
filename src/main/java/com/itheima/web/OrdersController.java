package com.itheima.web;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @program: ssm_authority
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-06-10 17:47
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
   @Autowired
   private IOrdersService ordersService;
   //为分页
  /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
      ModelAndView mv= new ModelAndView();
       List<Orders> ordersList = ordersService.findAll();
       mv.addObject("ordersList",ordersList);
       mv.setViewName("orders-list");
       return mv;
   }*/
  //分页
   @RequestMapping("/findAll.do")
   @Secured("ROLE_USER")
   public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
       ModelAndView mv= new ModelAndView();
       List<Orders> ordersList = ordersService.findAll(page, size);
       //pageInfo就是一个分页bean
       PageInfo pageInfo=new PageInfo(ordersList);
       mv.addObject("pageInfo",pageInfo);
       mv.setViewName("orders-page-list");
       return mv;
   }
   @RequestMapping("/findById.do")
   public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId) throws Exception {
       ModelAndView mv=new ModelAndView();
       Orders orders=ordersService.findById(ordersId);
       mv.addObject("orders",orders);
       mv.setViewName("orders-show");
       return mv;
   }
}