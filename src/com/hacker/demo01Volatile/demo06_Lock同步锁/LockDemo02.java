package com.hacker.demo01Volatile.demo06_Lock同步锁;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/21
 * @description :
 */
public class LockDemo02 {

    public static void main(String[] args) {
        ThreadDemo02 threadDemo = new ThreadDemo02();

        new Thread(threadDemo,"A号窗口").start();
        new Thread(threadDemo,"B号窗口").start();
        new Thread(threadDemo,"C号窗口").start();
    }
}


@SuppressWarnings("all")
class ThreadDemo02 implements Runnable{

    private int ticket = 200;

    @Override
    public void run() {
        synchronized (this){
            while (true){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName()+"售票完成，当前余票为：" + (--ticket));
                }
            }
        }
    }
}
