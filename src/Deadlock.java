import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    private static java.util.concurrent.locks.Lock lock1 = new ReentrantLock();

    private static java.util.concurrent.locks.Lock lock2 = new ReentrantLock();

    public static void task1Logic() {
        lock1.lock();
        System.out.println("Thread one acquire LOCK1");

        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        lock2.lock();
        System.out.println("Thread one acquire LOCK2");

        lock1.unlock();
        lock2.unlock();
    }

    public static void task2Logic() {
        lock1.lock();
        System.out.println("Thread two acquire LOCK1");

        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        lock2.lock();
        System.out.println("Thread two acquire LOCK2");

        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        Runnable task1 = Deadlock::task1Logic;
        Runnable task2 = Deadlock::task2Logic;

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
