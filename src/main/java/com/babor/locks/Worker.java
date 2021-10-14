package com.babor.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stepOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(2);
            }
            catch (InterruptedException e) {
                //do thread work
                e.printStackTrace();
            }
            list1.add(random.nextInt(1200));
        }
    }

    public void stepTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(2);
            }
            catch (InterruptedException e) {
                //do thread work
                e.printStackTrace();
            }
            list2.add(random.nextInt(1200));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stepOne();
            stepTwo();
        }
    }

    public void work() {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            process();
        });

        Thread t2 = new Thread(() -> {
            process();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}
