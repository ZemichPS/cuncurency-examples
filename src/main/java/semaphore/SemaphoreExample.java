package semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {

        ConnectionPool connectionPool = new ConnectionPool(3);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    connectionPool.getConnection();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }


    }
}
