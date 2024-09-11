package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class FileDownLoader implements Runnable {
    private final CountDownLatch countDownLatch;
    private final String fileName;

    public FileDownLoader(CountDownLatch countDownLatch, String fileName) {
        this.countDownLatch = countDownLatch;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        try {
            // Симуляция загрузки файла
            System.out.println("Download file process : " + fileName);
            Thread.sleep((long) (Math.random() * 20_000));  // Симулируем время загрузки файла
            System.out.println("Download completed: " + fileName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            countDownLatch.countDown();  // Уменьшаем счётчик на 1 после завершения задачи
        }
    }
}
