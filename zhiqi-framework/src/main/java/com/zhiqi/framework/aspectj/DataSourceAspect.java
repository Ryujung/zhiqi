package com.zhiqi.framework.aspectj;

import com.zhiqi.common.annotation.DataSource;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.framework.datasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 多数据源处理切面
 *
 * @author RyuJung
 * @since 2023/4/26-14:54
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    private final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.zhiqi.common.annotation.DataSource) || @within(com.zhiqi.common.annotation.DataSource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        DataSource dataSource = getDataSource(joinPoint);
        if (StringUtils.isNotNull(dataSource)) {
            log.info("数据源切换至：{}", dataSource.value().name());
            DataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            // 销毁数据源 在执行方法之后，避免内存泄露
            DataSourceContextHolder.clear();
            log.info("数据源已还原");
        }
    }

    /**
     * 获取需要切换的数据源
     * 优先从方法上获取，其次再从类名上获取
     */
    private DataSource getDataSource(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataSource datasource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (StringUtils.isNotNull(datasource)) {
            return datasource;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
