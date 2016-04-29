package com.ig833;

public class ThreadTest extends Thread {
    public ThreadTest(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run() {
        System.out.println("Hello from Thread.");
    }
}
