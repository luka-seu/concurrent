package cn.plasticlove.thread;

/**
 * @author luka-seu
 * @description volatile原子性
 * @create 2019/4/17-12:21
 */

class Data2{
    //volatile并不保证原子性
    volatile int num = 0;
    //加synchronized保证原子性，但这里不建议
    public synchronized void  addPulsPuls(){
        num++;
    }
}
public class VolatileDemo2 {

    public static void main(String[] args) {
        Data2 data = new Data2();
        //开启20个线程
        for (int i = 0;i<20;i++) {
            new Thread(() -> {
                //每个线程加1000次
                for (int j = 0;j<1000;j++){
                    data.addPulsPuls();
                }
            },String.valueOf(i)).start();

        }
        //后台默认主线程和GC线程
        while (Thread.activeCount()>2){
            //当前线程交出控制权
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+data.num);
    }
}
