package cn.plasticlove.reentrantlock;

/**
 * @author luka-seu
 * @description 可重入锁演示
 * @create 2019/4/20-15:47
 */
class Phone {
    public synchronized void sendMessage() {
        System.out.println(Thread.currentThread().getName() + "\t线程发送短信！！");
        //外层函数获取锁之可以自动获取内部方法的锁
        sendMail();
    }

    public synchronized void sendMail() {
        System.out.println(Thread.currentThread().getName() + "\t###########线程发送邮件！！");
    }
}


public class ReentrantLockDemo {
    /**
     * 可重入锁：一个线程可以进入它所拥有的锁所同步的代码块（外层函数可以进入内层函数）
     * <p>
     * t1	线程发送短信！！
     * t1	###########线程发送邮件！！
     * t2	线程发送短信！！
     * t2	###########线程发送邮件！！
     */
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendMessage();
        }, "t1").start();


        new Thread(() -> {
            phone.sendMessage();
        }, "t2").start();
    }


}
