package Multithreading;

// This implementation is kind of time-slicing algorithm
// Lambda expression has been used for this class as well
public class MultithreadingWithLambda {

    public static void main(String[] args) {

        Runnable runnable1 = () -> {
            for (int i = 0; i < 100; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("Thread1 : " +  i);
            }
        };

        Runnable runnable2 = () -> {
            for (int i = 0; i < 100; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("Thread2 : " +  i);
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

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
