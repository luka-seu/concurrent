package cn.plasticlove.zixuansuo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The type My zi xuan lock.
 *
 * @author luka -seu
 * @description 手写自旋锁
 * @create 2019 /4/20-16:50
 */
public class MyZiXuanLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * Lock.
     */
    public void lock() {
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "\t=========come in=========");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();

        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t========unlock leave=========");
    }

    public static void main(String[] args) {

        MyZiXuanLock lock = new MyZiXuanLock();
        new Thread(() -> {
            lock.lock();
            //t1线程持有当前锁5秒钟
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1").start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            lock.lock();
            //t2线程不停的尝试获取锁
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t2").start();
    }


}
