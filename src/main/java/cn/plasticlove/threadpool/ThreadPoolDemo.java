package cn.plasticlove.threadpool;

import java.util.concurrent.*;

/**
 * @author luka-seu
 * @description newFixedPoolExecutor的演示
 * @create 2019/4/21-17:47
 */
public class ThreadPoolDemo {


    //Executor-->ExecutorService-->ThreadPoolExecutor-->newFixedThreadPool,newSingleThreadPool,newCachedThreadPool
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        // ExecutorService threadPool = Executors.newCachedThreadPool();
       //正常情况下请手写线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        try {
            for (int i = 1;i<=100;i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t处理结果");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }





    }

    private static void fixedThreadPoolDemo() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 1;i<=100;i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t处理结果");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


}
