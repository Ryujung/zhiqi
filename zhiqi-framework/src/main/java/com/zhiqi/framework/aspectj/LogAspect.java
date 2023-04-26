package com.zhiqi.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.zhiqi.common.annotation.Log;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.enums.BusinessStatus;
import com.zhiqi.common.utils.SecurityUtils;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.ip.IpUtils;
import com.zhiqi.framework.manager.AsyncManager;
import com.zhiqi.framework.manager.factory.AsyncFactory;
import com.zhiqi.system.domain.SysOperLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * @author RyuJung
 * @since 2023/4/26-14:12
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 记录日志中参数的最大长度
     */
    private static final int MAX_RECORD_LENGTH = 2000;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object result) {
        handleLog(joinPoint, controllerLog, null, result);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(pointcut = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    private void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object result) {
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();

            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());

            HttpServletRequest request = ServletUtils.getRequest();
            String ip = IpUtils.getIpAddr(request);
            operLog.setOperIp(ip);
            operLog.setOperUrl(request.getRequestURI());
            if (StringUtils.isNotNull(loginUser)) {
                operLog.setOperName(loginUser.getUsername());
            }
            if (StringUtils.isNotNull(e)) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(e.getMessage());
            }
            // 记录请求的全限定方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");

            // 记录请求方式
            operLog.setRequestMethod(request.getMethod());

            // 处理设置注解上的参数
            getControllerMethodDscription(joinPoint, controllerLog, operLog, result);

            // 异步保存入数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        } catch (Exception exception) {
            log.error("==操作日志的前置通知异常==");
            log.error("异常信息：{}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param controllerLog 日志
     * @param operLog       操作日志
     * @throws Exception
     */
    private void getControllerMethodDscription(JoinPoint joinPoint, Log controllerLog, SysOperLog operLog, Object result) {
        operLog.setBusinessType(controllerLog.businessType().ordinal());
        operLog.setTitle(controllerLog.title());
        operLog.setOperatorType(controllerLog.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (controllerLog.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
        // 是否需要保存response，参数和值
        if (controllerLog.isSaveResponseData() && StringUtils.isNotNull(result)) {
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(result), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, MAX_RECORD_LENGTH));
        } else {
            Map<?, ?> paramMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramMap.toString(), 0, MAX_RECORD_LENGTH));
        }
    }

    private String argsArrayToString(Object[] args) {
        StringBuilder paramsBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(args)) {
            for (Object arg : args) {
                if (paramsBuilder.length() > MAX_RECORD_LENGTH) {
                    return paramsBuilder.toString();
                }
                if (StringUtils.isNotNull(arg) && !isFilterObject(arg)) {
                    try {
                        Object jsonObj = JSON.toJSON(arg);
                        paramsBuilder.append(jsonObj.toString()).append(" ");
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        }
        return paramsBuilder.toString();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param arg 参数信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    private boolean isFilterObject(Object arg) {
        Class<?> cls = arg.getClass();
        if (cls.isArray()) {
            return cls.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            Collection collection = (Collection) arg;
            for (Object o : collection) {
                return o instanceof MultipartFile;
            }
        }
        if (Map.class.isAssignableFrom(cls)) {
            Map map = (Map) arg;
            for (Object o : map.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        boolean result = arg instanceof MultipartFile || arg instanceof HttpServletRequest ||
                arg instanceof HttpServletResponse || arg instanceof BindingResult;
        return result;
    }
}
