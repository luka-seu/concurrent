package cn.plasticlove.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author luka-seu
 * @description 生产者消费者传统版本
 * @create 2019/4/21-14:56
 */
public class ProducerAndConsumerBlockingQueueVersion {

    public static void main(String[] args) throws Exception {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
      ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer(blockingQueue);


           new Thread(()->{
               try {
                   System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
                   producerAndConsumer.produce();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           },"PRODUCER").start();



            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
                    producerAndConsumer.consume();
                    System.out.println();
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"CONSUMER").start();

            Thread.sleep(5000);

            producerAndConsumer.stop();





    }


}
