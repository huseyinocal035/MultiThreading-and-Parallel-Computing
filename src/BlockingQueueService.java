import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueService {

    static class Producer implements Runnable {

        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int counter = 0;
            while (true) {
                try {
                    blockingQueue.put(counter);
                    System.out.println("Object Queue'ya ekleniyor " + counter);
                    counter++;
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<Integer> blockingQueue;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int number = blockingQueue.take();
                    System.out.println("Object Queue'den çıkarılıyor " + number);
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
