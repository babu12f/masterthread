package com.babor.callableExample;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableDemo2 {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future;

        for (int i = 1; i < 10; i++) {
            future = executor.submit(new MyCallable(i));
            try {
                list.add(future.get());
            }
            catch (ExecutionException ex) {
                System.out.println(ex.getMessage());
            }
        }

        executor.shutdown();
        //this is ont necessary in this case .. but .. good practice :)
        executor.awaitTermination(1, TimeUnit.DAYS);

        for (int i = 0; i < list.size(); i++) {
            //get returned values from call()
            System.out.println("List Values " + i + " Value: " + list.get(i));
        }


        /*FutureTask<Integer> myTask = new FutureTask<>(new MyCallable(10));
        Thread thread = new Thread(myTask);

        thread.start();

        try {
            System.out.println("return result is: " + myTask.get());
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }
}
