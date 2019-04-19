package cn.plasticlove.thread;

/**
 * @author luka-seu
 * @description volatile可见性
 * @create 2019/4/17-12:06
 */


class Data {
    //加入volatile
    volatile int num = 0;

    public void addTo() {
        this.num = 60;
    }
}

public class VolatileDemo {

    public static void main(String[] args) {
        Data data = new Data();
        //开启线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");

            data.addTo();
            try {
                //休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.println(Thread.currentThread().getName()+" is over "+data.num);
        }, "AAA").start();

        //如果为零，则一直自旋
        while (data.num == 0) {

        }

        //不为零，就打印主线程
        System.out.println(Thread.currentThread().getName() + " come back " + data.num);
    }


}
