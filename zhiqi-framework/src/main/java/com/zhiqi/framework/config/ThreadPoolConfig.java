package com.zhiqi.framework.config;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author RyuJung
 * @since 2023/4/19-17:50
 */
@Configuration
public class ThreadPoolConfig {

    // 核心线程池大小
    private int corePoolSize = 50;

    // 最大可创建的线程数
    private int maxPoolSize = 200;

    // 队列最大长度
    private int queueCapacity = 1000;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;

    @Bean(name = Constants.THREAD_POOL_BEAN_NAME)
    public ThreadPoolExecutor threadPoolExecutor() {

        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder()
                .namingPattern("zhiqi-thread-pool-%d")
                .daemon(true)
                .build();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        return threadPoolExecutor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = Constants.SCHEDULED_THREAD_POOL_BEAN_NAME)
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {

        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder()
                .namingPattern("zhiqi-scheduled-pool-%d")
                .daemon(true)
                .build();

        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };

        return scheduledExecutor;
    }
}
