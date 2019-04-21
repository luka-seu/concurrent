package cn.plasticlove.deadlock;

/**
 * @author luka-seu
 * @description 死锁演示
 * @create 2019/4/21-20:40
 */


class Data implements Runnable {

    private String lockA;
    private String lockB;

    public Data(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t"+"持有锁"+lockA+"，试图获取锁"+lockB);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t"+"持有锁"+lockB+"，试图获取锁"+lockA);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new Data(lockA,lockB)).start();
        new Thread(new Data(lockB,lockA)).start();
    }
    /**
     * 定位问题：jps+jstack
     *
     *
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     *         at cn.plasticlove.deadlock.Data.run(DeadLockDemo.java:31)
     *         - waiting to lock <0x00000007805111a8> (a java.lang.String)
     *         - locked <0x00000007805111e0> (a java.lang.String)
     *         at java.lang.Thread.run(Thread.java:748)
     * "Thread-0":
     *         at cn.plasticlove.deadlock.Data.run(DeadLockDemo.java:31)
     *         - waiting to lock <0x00000007805111e0> (a java.lang.String)
     *         - locked <0x00000007805111a8> (a java.lang.String)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     */

}
