import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<String> future1 =
                executorService.submit(new TaskDemo("Task 1"));

        Future<String> future2 =
                executorService.submit(new TaskDemo("Task 2"));

        Future<String> future3 =
                executorService.submit(new TaskDemo("Task 3"));

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        executorService.shutdown();
    }
}