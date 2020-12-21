package Multithreading;

import java.util.concurrent.Exchanger;

public class ThreadExchangeService {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread producerThread = new Thread(new Producer(exchanger));
        Thread consumerThread = new Thread(new Consumer(exchanger));

        producerThread.start();
        consumerThread.start();
    }


    static class Producer implements Runnable {

        private Exchanger<Integer> exchanger;

        private int counter;

        public Producer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter + 1;
                System.out.println("Producer incremented the counter " + counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private Exchanger<Integer> exchanger;

        private int counter;

        public Consumer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter - 1;
                System.out.println("Consumer decremented the counter " + counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
