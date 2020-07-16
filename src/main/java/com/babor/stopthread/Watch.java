package com.babor.stopthread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Watch implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            printCurrentTime();
            sleepOneSecond();

            if (Thread.interrupted()) {
                System.out.println("Thread is Interrupted");

                return;
            }
        }
    }

    private static void printCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:s a");

        String formattedTime = LocalDateTime.now().format(dateTimeFormatter);

        System.out.println(formattedTime);
    }

    private static void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        this.running = false;
    }
}
