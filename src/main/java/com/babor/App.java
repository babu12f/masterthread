package com.babor;

/**
 * Master Thread
 *
 * @author Shofiullah Babor
 * @email babu_12f@yahoo.com
 *
 */
public class App {
    public static void main( String[] args ) {
        Thread currentThread = Thread.currentThread();

        System.out.println(currentThread.getName());
    }
}
