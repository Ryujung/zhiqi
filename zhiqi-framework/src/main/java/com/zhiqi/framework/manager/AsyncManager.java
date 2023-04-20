package com.zhiqi.framework.manager;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.utils.Threads;
import com.zhiqi.common.utils.spring.SpringUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author RyuJung
 * @since 2023/4/19-17:39
 */
public class AsyncManager {

    private static final int DEFAULT_DELAY_TIME_MILLIS = 10;

    private ScheduledThreadPoolExecutor scheduledExecutor = SpringUtils.getBean(Constants.SCHEDULED_THREAD_POOL_BEAN_NAME);

    private AsyncManager() {
    }

    /**
     * 静态内部类方式实现单例模式 + 懒加载
     * 利用静态内部类的加载机制实现线程安全的单例模式
     */
    private static class Holder {
        private static final AsyncManager INSTANCE = new AsyncManager();
    }

    public static AsyncManager me() {
        return Holder.INSTANCE;
    }

    public void execute(TimerTask task) {
        scheduledExecutor.schedule(task, DEFAULT_DELAY_TIME_MILLIS, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        Threads.shutdownAndWaitTermination(scheduledExecutor);
    }

}
