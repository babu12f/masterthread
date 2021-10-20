package com.babor.semaphores;

import java.util.concurrent.*;

public class SemaphoresDemo {
    public static void main(String[] args) throws InterruptedException {
        /*Semaphore semaphore = new Semaphore(1);

        semaphore.acquire();
        semaphore.release();

        System.out.println("Available permit: " + semaphore.availablePermits());*/

        //Connection.getInstance().connect();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    //Connection.getInstance().connect();
                    Connection2.getInstance().connect();
                }
                catch (Exception ie) {
                    ie.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("finished executor service");

    }
}
