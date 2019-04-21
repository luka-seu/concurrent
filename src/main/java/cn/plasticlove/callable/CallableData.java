package cn.plasticlove.callable;

import java.util.concurrent.Callable;

/**
 * @author luka-seu
 * @description
 * @create 2019/4/21-17:11
 */
public class CallableData implements Callable<String> {

    @Override
    public String call() throws Exception {
        String result = "hello world";


        return result;
    }
}
