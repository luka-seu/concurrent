package cn.plasticlove.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author luka-seu
 * @description SemaPhore演示
 * @create 2019/4/21-13:48
 */
public class SemaPhoreDemo {

    public static void main(String[] args) {
        //模拟三个停车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6个车抢三个车位
        for (int i = 0;i<6;i++){
            new Thread(() -> {


                try {
                    //获得车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t获得车位");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放车位
                    semaphore.release();
                }



            },String.valueOf(i)).start();
        }
    }
}
