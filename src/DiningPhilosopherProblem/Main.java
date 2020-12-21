package DiningPhilosopherProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;

        try{

            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            Fork[] chopSticks = new Fork[Constants.NUMBER_OF_PHILOSOPHERS];

            for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;i++){
                chopSticks[i] = new Fork(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

            for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;i++){
                philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i+1) % Constants.NUMBER_OF_PHILOSOPHERS]);
                executorService.execute(philosophers[i]);
            }

            try {
                Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            for(Philosopher philosopher : philosophers){
                philosopher.setFull(true);
            }
        }finally{

            executorService.shutdown();

            while(!executorService.isTerminated()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            for(Philosopher philosopher : philosophers ){
                System.out.println(philosopher + " " + philosopher.getEatingCounter() +  " kere yedi");
            }

        }
    }
}
