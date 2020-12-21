package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample implements Callable<Integer> {

    private int id;

    public CallableExample(int id) {
        this.id = id;
    }

    @Override
    public Integer call() {
        return id;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Future<Integer> future = executorService.submit(new CallableExample(i));
            futureList.add(future);
        }

        for (Future<Integer> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
