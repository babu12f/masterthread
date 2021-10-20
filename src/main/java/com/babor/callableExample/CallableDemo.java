package com.babor.callableExample;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws IOException {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 1500) {
                    throw new IOException("babor io exception");
                }

                System.out.println("Starting ...");
                try {
                    Thread.sleep(duration);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                System.out.println("Finished.");
                return duration;
            }
        });

        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.DAYS);
        try {
            //get returned value from call()
            System.out.println("Result is: " + future.get());

        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
