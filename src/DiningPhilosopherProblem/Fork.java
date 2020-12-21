package DiningPhilosopherProblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private Lock lock;

    private int index;

    public Fork(int index) {
        this.index = index;
        this.lock = new ReentrantLock();
    }

    public boolean acquire(Philosopher philosopher, State state) {
        try {
            if (this.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                System.out.println(philosopher + " " + state.toString() + " şunu aldı -> " + this);
                return true;
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        lock.unlock();
        System.out.println(philosopher + " şunu bıraktı -> " + this);
    }

    @Override
    public String toString() {
        return "Fork{" +
            ", index=" + index +
            '}';
    }
}
