package cn.plasticlove.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luka-seu
 * @description newFixedPoolExecutor的演示
 * @create 2019/4/21-17:47
 */
public class FixedPoolDemo {


    //Executor-->ExecutorService-->ThreadPoolExecutor-->newFixedThreadPool,newSingleThreadPool,newCachedThreadPool
    public static void main(String[] args) {
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 1;i<=10;i++) {
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
