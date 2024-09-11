package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {


    public static void main(String[] args) throws InterruptedException {

        int numberOfFiles = 3;
        CountDownLatch latch = new CountDownLatch(numberOfFiles);

        // Запуск потоков для загрузки файлов
        new Thread(new FileDownLoader(latch, "file1.txt")).start();
        new Thread(new FileDownLoader(latch, "file2.txt")).start();
        new Thread(new FileDownLoader(latch, "file3.txt")).start();

        latch.await();
        System.out.println("There are all files has been downloaded. Go on main thread.");
    }

}
