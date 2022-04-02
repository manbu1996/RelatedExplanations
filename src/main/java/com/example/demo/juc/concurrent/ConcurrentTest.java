package com.example.demo.juc.concurrent;

import com.example.demo.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: RelatedExplanations
 * @description:Concurrent 简单demo
 * @author: Mr.Wang
 * @create: 2022-04-03 01:51
 **/
@Slf4j
@NoThreadSafe
public class ConcurrentTest {

    public  static int childTotal=1000;
    public  static int threadTotal=50;
    public  static int count=0;

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
            count++;
    }

}
