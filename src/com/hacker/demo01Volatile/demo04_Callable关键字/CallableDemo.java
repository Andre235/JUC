package com.hacker.demo01Volatile.demo04_Callable关键字;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/20
 * @description :
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable();

        //创建FutureTask对象，接收Callable接口的实现类 testCallable类
        FutureTask<Integer> futureTask = new FutureTask<>(testCallable);
        //开启线程
        new Thread(futureTask).start();

        // 等待副线程执行完成后，再调用futureTask.get()方法，效果类似闭锁
        Integer integer = futureTask.get();
        System.out.println(integer);
    }
}


class TestCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
            System.out.println(sum);
        }
        return sum;
    }
}
