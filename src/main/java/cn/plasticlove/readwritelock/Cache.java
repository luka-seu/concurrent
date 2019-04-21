package cn.plasticlove.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luka-seu
 * @description 资源类
 * @create 2019/4/21-12:17
 */
public class Cache {
    //加volatile保证可见性
    private  volatile Map<String,Object> map = new HashMap<>();
    //读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value){


        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t存缓存\t"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t存缓存完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key){

        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t取缓存");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t取缓存完成\t"+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }



}
