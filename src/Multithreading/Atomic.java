package Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increment() {
        for (int i = 0; i < 100; i++) {
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        Runnable task1 = Atomic::increment;
        Runnable task2 = Atomic::increment;

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        System.out.println("AtomicInterger : " + atomicInteger);
    }
}
