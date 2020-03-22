package com.hacker.demo01Volatile.demo09_读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/22
 * @description : 线程安全问题：写写/读写，读读不会出现线程安全问题
 */
public class Demo {

    public static void main(String[] args) {
        WriteAndRead writeAndRead = new WriteAndRead();
        for (int i = 0; i < 1000; i++) {
            new Thread(writeAndRead::get,"读线程").start();
        }
        new Thread(() -> writeAndRead.set((int)(Math.random()*1001)),"write:").start();

    }
}

class WriteAndRead{
    private int num = 1;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    void get(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+": "+num);
        } finally {
            lock.readLock().unlock();
        }
    }

    void set(int num){
        lock.writeLock().lock();
        try{
            //Thread.sleep(2000);
            this.num = num;
            System.out.println(Thread.currentThread().getName()+" : "+num);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
