package com.babor.createanduse;

import java.util.concurrent.TimeUnit;

public class ExtendThread extends Thread {
    @Override
    public void run() {
        for (int i=0; i< 5; i++) {
            System.out.println("["+i+"] Inside " + Thread.currentThread().getName());
            sleepOneSecond();
        }
    }

    private static void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
