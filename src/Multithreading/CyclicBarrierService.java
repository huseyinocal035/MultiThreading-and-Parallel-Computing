package Multithreading;

import java.util.concurrent.*;

public class CyclicBarrierService {

    static class Processor implements Runnable {

        int id;
        CyclicBarrier cyclicBarrier;

        public Processor(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("Thread " + id + " started.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("Thread " + id + " finished.");

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier =
            new CyclicBarrier(5, () -> System.out.println("That's done all."));

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Processor(i + 1, cyclicBarrier));
        }

        executorService.shutdown();
    }
}
