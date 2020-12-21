package Multithreading;

public class SynchronizedBlock {

    public void create() {
        synchronized (this) {
            System.out.println("Create Method");
            try {
                // wait is used on the Object
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("Create method again");
        }
    }

    public void eat() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Eat Method");
            // To notify the threads in the WAIT state
            notify();
            // wait is used on the Object
            Thread.sleep(10000);
        }
    }

    public static void main(String[] args) {
        SynchronizedBlock synchronizedBlock = new SynchronizedBlock();

        Runnable runnable1 = () -> {
            synchronizedBlock.create();
        };
        Runnable runnable2 = () -> {
            try {
                synchronizedBlock.eat();
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
