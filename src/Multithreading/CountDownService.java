package Multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownService {

    static CountDownLatch countDownLatch = new CountDownLatch(4);

    static class Processor implements Runnable {

        int id;
        CountDownLatch countDownLatch;

        public Processor(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Thread " + id + " started.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("Thread " + id + " finished.");
            countDownLatch.countDown();
            System.out.println("LatchCount : " + countDownLatch.getCount());
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 4; i++) {
            executorService.submit(new Processor((i + 1), countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("That's done all.");
        executorService.shutdown();
    }
}
