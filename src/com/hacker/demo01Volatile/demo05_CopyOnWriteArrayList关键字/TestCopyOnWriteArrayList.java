package com.hacker.demo01Volatile.demo05_CopyOnWriteArrayList关键字;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/20
 * @description : CopyOnWriteArrayList/CopyOnWriteArraySet原理：写并复制
 * 注意：
 *      多线程实现集合 添加操作多时，用CopyOnWriteArrayList实现效率低。因为每次添加都new一个对象，将旧的对象复制过去，资源开销很大
 *      多线程实现集合 迭代操作多时，用该对象实现效率很高
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(threadDemo).start();
        }
    }
}

class ThreadDemo implements Runnable{

    // Collections.synchronizedList() 将list的所有方法以同步的方式实现
    //private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("DD");
        }
    }
}
