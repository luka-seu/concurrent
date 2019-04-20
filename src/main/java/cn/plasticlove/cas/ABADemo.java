package cn.plasticlove.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author luka-seu
 * @description 演示CAS的ABA问题以及解决方案
 * @create 2019/4/20-12:45
 */
public class ABADemo {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================ABA问题的产生==================");
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        new Thread(() -> {
            atomicReference.compareAndSet(100, 102);
            atomicReference.compareAndSet(102, 100);
            // System.out.println(Thread.currentThread().getName()+"\t修改是否成功："+atomicReference.compareAndSet(2019,100)+"\t结果为："+atomicReference.get());

        }, "t1").start();


        new Thread(() -> {
            //保证t1完成一次ABA操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean result = atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功：" + result + "\t结果为：" + atomicReference.get());
        }, "t2").start();

        Thread.sleep(6000);


        System.out.println("==================ABA问题的解决==================");
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            //保证它t3和t4拿到同一个版本号
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号：" + stampedReference.getStamp());
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号：" + stampedReference.getStamp());
        }, "t3").start();


        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            //保证t3完成了ABA操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t是否修改成功：" + b + "\t当前版本号：" + stampedReference.getStamp());
            System.out.println("当前实际值：" + stampedReference.getReference());

        }, "t4").start();


    }


}
