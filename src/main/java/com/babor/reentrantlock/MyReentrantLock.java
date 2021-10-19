package com.babor.reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void inc() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstStep() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting ....");
        condition.await();
        System.out.println("Woken up!");

        try{
            inc();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondStep() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        condition.signal();

        try{
            inc();
        }
        finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
