package cn.plasticlove.countdowmlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author luka-seu
 * @description CountDownLatch演示    秦灭六国，一统华夏
 * @create 2019/4/21-12:56
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        //所有人走完才能锁门
        for (int i = 0;i<6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室！！！");
                //每执行一次计数器减一
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            //当前线程阻塞等待计数器到零
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t班长锁门！！！");


    }
}
