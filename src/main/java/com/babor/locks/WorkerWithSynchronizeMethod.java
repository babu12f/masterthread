package com.babor.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerWithSynchronizeMethod {
    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    /**
     * synchronized, methods use different data (list1 list2) so by synchronized
     * methods if one thread runs the stageOne other thread cannot run stageTwo
     * at the same time because that same locks are used. Solution is using two
     * lock Object for two shared data.
     */
    public synchronized void stepOne() {
        try {
            //do thread work
            Thread.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(1200));
    }

    public synchronized void stepTwo() {
        try {
            //do thread work
            Thread.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(1200));
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
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}
