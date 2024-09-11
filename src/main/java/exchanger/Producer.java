package exchanger;

import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
    private final Exchanger<String> exchanger;
    private String data;

    public Producer(Exchanger<String> exchanger, String data) {
        this.exchanger = exchanger;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            System.out.println("Producer is preparing ...");
            // Обмен данными с потребителем
            String receivedData = exchanger.exchange(data);
            System.out.println("Producer get data: " + receivedData);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
