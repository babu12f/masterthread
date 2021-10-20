package com.babor.callableExample;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<Integer> {
    private int someVal;

    public CallableImpl(int someVal) {
        this.someVal = someVal;
    }

    @Override
    public Integer call() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread : " + getSomeVal() + " value is : " + i);
        }
        return getSomeVal();
    }

    public int getSomeVal() {
        return someVal;
    }

    public void setSomeVal(int someVal) {
        this.someVal = someVal;
    }
}
