package lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    ReentrantLock lock = new ReentrantLock();
    int counter = 0;

    public static void main(String[] args) {
        LockExample example = new LockExample();

        for (int i = 0; i < 1_0000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10_0; j++) {
                    example.increment();

                }
                System.out.println(Thread.currentThread().getName() + " complete task");
            }).start();
        }

        System.out.println("Final counter: " + example.counter);
    }


    public void increment() {
        if (lock.tryLock()) {
            try {
                counter++;
                System.out.println("Counter:" + counter);
            } finally {
                lock.unlock();
            }
        } else System.out.println("Fall in capture block");
    }
}
