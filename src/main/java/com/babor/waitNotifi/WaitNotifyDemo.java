package com.babor.waitNotifi;

public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {

        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    processor.produce();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    processor.consume();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
