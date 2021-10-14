package com.babor.latchInThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started.");

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        latch.countDown();
    }
}

public class LatchInThread {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            executor.submit(new Processor(latch));
        }
        executor.shutdown();

        try {
            // Application’s main thread waits, till other service threads which are
            // as an example responsible for starting framework services have completed started all services.
            latch.await();
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("Completed.");
    }

}