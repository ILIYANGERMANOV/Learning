package com.ig833;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicBoolean wellFed = new AtomicBoolean(false);


    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;
        AtomicInteger hunger = new AtomicInteger(30);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Thread t = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer#" + i);
            t.start();
            threads.add(t);
        }
        for (int i = 0; i < 5; ++i) {
            Thread t = new Thread(new Consumer(taskQueue, hunger), "Consumer#" + i);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("~Production stack size is " + taskQueue.size());
        //new ThreadTest(()->System.out.println("Hello from Runnable")).start();
    }
}
