import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapService {

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        Thread producerThread = new Thread(new Producer(concurrentMap));
        Thread consumerThread = new Thread(new Consumer(concurrentMap));

        producerThread.start();
        consumerThread.start();
    }


    static class Producer implements Runnable {

        private ConcurrentMap<String, Integer> concurrentMap;

        public Producer(ConcurrentMap<String, Integer> concurrentMap) {
            this.concurrentMap = concurrentMap;
        }

        @Override
        public void run() {
            try {
                concurrentMap.put("Ahmet", 1);
                concurrentMap.put("Ali", 2);
                Thread.sleep(1000);
                concurrentMap.put("Okan", 3);
                concurrentMap.put("Mustafa", 4);
                Thread.sleep(1000);
                concurrentMap.put("Ömer", 5);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {

        private ConcurrentMap<String, Integer> concurrentMap;

        public Consumer(ConcurrentMap<String, Integer> concurrentMap) {
            this.concurrentMap = concurrentMap;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(concurrentMap.get("Okan"));
                Thread.sleep(1000);
                System.out.println(concurrentMap.get("Hüseyin"));
                System.out.println(concurrentMap.get("Mustafa"));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
