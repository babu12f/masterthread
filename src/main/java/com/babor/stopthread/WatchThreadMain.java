package com.babor.stopthread;

public class WatchThreadMain {
    public static void main(String[] args) throws InterruptedException {
        Watch watch = new Watch();
        Thread watchThread = new Thread(watch);
        watchThread.start();

        Thread.sleep(5000);
        watch.shutDown();
    }
}
