package lock.model;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable {

    private ProtectedStorage storage;
    private List<String> messages = new ArrayList<String>();

    public Consumer(ProtectedStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            String news = storage.pop();
            messages.add(news);
            String threadName = Thread.currentThread().getName();
            System.out.println("Consumer %s consume news: %s".formatted(threadName, news));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
