package com.hacker.demo01Volatile.demo07_ProductorAndConsumer;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/21
 * @description : 线程间的等待、唤醒案例
 */
public class DemoProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor,"生产者").start();
        new Thread(consumer,"消费者").start();
    }
}

// 售货员
class Clerk{
    private int goods = 0;

    // 进货
    public synchronized void get() {
        if(goods > 10){
            System.out.println("货物已满，不需要在进货...");
            // 货物已满，则不再进货，阻塞生产者线程
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName() + "生产中... 储备量为" + (++goods));
            // 货物未满，唤醒生产者线程，继续进货
            this.notifyAll();
        }
    }

    // 卖货
    public synchronized void sale(){
        if(goods <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前货物已售完...");
        }else{
            System.out.println(Thread.currentThread().getName() + "消费中... 储备量为" + (--goods));
            this.notifyAll();
        }
    }
}

// 生产者
class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}

// 消费者
class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}