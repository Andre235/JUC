package com.hacker.demo01Volatile.demo01_volatile关键字;

/**
 * @author : Jeffersonnn
 * @date : 2020/1/29
 * @description :
 */
public class ThreadDemo implements Runnable {

    private volatile Boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag= "+flag);
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
