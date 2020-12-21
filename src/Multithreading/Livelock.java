package Multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// This class is an livelock example implementation, so
// FIXME it should be fixed
public class Livelock {

    private static java.util.concurrent.locks.Lock lock1 = new ReentrantLock();

    private static java.util.concurrent.locks.Lock lock2 = new ReentrantLock();

    public static void task1Logic() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            System.out.println("TaskOne is acquired LOCK1");
            System.out.println("TaskOne is trying to acquire LOCK2");

            if (lock2.tryLock()) {
                System.out.println("TaskOne is acquired LOCK2");
                lock2.unlock();
            } else {
                System.out.println("TaskOne is not acquired LOCK2");
                continue;
            }

            break;
        }

        lock1.unlock();
        lock2.unlock();
    }

    public static void task2Logic() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            System.out.println("TaskTwo is acquired LOCK2");
            System.out.println("TaskTwo is trying to acquire LOCK1");

            if (lock1.tryLock()) {
                System.out.println("TaskTwo is acquired LOCK1");
                lock1.unlock();
            } else {
                System.out.println("TaskTwo is not acquired LOCK1");
                continue;
            }

            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        Runnable task1 = Livelock::task1Logic;
        Runnable task2 = Livelock::task2Logic;

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
