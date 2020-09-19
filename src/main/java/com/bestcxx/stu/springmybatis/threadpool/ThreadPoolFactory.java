package com.bestcxx.stu.springmybatis.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Title: 线程池测试
 * @Description: 线程池测试
 * @Author: wujie
 * @Version: v1.0
 * @Date:2020-06-10
 * @Updatedby:
 */
public class ThreadPoolFactory {
    private final static int CORE_POOL_SIZE = 20;
    private final static int MAX_I_MUM_POOL_SIZE = 100;
    private final static long KEEP_ALIVE_TIME = 0L;
    private final static int WORKQUEUE_SIZE = 1000000;
    /**
     * 自定义线程名称,方便的出错的时候溯源
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("center-pool-%d").build();
    private static ExecutorService threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_I_MUM_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque(WORKQUEUE_SIZE), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 获取线程池
     */
    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    /**
     * 获取线程池工厂
     */
    public static ThreadFactory getThreadFactory(){
        return namedThreadFactory;
    }

    /**
     * 执行任务
     * @param r
     */
    public static void newTask(Runnable r) {
        threadPool.execute(r);
    }

    /**
     * 关闭线程池(如有在执行任务则等待)
     */
    public static void destroyExecutorService(){
        System.out.println("关闭线程池");
        if(!threadPool.isShutdown()){
            threadPool.shutdown();
        }
    }

    /**
     * 立即关闭线程池
     */
    public static void destroyNowExecutorService(){
        System.out.println("立即关闭线程池");
        if(!threadPool.isShutdown()){
            threadPool.shutdownNow();
        }
    }

    /**
     * 线程池是否关闭
     * @return
     */
    public static boolean isExecutorServiceDownNow(){
        return threadPool.isShutdown();
    }
}

