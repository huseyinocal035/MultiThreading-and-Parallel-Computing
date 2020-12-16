import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    private java.util.concurrent.locks.Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer Method");
        condition.await();
        System.out.println("Producer method again");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(1500);
        System.out.println("Consumer Method");
        condition.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        Lock lock = new Lock();

        Runnable runnable1 = () -> {
            try {
                lock.producer();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try {
                lock.consumer();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
