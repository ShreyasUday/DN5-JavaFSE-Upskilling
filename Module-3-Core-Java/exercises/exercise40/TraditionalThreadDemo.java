public class TraditionalThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {

            int threadNumber = i;

            Thread thread = new Thread(() -> {
                if (threadNumber <= 10) {
                    System.out.println("Traditional Thread " + threadNumber);
                }
            });

            thread.start();
        }

        Thread.sleep(3000);

        long endTime = System.currentTimeMillis();

        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }
}