package cn.plasticlove.blockingqueue;

/**
 * @author luka-seu
 * @description 生产者消费者传统版本
 * @create 2019/4/21-14:56
 */
public class ProducerAndConsumerLockVersion {

    public static void main(String[] args) {
       Data1 data1 = new Data1();

       for (int i = 1;i<5;i++){
           new Thread(()->{
               data1.print5();
           },String.valueOf(i)).start();
       }

        for (int i = 1;i<5;i++){
            new Thread(()->{
                data1.print10();
            },String.valueOf(i)).start();
        }


        for (int i = 1;i<5;i++){
            new Thread(()->{
                data1.print15();
            },String.valueOf(i)).start();
        }

        //五个线程交替执行，一个生产，一个消费
        // for(int i = 0;i<5;i++){
        //     new Thread(()->{
        //        data.incr();
        //     },String.valueOf(i)).start();
        // }
        //
        //
        // for(int i = 0;i<5;i++){
        //     new Thread(()->{
        //         data.decr();
        //     },String.valueOf(i)).start();
        // }
    }


}
