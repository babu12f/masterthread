package com.babor.callableExample;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    int value;

    public MyCallable(int value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        Integer sum = 0;
        for (int i = 0; i <= this.value; i++) {
            sum += i;
        }
        System.out.println("Sum in Callable.Call() " + sum);
        return sum;
    }
}
