package cn.plasticlove.cylicbarier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author luka-seu
 * @description 回环栅栏
 * @create 2019/4/21-13:22
 */
public class CylicBarrierDemo {
    public static void main(String[] args) {
        //召唤7颗龙珠，召唤神龙
        CyclicBarrier barrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤七颗龙珠");
        });


        for (int i = 0;i<7;i++){
            final int temp = i;
            new Thread(()->{
                System.out.println("召唤了第"+temp+"颗龙珠");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }



    }



}
