package com.hacker.demo01Volatile.demo01_volatile关键字;

/**
 * @author : Jeffersonnn
 * @date : 2020/1/29
 * @description : volatile关键字作用：可以保证多个线程在访问共享数据时，彼此的内存可见，不会产生脏数据(线程1 和 主线程直接操作主内存中的数据)
 * 注意：
 *      1.synchronized是互斥锁，然而volatile不具备互斥性
 *      2.volatile 不具备原子性
 */
public class TestVolatile {

    public static void main(String[] args) {

        //线程1写操作flag字段
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        //main线程读操作flag字段
        /**
         * 由于while(true){} 是底层代码，执行效率非常高
         * 故当main线程 读取线程 1 的flag字段时，线程1对flag字段的写操作还未完成
         * 原因：
         *      内存可见性问题导致，两个线程在操作同一个共享数据时，彼此内存中数据不可见，故会产生脏数据
         * 解决办法：
         *      用同步锁机制可以避免该问题，主线程在每次读写数据时会先到主存中刷新数据(同步最新数据)
         * 带来新的问题：
         *      多个线程操作同一个数据，使用同步锁将会造成效率非常低的现象
         */
        while (true){
            if(threadDemo.getFlag()){
                System.out.println("-------------------");
                break;
            }
        }
    }

}


