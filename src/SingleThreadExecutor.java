import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor implements Runnable{

    private int id;

    public SingleThreadExecutor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println( "SingleThreadExecutor's id : " + id + "\n Thread id : " +  Thread.currentThread().getId() + "\n");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorService.execute(new SingleThreadExecutor(i));
        }

    }
}
