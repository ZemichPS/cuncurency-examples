package exchanger;

import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

    private final Exchanger<String> exchanger;
    private String data;

    public Consumer(Exchanger<String> exchanger, String data) {
        this.exchanger = exchanger;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            System.out.println("Consumer is preparing ...");
            // Обмен данными с потребителем
            String receivedData = exchanger.exchange(data);
            System.out.println("Consumer get data: " + receivedData);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
