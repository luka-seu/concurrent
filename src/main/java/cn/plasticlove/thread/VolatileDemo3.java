package cn.plasticlove.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luka-seu
 * @description volatile可见性
 * @create 2019/4/17-12:06
 */


class Data3 {
    //加入volatile
    volatile int num = 0;

    public void addPlusPlus() {
        num++;
    }
    //利用原子类保证原子性
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }

}

public class VolatileDemo3 {

    public static void main(String[] args) {
        Data3 data = new Data3();
        //开启线程
        for (int i = 0;i<20;i++) {
            new Thread(() -> {
                //每个线程加1000次
                for (int j = 0;j<1000;j++){
                    data.addAtomic();
                }
            },String.valueOf(i)).start();

        }
        //后台默认主线程和GC线程
        while (Thread.activeCount()>2){
            //当前线程交出控制权
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+data.atomicInteger);
    }


}
