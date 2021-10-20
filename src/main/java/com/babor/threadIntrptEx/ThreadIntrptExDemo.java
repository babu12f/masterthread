package com.babor.threadIntrptEx;

import java.util.Random;
import java.util.concurrent.*;

/**
 * How to interrupt running threads in Java using the built-in thread
 * interruption mechanism.
 * http://www.javamex.com/tutorials/threads/thread_interruption.shtml
 */
public class ThreadIntrptExDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future = executorService.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                Random random = new Random();

                for (int i = 0; i < 1e8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("thread is interrupted !!!");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
                return null;
            }
        });

        executorService.shutdown();
        Thread.sleep(500);

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#cancel-boolean-
//        future.cancel(true);

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdownNow--
//        executorService.shutdownNow();

        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished.");


       /* System.out.println("starting");

        Thread thread = new Thread(() -> {
            Random random = new Random();

            for (int i = 0; i < 1e8; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("thread is interrupted !!!");
                    break;
                }
                *//*try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    System.out.println("thread is interrupted !!!");
                    break;
                }*//*
                Math.sin(random.nextDouble());
            }
        });

        thread.start();

        Thread.sleep(500);

        thread.interrupt();

        thread.join();

        System.out.println("end");*/

    }
}
