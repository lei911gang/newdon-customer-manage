package com.newdon.logback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.newdon.constants.CommonConstants;
import com.newdon.entity.SystemLog;
import com.newdon.mapper.SysLogMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Rlserim
 * @create 2018/1/13 15:12
 * @desc
 **/
@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogMapper sysLogMapper;
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String STRING_START = "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    private static final String STRING_END = "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n";

    @Pointcut("execution(* com.newdon.controller..*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestUUID = MDC.get(CommonConstants.REQUEST_UUID);
        if (StringUtils.isBlank(requestUUID)) {
            MDC.put(CommonConstants.REQUEST_UUID, UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String[] parameterNames = methodSignature.getParameterNames();
        Class<?> targetClass = method.getDeclaringClass();

        String target = targetClass.getName() + "#" + method.getName();
        LOG.info(STRING_START + "开始调用--> {} ", target);
        LOG.info("URL : " + request.getRequestURL().toString());

        LOG.info("HTTP_METHOD : " + request.getMethod());

        LOG.info("IP : " + request.getRemoteAddr());

        Map<String, String> map = new ConcurrentHashMap<>();
        String args = null;
        for (String s : parameterNames) {
            String parameter = request.getParameter(s);
            if (StringUtils.isNotBlank(parameter)) {
                map.put(s, parameter);
            }
        }
        if (map.size() > 0) {

            LOG.info("ARGS : " + map.toString());
        } else {
            try {
                args = JSONObject.toJSONString(joinPoint.getArgs());
                LOG.info("ARGS : " + args);
            } catch (Exception e) {
            }
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;
        String param = null;
        try {
            param = JSONObject.toJSONStringWithDateFormat(result, DATE_FORMAT, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("RETURN : " + param);
        LOG.info("\n调用结束<-- {} 耗时:{}ms" + STRING_END, target, timeConsuming);
        SystemLog sysLog = getSysLog(request);
        if (StringUtils.isNotBlank(sysLog.getOperationType()) && StringUtils.isNotBlank(sysLog.getOperationProject())) {
            if (map.size() > 0) {
                sysLog.setArgs(map.toString());
            }
            if (StringUtils.isNotBlank(args)) {
                sysLog.setArgs(args);
            }
            new SysLogCommand(sysLogMapper, sysLog).run();
        }
        return result;
    }

    public SystemLog getSysLog(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        SystemLog sysLog = new SystemLog();
        sysLog.setTime(System.currentTimeMillis());
        String username = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader("username");
        if (StringUtils.isBlank(username)) {
            username = (String) request.getSession().getAttribute("username");
        }
        sysLog.setOperator(username);
        String[] split = url.split("/");
        List<String> list = Arrays.asList(split);
        String methodName = list.get(list.size() - 1);

        switch (methodName) {
            case "add":
                sysLog.setOperationType("新增");
                break;
            case "delete":
                sysLog.setOperationType("删除");
                break;
            case "update":
                sysLog.setOperationType("修改");
                break;
            case "login":
                sysLog.setOperationType("登录");
                break;
            case "query":
                sysLog.setOperationType("查询");
                break;
            default:
                sysLog.setOperationType("其他");
        }
        String projectName = list.get(list.size() - 2);
        switch (projectName) {
            case "user":
                sysLog.setOperationProject("智能卫士子系统用户");
                break;
            case "userDevice":
                sysLog.setOperationProject("智能卫士子系统用户设备");
                break;
            default:
                sysLog.setOperationProject("其他");
        }
        sysLog.setUrl(url);
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setIp(request.getRemoteAddr());
        return sysLog;
    }
}
