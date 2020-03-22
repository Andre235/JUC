package com.hacker.demo01Volatile.demo02_原子性;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/19
 * @description : demo02_原子性：计算机中最小的可执行单元，其操作不可分割
 * volatile关键字没有避免原子性问题
 * i++的原子性的问题，可以拆分成读、改、写三部
 * int i = 10;
 * i++; // 实际输出结果为10
 *
 * int temp = i;
 * i = i + 1;
 * i = temp;
 *
 * 解决方案：用JUC包中原子变量解决
 *      原子变量底层原理：
 *          1.volatile关键字可以解决内存可见性问题
 *          2.CAS(compare-and-swap)算法是硬件对并发操作的支持
 *              2.1 比较操作
 *              2.2 交换操作 | 比较失败则等待下一次重新进行比较
 *
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        TestAtomic testAtomic = new TestAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread(testAtomic).start();
        }
    }
}

