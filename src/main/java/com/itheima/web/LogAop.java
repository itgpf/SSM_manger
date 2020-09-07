package com.itheima.web;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: ssm_authority
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-07-17 09:03
 */
@Component
@Aspect
public class LogAop {
    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    //前置通知 主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.itheima.web.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz=jp.getTarget().getClass();
        String methodName=jp.getSignature().getName();//获取访问方法的名称
        Object[] args=jp.getArgs();//获取访问方法的参数
        //获取具体执行的方法的method对象
        if (args==null||args.length==0){
            method=clazz.getMethod(methodName);
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i]=args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }
    @After("execution(* com.itheima.web.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问的时长
        long time = new Date().getTime()-visitTime.getTime();
       //获取url
        String url=" ";
        if (clazz!=null&&method!=null&&clazz!=LogAop.class){
            //1.获取类方法上的@RequestMapping
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classValue=classAnnotation.value();
                    //2.获取方法上的@RequestMapping
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if (methodAnnotation!=null){
                        String[] methodValue = methodAnnotation.value();
                        url=classValue[0]+methodValue[0];
                        //获取访问的ip
                        String ip = request.getRemoteAddr();
                        //获取当前操作的用户
                        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                        User user = (User) context.getAuthentication().getPrincipal();
                        String username = user.getUsername();
                        if (clazz.getName()!="com.itheima.web.SysLogController"){
                            SysLog sysLog = new SysLog();
                            sysLog.setExecutionTime(time);
                            sysLog.setIp(ip);
                            sysLog.setUrl(url);
                            sysLog.setMethod("[类名]"+clazz.getName()+"方法名"+method.getName());
                            sysLog.setUsername(username);
                            sysLog.setVisitTime(visitTime);
                            //调用service
                            sysLogService.save(sysLog);
                        }

                    }



            }
        }

    }

}