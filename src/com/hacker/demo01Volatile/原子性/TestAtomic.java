package com.hacker.demo01Volatile.原子性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/19
 * @description :
 */
public class TestAtomic implements Runnable{

    //private int num = 0;
    private AtomicInteger num = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+": "+getNum());
    }

    int getNum(){
        return num.getAndIncrement();
    }
}
