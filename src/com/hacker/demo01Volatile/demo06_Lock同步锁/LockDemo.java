package com.hacker.demo01Volatile.demo06_Lock同步锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/21
 * @description :
 * Lock同步锁：是显式锁，必须通过lock()方法上锁，unlock()方法解锁
 */
public class LockDemo {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();

        new Thread(threadDemo,"1号窗口").start();
        new Thread(threadDemo,"2号窗口").start();
        new Thread(threadDemo,"3号窗口").start();
    }
}

class ThreadDemo implements Runnable{

    private int ticket = 1000;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock(); // 上锁
            try{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName()+"售票完成，当前余票为：" + (--ticket));
                }
            }finally {
                lock.unlock(); // 释放锁
            }
        }
    }
}
