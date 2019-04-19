package cn.plasticlove.thread;

/**
 * @author luka-seu
 * @description 双重检测模型单例模型
 * @create 2019/4/17-14:07
 */
public class Singleton {

    private static Singleton singleton  = null;

    private Singleton(){
        System.out.println(Thread.currentThread().getName()+" 实例单例对象");
    }

    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null){
                    singleton =  new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0;i<20;i++){
            new Thread(() -> {
                System.out.println(Singleton.getInstance()==Singleton.getInstance());
            },String.valueOf(i)).start();
        }
    }



}
