package com.hacker.demo01Volatile.demo08_顺序执行3个线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/21
 * @description : Condition实现线程之间的通信
 * 实例：编写一个程序：开启3个线程，这三个线程的ID分别为A,B,C，每个线程分别将自己的ID的上打印10次。要求线程分别顺序输出(ABC ABC ABC)
 */
@SuppressWarnings("all")
public class TestAlternateDemo {

    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternateDemo.LoopA(i);
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternateDemo.LoopB(i);
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternateDemo.LoopC(i);
            }
        },"C").start();
    }
}

@SuppressWarnings("all")
class AlternateDemo{

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    void LoopA(int totalLoop){
        lock.lock();
        try{
            if(num != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            num = 2;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void LoopB(int totalLoop) {
        lock.lock();
        try{
            if(num != 2){
                condition2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            num = 3;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void LoopC(int totalLoop) {
        lock.lock();
        try{
            if(num != 3){
                condition3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            num = 1;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

