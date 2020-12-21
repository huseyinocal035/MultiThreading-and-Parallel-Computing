package Multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueService implements Delayed {

    private Long duration;

    private String message;

    public DelayedQueueService(Long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    public static void main(String[] args) {
        BlockingQueue<DelayedQueueService> blockingQueue = new DelayQueue<>();

        try {
            blockingQueue.put(new DelayedQueueService((long) 100, "0.1 second"));
            blockingQueue.put(new DelayedQueueService((long) 1000, "1 second"));
            blockingQueue.put(new DelayedQueueService((long) 500, "0.5 second"));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        while (!blockingQueue.isEmpty()) {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = duration - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);    }

    @Override
    public int compareTo(Delayed delayed) {
        if (this.duration < ((DelayedQueueService) delayed).duration)
            return -1;
        if (this.duration > ((DelayedQueueService) delayed).duration)
            return +1;
        return 0;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Multithreading.DelayedQueueService{" +
            "duration=" + duration +
            ", message='" + message + '\'' +
            '}';
    }
}
