package com.babor.prodcerConsumerDefault;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDefault {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                producer();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                consumer();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });
        t1.start();
        t2.start();

        // Pause for 1 min and force quitting the app (because we're looping infinitely)
        Thread.sleep(1000 * 60);
        System.exit(0);
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));//if queue is full waits
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer value = queue.take();//if queue is empty waits
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            }
        }
    }
}
