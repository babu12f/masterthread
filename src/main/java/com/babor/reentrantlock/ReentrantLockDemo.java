package com.babor.reentrantlock;

/**
 *
 * http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/locks/ReentrantLock.html
 *
 * http://guruzon.com/1/concurrency/explicit-lock-locking/difference-between-synchronized-and-reentrantlock-in-java
 */

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        final MyReentrantLock myReentrantLock = new MyReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                myReentrantLock.firstStep();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                myReentrantLock.secondStep();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        myReentrantLock.finished();
    }
}
