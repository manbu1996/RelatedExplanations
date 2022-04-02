package com.example.demo.juc.concurrent.explanations;

import com.example.demo.annoations.NoThreadSafe;
import com.example.demo.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: RelatedExplanations
 * @description:Concurrent  通过 cas的 安全Int类 实现线程安全
 * @author: Mr.Wang
 * @create: 2022-04-03 01:51
 **/
@Slf4j
@ThreadSafe
public class ConcurrentTest2 {

    public  static int childTotal=1000;
    public  static int threadTotal=50;
    public  static AtomicInteger count=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(childTotal);
        for (int i=0;i<childTotal;i++){
            exec.execute(()->{
            try {
                semaphore.acquire();
                add();
                semaphore.release();

            }catch (Exception e){
                log.error("error:", e);
            }
            countDownLatch.countDown();;
            });

            countDownLatch.countDown();

        }
        countDownLatch.await();
        log.info("count:{}", count);
        exec.shutdown();
    }

    public static void add(){

        count.incrementAndGet();

    }

}
