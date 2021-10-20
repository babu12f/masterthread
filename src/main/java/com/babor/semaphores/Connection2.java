package com.babor.semaphores;

import java.util.concurrent.Semaphore;

public class Connection2 {
    private static Connection2 instance = new Connection2();

    private Semaphore semaphore = new Semaphore(10, true);

    private Connection2() {
    }

    public static Connection2 getInstance() {
        return instance;
    }

    public void connect() {
        try {
            // get permit decrease the sem value, if 0 wait for release
            semaphore.acquire();

            System.out.printf("%s:: Current connections (max 10 allowed): %d\n",
                    Thread.currentThread().getName(),
                    semaphore.availablePermits());

            //do our job here
            System.out.printf("%s:: WORKING...\n",
                    Thread.currentThread().getName());

            Thread.sleep(2000);

            System.out.printf("%s:: Connection released. Permits Left = %d\n",
                    Thread.currentThread().getName(),
                    semaphore.availablePermits());

        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        finally {
            //release permit, increase the sem value and activate waiting thread
            semaphore.release();
        }
    }
}
