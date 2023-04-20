package com.zhiqi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author RyuJung
 * @since 2023/4/19-18:03
 */
public class Threads {

    private static final Logger log = LoggerFactory.getLogger(Threads.class);

    /**
     * 默认等待线程池关闭的等待时长（分钟）
     */
    private static final int DEFAULT_SHUTDOWN_AWAIT_TIME_MINUTE = 2;

    /**
     * 打印线程异常信息
     *
     * 这个方法中的异常处理主要是处理三种异常：
     * CancellationException、ExecutionException和InterruptedException
     * <br>
     * CancellationException表示任务在执行过程中被取消
     * <br>
     * ExecutionException表示任务执行过程中出现了异常
     * 在该方法中，如果捕获到该异常，则通过调用getCause()方法获取原始异常
     * <br>
     * InterruptedException表示线程被中断。如果捕获到该异常，则将当前线程的中断标志位设置为true
     *
     * @param r 线程执行的任务task
     * @param t 线程执行时捕获的异常
     */
    public static void printException(Runnable r, Throwable t) {
        if (t != null) {
            log.error(t.getMessage(), t);
        } else if (r instanceof Future<?>) {

            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                log.error(ce.getMessage(), ce);
            } catch (ExecutionException ee) {
                log.error(ee.getCause().getMessage(), ee);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    /**
     * 停止线程池
     * <br>
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍然超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    public static void shutdownAndWaitTermination(ExecutorService executor) {
        if (StringUtils.isNotNull(executor) && !executor.isShutdown()) {
            executor.shutdown();
            try {
                boolean isTerminated = executor.awaitTermination(DEFAULT_SHUTDOWN_AWAIT_TIME_MINUTE, TimeUnit.MINUTES);
                if (!isTerminated) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(DEFAULT_SHUTDOWN_AWAIT_TIME_MINUTE, TimeUnit.MINUTES)) {
                        log.error("The executor service terminated fail: {} ", executor);
                    }
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}
