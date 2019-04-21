package cn.plasticlove.readwritelock;

/**
 * @author luka-seu
 * @description 读写锁
 * @create 2019/4/21-12:16
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        Cache cache = new Cache();
        //多线程读
        for (int i = 0;i<10;i++){
            String temp = String.valueOf(i);
            new Thread(() -> {

                cache.put(temp,temp);
            },String.valueOf(i)).start();
        }

        //多线程写

        for (int i = 0;i<10;i++){
            String temp = String.valueOf(i);
            new Thread(() -> {
                cache.get(temp);
            },String.valueOf(i)).start();
        }

    }



}
