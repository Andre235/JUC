package com.hacker.demo01Volatile.demo11_ForkJoin框架;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author : Jeffersonnn
 * @date : 2020/3/22
 * @description : Fork/Join 分支合并框架
 */
public class TestForkJoin {

    public static void main(String[] args) {
/*        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumCalculate forkJoinSumCalculate = new ForkJoinSumCalculate(0L, 10000000000L); //24758毫秒
        Long result = pool.invoke(forkJoinSumCalculate);
        Instant end = Instant.now();
        System.out.println(result);
        System.out.println("消耗的时间为："+Duration.between(start,end).toMillis()+"毫秒");

        test1();*/
        test2();

    }

    static void test1(){
        Instant start = Instant.now();
        long result = 0;
        for (long i = 0; i <= 10000000000L; i++) {  // 4679毫秒
            result +=i;
        }
        Instant end = Instant.now();
        System.out.println(result);
        System.out.println("消耗的时间为："+Duration.between(start,end).toMillis()+"毫秒");
    }

    static void test2(){
        int a = 2;
        int a1 = 2;
        int a2 = 2;
        int a3 = 2;
        System.out.println(a++);
        System.out.println(++a1);
        int b = a2++;
        int c = ++a3;
        System.out.println(b);
        System.out.println(c);

        int temp = 0;
        temp ++;
        System.out.println(temp);
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private final Long threshold = 10000L;

    public ForkJoinSumCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        Long step = end - start;
        if(step <= threshold){
            Long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            Long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            // 进行拆分，同时压入线程队列
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
