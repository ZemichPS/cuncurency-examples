package executorservice;

import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> helloWorld = executorService.submit(() -> System.out.println("Hello World"));
        helloWorld.get(5_000, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

}
