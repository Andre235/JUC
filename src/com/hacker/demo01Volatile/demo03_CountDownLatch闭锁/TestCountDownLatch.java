package com.hacker.demo01Volatile.demo03_CountDownLatch闭锁;

import java.util.concurrent.CountDownLatch;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/20
 * @description : 闭锁：在完成某些运算时，只有当其他所有线程的运算完成时，才能执行当前线程的运算
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Demo demo = new Demo(latch);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(demo).start();
        }

        latch.await();

        long end = System.currentTimeMillis();
        System.out.println("多线程执行消耗时间："+(end-start));

        //calcTimes();
    }

    private static void calcTimes() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            if(i%2 == 0){
                System.out.println(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程执行消耗时间："+(end-start));
    }
}

class Demo implements Runnable{

    private CountDownLatch latch;

    public Demo(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 50000; i++) {
                    if(i%2 == 0){
                        System.out.println(i);
                    }
                }
            }finally {
                latch.countDown();
            }
        }
    }
}
