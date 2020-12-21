package Multithreading;

// This implementation is kind of time-slicing algorithm
public class Multithreading {

    public static class Thread1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("Thread1 : " +  i);
            }
        }
    }

    public static class Thread2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("Thread2 : " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());

        thread1.start();
        thread2.start();

        try {
            // added join method to print 'Threads have finished.' after threads.
            thread1.join();
            thread2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("Threads have finished");
    }

}


