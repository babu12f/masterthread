package com.babor.locks;

public class LockApp {
    public static void main(String[] args) {
        System.out.println("Synchronized Objects: ");
        Worker worker = new Worker();
        worker.work();

        System.out.println("Synchronized Methods: ");
        WorkerWithSynchronizeMethod worker2 = new WorkerWithSynchronizeMethod();
        worker2.work();
    }
}
