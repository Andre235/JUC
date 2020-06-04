package com.hacker.demo01Volatile.demo10_线程池;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/22
 * @description :
 */
public class TestScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        //分配任务
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(101);
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);
            System.out.println(future.get());
        }
        //关闭线程池
        pool.shutdown();
    }
}
