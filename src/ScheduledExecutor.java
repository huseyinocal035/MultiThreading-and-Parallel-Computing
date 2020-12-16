import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;

public class ScheduledExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable runnable = () -> {
            System.out.println("Getting data from database periodically");
        };

        scheduledExecutorService.scheduleAtFixedRate(runnable, 1000, 1000, MILLISECONDS);

    }

}
