package com.hacker.demo01Volatile;

/**
 * @author : Jeffersonnn
 * @date : 2020/1/29
 * @description :
 */
public class ThreadDemo implements Runnable {

    private Boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
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
