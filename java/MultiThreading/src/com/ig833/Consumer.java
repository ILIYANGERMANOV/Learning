package com.ig833;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Consumer implements Runnable {
    private final List<Integer> taskQueue;
    private final AtomicInteger hunger;

    public Consumer(List<Integer> sharedQueue, AtomicInteger hunger) {
        this.taskQueue = sharedQueue;
        this.hunger = hunger;
    }

    @Override
    public void run() {
        while (!Main.wellFed.get()) {
            try {
                consume();
            } catch (InterruptedException ex) {
                System.out.println("###Interrupted exception!!!!!!");
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void consume() throws InterruptedException {
        int i;

        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
            if (hunger.get() == 0) {
                System.out.println("%%Well fed in " + Thread.currentThread().getName());
                return;
            }
            try {
                i = taskQueue.remove(0);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
                return;
            }

        }

        System.out.println("#Consumption started by " + Thread.currentThread().getName());
        Thread.sleep(1000); //simulating consuming

        System.out.println("Consumed by " + Thread.currentThread().getName() + ": " + i);
        notifyProducers();

        if (hunger.decrementAndGet() == 0) {
            Main.wellFed.set(true);
            System.out.println("%%Last eaten in " + Thread.currentThread().getName());
            notifyProducers();
        }
    }

    private void notifyProducers() {
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
    }
}