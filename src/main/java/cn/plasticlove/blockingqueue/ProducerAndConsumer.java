package cn.plasticlove.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luka-seu
 * @description 生产者消费者阻塞队列版本资源类
 * @create 2019/4/21-15:43
 */
public class ProducerAndConsumer {
    //是否开启生产消费+volatile保证多线程间的可见性
    private volatile boolean FLAG = true;
    //原子整形类
    private AtomicInteger atomicInteger = new AtomicInteger();
    //阻塞队列
    BlockingQueue<String> blockingQueue = null;

    public ProducerAndConsumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        //获取当前的接口实现类，阻塞队列
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产方法
    public void produce() throws Exception{

        String data = null;
        boolean b;
        //是true就一直生产
        while (FLAG){
            data = atomicInteger.getAndIncrement()+"";
            b = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (b){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            Thread.sleep(1000);

        }

        System.out.println(Thread.currentThread().getName()+"\t队列生产结束");
    }
    //消费方法
    public void consume() throws Exception{
        String value = null;
        while (FLAG){
            value =  blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (value==null||value.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t消费失败，超时");
                System.out.println();
                System.out.println();

                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费"+value+"成功");
        }
    }
    //推出方法
    public void stop() throws  Exception{
        this.FLAG = false;
    }



}
