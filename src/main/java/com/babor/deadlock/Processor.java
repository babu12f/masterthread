package com.babor.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock first, Lock second) throws InterruptedException {
        while (true) {
            boolean isFirstLockLocked = false;
            boolean isSecondLockLocked = false;

            try {
                isFirstLockLocked = first.tryLock();
                isSecondLockLocked = second.tryLock();
            }
            finally {
                if (isFirstLockLocked && isSecondLockLocked) {
                    return;
                }
                else if (isFirstLockLocked) {
                    first.unlock();
                }
                else if (isSecondLockLocked) {
                    second.unlock();
                }
            }

            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            /*lock1.lock();
            lock2.lock();*/
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(account1, account2, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            /*
            this order of lock create deadlock,
            solution is lock in same order
            lock2.lock();
            lock1.lock();*/

            acquireLocks(lock2, lock1);

            try {
                Account.transfer(account2, account1, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }


    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }

}
