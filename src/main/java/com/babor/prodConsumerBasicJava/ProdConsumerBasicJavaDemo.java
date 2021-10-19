package com.babor.prodConsumerBasicJava;

public class ProdConsumerBasicJavaDemo {
    public static void main(String[] args) throws InterruptedException {

        ProdConsumerBasicJava prodConsumerBasicJava = new ProdConsumerBasicJava();

        Thread t1 = new Thread(() -> {
            try {
                prodConsumerBasicJava.produce();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                prodConsumerBasicJava.consume();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });

        t1.start();
        t2.start();
//        t1.join();
//        t2.join();

        // Pause for 30 seconds and force quitting the app (because we're
        // looping infinitely)
        Thread.sleep(30000);
        System.exit(0);
    }
}
