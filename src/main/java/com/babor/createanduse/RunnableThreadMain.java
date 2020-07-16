package com.babor.createanduse;

import java.util.concurrent.TimeUnit;

public class RunnableThreadMain {
    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        Thread thread = new Thread(runnableThread);

        thread.setName("Thread using runnable interface implements");
        thread.start();

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
