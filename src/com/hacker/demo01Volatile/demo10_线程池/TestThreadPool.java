package com.hacker.demo01Volatile.demo10_线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/22
 * @description : 线程池的创建、分配线程任务、关闭
 */
public class TestThreadPool {

    public static void main(String[] args) {

        //创建线程池接口
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //为线程池分配线程任务
        ThreadDemo threadDemo = new ThreadDemo();
        for (int i = 0; i < 5; i++) {
            pool.submit(threadDemo);
        }
        //关闭线程池
        pool.shutdown(); //等待线程池的所有线程完成任务后再关闭
        // pool.shutdownNow(); //强制关闭目前正在运行的所有线程

    }
}

class ThreadDemo implements Runnable{

    private int num = 0;

    @Override
    public void run() {
        while (num <=100){
            System.out.println(Thread.currentThread().getName() +"  "+(num++));
        }
    }
}
