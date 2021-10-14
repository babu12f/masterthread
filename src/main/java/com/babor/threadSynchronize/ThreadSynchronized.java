package com.babor.threadSynchronize;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSynchronized {

    private int count = 0;

    public static void main(String[] args) {
        ThreadSynchronized worker = new ThreadSynchronized();
        worker.doWork();
    }

    /**
     * Run code again by removing the synchronized and join keywords By removing
     * synchronized keyword count variable will vary that is it is not atomic in
     * this case due to the reason that count is shared between the threads or
     * without join keyword count will output wrong.
     */
    public synchronized void increment(String threadName) throws InterruptedException {
        count++;
        //Thread.sleep(100);
        System.out.println("Thread in Progress: " + threadName + " and count is: " + count);
    }

    public void doWork() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                try {
                    increment(Thread.currentThread().getName());
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(ThreadSynchronized.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                try {
                    increment(Thread.currentThread().getName());
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(ThreadSynchronized.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread1.start();
        thread2.start();

        /**
         * Join Threads: Finish until thread finishes execution, then progress
         * the code Reminder: your method is also a thread so without joining
         * threads System.out.println("Count is: " + count); will work
         * immediately. Join does not terminate Thread 2, just progress of the
         * code stops until Threads terminate.
         */
        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Count is: " + count);
    }
}
