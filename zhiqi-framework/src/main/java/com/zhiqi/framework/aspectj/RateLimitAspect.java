package com.zhiqi.framework.aspectj;

import com.zhiqi.common.annotation.RateLimit;
import com.zhiqi.common.enums.LimitType;
import com.zhiqi.common.exception.ServiceException;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.ip.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/26-15:03
 */
@Aspect
@Component
public class RateLimitAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimitAspect.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> limitScript;

    @Before("@annotation(rateLimit)")
    public void doBefore(JoinPoint joinPoint, RateLimit rateLimit) {
        String key = rateLimit.key();
        int time = rateLimit.time();
        int count = rateLimit.count();

        String redisKey = getRedisRateLimitKey(rateLimit, joinPoint);
        List<Object> keys = Collections.singletonList(redisKey);
        try {
            Long number = redisTemplate.execute(limitScript, keys, count, time);
            if (StringUtils.isNull(number) || number.intValue() > count) {
                throw new ServiceException("访问请求过于频繁，请稍后再试");
            }
            log.info("请求限制'{}'，当前请求'{}'，缓存key'{}'", count, number.intValue(), key);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("服务器限流异常，请稍后再试");
        }
    }

    /**
     * 根据不同的限流策略，设置对应的redisKey
     *
     * @param rateLimit
     * @param joinPoint
     * @return
     */
    private String getRedisRateLimitKey(RateLimit rateLimit, JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer(rateLimit.key());

        if (LimitType.IP.equals(rateLimit.limitType())) {
            sb.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append("-");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> cls = method.getDeclaringClass();
        sb.append(cls.getName()).append("-").append(method.getName());
        return sb.toString();
    }
}
