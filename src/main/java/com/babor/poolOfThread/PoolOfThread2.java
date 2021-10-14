package com.babor.poolOfThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();


    @Override
    public void run() {
        process();
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stepOne();
            stepTwo();
        }
    }

    public void stepOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stepTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }
}

public class PoolOfThread2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);//two threads, try setting by 1 to observe time
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Worker worker = new Worker();
        for (int i = 1; i <7; i++) {//worker.run is called 2 (threads started) times by two threads
            executor.submit(worker);
        }
        System.out.println("All Task Submitted!");
        executor.shutdown(); //prevents new tasks from being accepted by the ExecutorService
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
            // How long should I wait for termination If I do not know exactly when threads are done with the tasks ?
            // Source:http://stackoverflow.com/questions/1250643/how-to-wait-for-all-threads-to-finish-using-executorservice
            // For a perpetually running batch kind of thing u need to submit jobs and wait for them to
            // finish before jumping ahead.
            // In Such a case a latch or a barrier makes more sense than a shutdown.
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + worker.list1.size() + "; List2: " + worker.list2.size());
    }
}
