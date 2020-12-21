package Multithreading;

public class Volatile {

    private static volatile boolean terminated;

    public static boolean isTerminated() {
        return terminated;
    }

    public static void setTerminated(boolean terminated) {
        Volatile.terminated = terminated;
    }

    public static void logic() {
        while (!terminated) {
            // print should work 25 times until terminated set to true
            System.out.println("Thread is running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Runnable runnable = Volatile::logic;

        Thread thread = new Thread(runnable);
        thread.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        setTerminated(true);
        System.out.println("Finished!!!");
    }
}
