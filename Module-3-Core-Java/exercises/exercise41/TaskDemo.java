import java.util.concurrent.Callable;

public class TaskDemo implements Callable<String> {

    private final String taskName;

    public TaskDemo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() {
        return taskName + " completed";
    }
}