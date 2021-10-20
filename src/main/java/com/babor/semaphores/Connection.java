package com.babor.semaphores;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance;

    private int conCount = 0;
    private Semaphore semaphore = new Semaphore(20, true);

    private Connection() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void connect() {

        try {
            semaphore.acquire();
            connectUtil();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }

    public void connectUtil() throws InterruptedException {

        //semaphore.acquire();

        synchronized (this) {
            conCount++;
            System.out.println("Number of Connection: " + conCount);
        }

        Thread.sleep(2000);

        synchronized (this) {
            conCount--;
            System.out.println("Number of Connection: " + conCount);
        }

        //semaphore.release();
    }
}
