import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Semaphore {

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(4, true);

    private static Semaphore threadSafeSingleton;

    private Semaphore() {};

    public static synchronized Semaphore getInstance() {
        if (threadSafeSingleton == null)
            threadSafeSingleton = new Semaphore();
        return threadSafeSingleton;
    }

    public void getDataFromDatabase() {
        try {
            semaphore.acquire();
            getData();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void getData() throws InterruptedException {
        System.out.println("Data is getting from database");
        Thread.sleep(3000);
    }

    public static void main(String[] args) {
        Semaphore semaphore = Semaphore.getInstance();
        for (int i = 0; i < 20; i++) {
            executorService.execute(semaphore::getDataFromDatabase);
        }
    }
}
