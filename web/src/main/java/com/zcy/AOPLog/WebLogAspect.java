package com.zcy.AOPLog;

import com.zcy.domain.LogInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zcy on 2018/8/23.
 */
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private LogService logService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*1) execution(): 表达式主体
    2) 第一个public *号：表示返回类型， *号表示所有的类型
    3) 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.king.controller包、子孙包下所有类的方法。
    4) 第二个*号：表示类名，*号表示所有的类。
    5) *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数*/
    @Pointcut("execution(public * com.zcy.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        /*logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info(String.valueOf(joinPoint.getSignature().getDeclaringTypeName()));
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }*/
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        int userid = 0;
        if (userinfo!=null){
            userid = userinfo.getId();
        }
        String classname = String.valueOf(joinPoint.getSignature().getDeclaringTypeName());
        String operation = String.valueOf(joinPoint.getSignature().getName());
        String url = request.getRequestURL().toString();
        Date time = new Date();
        String ipadd = request.getRemoteAddr();
        List list = new ArrayList();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            Map map = new HashMap<>();
            String name = enu.nextElement();
            String value =  request.getParameter(name);
            map.put(name,value);
            list.add(map);
        }
        String args = list.toString();
        //1入库标准定了500，2确实没必要存入过多信息
        if(args.length()>490){
            args.substring(0,450);
        }
        //入库
        LogInfo l = new LogInfo();
        l.setUserid(userid);
        l.setClassname(classname);
        l.setOperation(operation);
        l.setUrl(url);
        l.setTime(time);
        l.setIpadd(ipadd);
        l.setArgs(args);
        int i = logService.insertTrack(l);
        if (i!=1){
            logger.info("Tracking failed!");
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        //logger.info("RESPONSE : " + ret);
    }
}
