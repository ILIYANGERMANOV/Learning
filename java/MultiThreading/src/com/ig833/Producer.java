package com.ig833;

import java.util.List;

class Producer implements Runnable {
    private static int counter = 1;
    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;


    public Producer(List<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;

    }

    @Override
    public void run() {
        while (!Main.wellFed.get()) {
            try {
                produce();
            } catch (InterruptedException ex) {
                System.out.println("###Interrupted exception!!!!!!");
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void produce() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() >= MAX_CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
        }

        if (Main.wellFed.get()) {
            System.out.println("%%Producing stopped in " + Thread.currentThread().getName());
            return;
        }

        System.out.println("#Production stated in " + Thread.currentThread().getName());
        Thread.sleep(1000); //simulating producing


        synchronized (taskQueue) {
            if (taskQueue.size() < MAX_CAPACITY) {
                taskQueue.add(counter);
                System.out.println("Produced by " + Thread.currentThread().getName() + ": " + counter++);
                taskQueue.notifyAll();
            } else {
                System.out.println("!Production item abandoned in " + Thread.currentThread().getName());
            }
        }

    }

}