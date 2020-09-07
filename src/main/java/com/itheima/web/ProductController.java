package com.itheima.web;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @program: ssm_authority
 * @description: 产品xinxi
 * @author: Geng Peng fei
 * @create: 2020-06-09 16:37
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    //商品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        System.out.println("方法执行了");
        productService.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findAll.do")
    @RolesAllowed("USER")
    public ModelAndView finaAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list");
        return mv;
    }
}