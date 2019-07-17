package com.facerecog.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAopConfiguration {

    static final Logger LOGGER = LoggerFactory.getLogger(LogAopConfiguration.class);

    @Autowired
    private HttpServletRequest request;

    private Class clazz; //访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.facerecog.controller.*.*(..))")
    public void doBefore(JoinPoint jp) {
        try {
            clazz = jp.getTarget().getClass(); //具体要访问的类
            String methodName = jp.getSignature().getName(); //获取访问的方法的名称
//            Object[] args = jp.getArgs();//获取访问的方法的参数

            //获取具体执行的方法的Method对象
//            if (args == null || args.length == 0) {
//                method = clazz.getMethod(methodName); //只能获取无参数的方法
//            } else {
//                Class[] classArgs = new Class[args.length];
//                for (int i = 0; i < args.length; i++) {
//                    classArgs[i] = args[i].getClass();
//                }
//                method = clazz.getDeclaredMethod(methodName, classArgs);
//            }

            String ip = request.getRemoteAddr();
            String url = request.getContextPath() + request.getServletPath();

            System.out.println(">>>>>>>>>>>>>>>>>>开始>>>>>>>>>>>>>>>>>");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-[Ip]" + ip + "-[Mapping]" + url + "-[类名]" + clazz.getName() + "-[方法]" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //后置通知
    @After("execution(* com.facerecog.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        try {
//            String url = "";
//            //获取url
//            if (clazz != null && method != null && clazz != LogAop.class) {
//                //1.获取类上的@RequestMapping("/orders")
//                RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
//                if (classAnnotation != null) {
//                    String[] classValue = classAnnotation.value();
//                    //2.获取方法上的@RequestMapping(xxx)
//                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
//                    if (methodAnnotation != null) {
//                        String[] methodValue = methodAnnotation.value();
//                        url = classValue[0] + methodValue[0];
//                    }
//                }
//                //获取当前操作的用户
//                SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
//                User user = (User) context.getAuthentication().getPrincipal();
//                String username = user.getUsername();
//
//            }
            System.out.println("<<<<<<<<<<<<<<<<<结束<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
