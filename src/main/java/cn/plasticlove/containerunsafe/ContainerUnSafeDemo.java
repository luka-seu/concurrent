package cn.plasticlove.containerunsafe;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luka-seu
 * @description 集合线程不安全演示
 * @create 2019/4/20-13:54
 */
public class ContainerUnSafeDemo {
    public static void main(String[] args) {
        //ArrayList线程不安全

        //java.util.ConcurrentModificationException
        /**
         * 并发修改异常
         */
        // List<String> list = new ArrayList<>();

        //CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0;i<30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        /**
         * 解决方案：1.Vector类
         *          2.Collections.synchronizedList();
         *          3.CopyOnWriteArrayList  写时复制
         */



        // Thread A = new Thread(new Runnable(){
        //     public void run(){
        //         System.out.print("A");
        //     }
        // });
        // Thread B = new Thread(new Runnable(){
        //     public void run(){
        //         System.out.print("B");
        //     }
        // });
        // Thread C = new Thread(new Runnable(){
        //     public void run(){
        //         System.out.print("C");
        //     }
        // });
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // for(int i = 0;i<10;i++){
        //     executorService.submit(A);
        //     executorService.submit(B);
        //     executorService.submit(C);
        // }


    }










}
