package lock;

import lock.model.Consumer;
import lock.model.Producer;
import lock.model.ProtectedStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ProtectedStorage storage = new ProtectedStorage(4);


        List<Runnable> tasks = new ArrayList<>();
        Stream.generate(()-> new Consumer(storage)).limit(16).forEach(tasks::add);

        ExecutorService executorService = Executors.newFixedThreadPool(21);
        tasks.add(new Producer(storage));
        tasks.forEach(executorService::execute);

    }
}
