package com.babor.callableExample;

import java.util.concurrent.*;

/**
 * Source:
 * http://java-x.blogspot.com.tr/2006/11/java-5-concurrency-callable-and-future.html
 *
 * */
public class CallableDemo3 {

    public static void main(String[] args) throws InterruptedException {

        Callable<Integer> callable = new CallableImpl(2);
        ExecutorService executor = new ScheduledThreadPoolExecutor(1);
        Future<Integer> future = executor.submit(callable);

        try {
            System.out.println("Future value: " + future.get());
        }
        catch (Exception ie) {
            ie.printStackTrace();
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

    }
}
