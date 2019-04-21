package cn.plasticlove.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Data.
 *
 * @author luka -seu
 * @description 资源类
 * @create 2019 /4/21-14:56
 */
public class Data {


    private int num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * Incr.
     */
//生产操作加一
    public void incr() {
        lock.lock();
        try {
            //多线程的判断一般用while不是if
            while (num != 0) {
                //当前线程需要阻塞
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"开始生产");
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+"生产结束"+"\t"+num);
            //唤醒其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * Decr.
     */
//消费者减一
    public void decr() {
        lock.lock();
        try {
            //多线程的判断一般用while不是if
            while (num == 0) {
                //当前线程需要阻塞
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"开始消费"+"\t"+num);
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+"消费结束");

            //唤醒其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
