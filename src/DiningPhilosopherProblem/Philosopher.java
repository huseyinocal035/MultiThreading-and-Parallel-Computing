package DiningPhilosopherProblem;

import java.util.Random;

public class Philosopher implements Runnable {

    private Fork leftFork;

    private Fork rightFork;

    private int index;

    private volatile boolean isFull = false;

    private Random random;

    private int counter;

    public Philosopher( int index, Fork leftFork, Fork rightFork) {
        this.index = index;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isFull) {

                think();
                if (leftFork.acquire(this, State.LEFT))
                    if (rightFork.acquire(this, State.RIGHT)) {
                        eat();
                        rightFork.putDown(this, State.RIGHT);
                    }
                leftFork.putDown(this, State.LEFT);

            }
        } catch (InterruptedException exception) {
        exception.printStackTrace();
    }

    }

    private void think() throws InterruptedException {
        System.out.println(this + " düşünüyor...");
        Thread.sleep(this.random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " yiyor...");
        counter++;
        Thread.sleep(this.random.nextInt(1000));
    }

    public int getEatingCounter(){
        return this.counter;
    }

    public void setFull(boolean isFull){
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
            "index=" + index +
            '}';
    }
}
