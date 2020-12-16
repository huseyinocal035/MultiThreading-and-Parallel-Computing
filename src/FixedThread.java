import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThread implements Runnable {

    private int id;

    public FixedThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println( "FixedThread's id : " + id + "\n Thread id : " +  Thread.currentThread().getId() + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new FixedThread(i));
        }

        // to prevent for executorService work from now on
        // will execute nothing
        executorService.shutdown();
    }
}
