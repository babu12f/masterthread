package com.babor.createanduse;

import java.util.concurrent.TimeUnit;

public class ExtendThreadMain {
    public static void main(String[] args) {

        ExtendThread extendThread = new ExtendThread();

        extendThread.setName("Thread Using extending Thread class");
        extendThread.start();

        for (int i = 0; i < 5; i++) {
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
