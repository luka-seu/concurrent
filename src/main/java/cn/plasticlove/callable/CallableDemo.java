package cn.plasticlove.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author luka-seu
 * @description Callable和FutureTask模式
 * @create 2019/4/21-17:09
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableData());

        Thread t1 = new Thread(futureTask,"AAA");
        t1.start();
        //自旋锁的思想，计算完再执行下面的代码
        while (!futureTask.isDone()){

        }

        System.out.println(futureTask.get());

    }



}
