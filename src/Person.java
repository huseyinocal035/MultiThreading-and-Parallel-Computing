import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

// NOTE This is a PriorityQueue implementation
public class Person implements Comparable<Person>{

    private int id;

    private int age;

    private String name;

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        BlockingQueue<Person> blockingQueue = new PriorityBlockingQueue<>();

        Thread producerThread = new Thread(new Producer(blockingQueue));
        Thread consumerThread = new Thread(new Consumer(blockingQueue));

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {

        private BlockingQueue<Person> blockingQueue;

        public Producer(BlockingQueue<Person> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                blockingQueue.put(new Person(1, 23, "Ahmet"));
                Thread.sleep(3000);
                blockingQueue.put(new Person(2, 28, "Ali"));
                Thread.sleep(300);
                blockingQueue.put(new Person(3, 46, "Mustafa"));
                Thread.sleep(1000);
                blockingQueue.put(new Person(4, 76, "Hüseyin"));
                blockingQueue.put(new Person(5, 19, "Ömer"));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<Person> blockingQueue;

        public Consumer(BlockingQueue<Person> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                System.out.println(blockingQueue.take());
                Thread.sleep(1000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                Thread.sleep(3000);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public int compareTo(Person person) {
        // here is the sorting related to fields (for this field is id)
        return Integer.compare(this.id, person.id);
//        For field name is below
//        return name.compareTo(person.name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", age=" + age +
            ", name='" + name + '\'' +
            '}';
    }
}
