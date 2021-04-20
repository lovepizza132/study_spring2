package test_08_callable;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex_ExecutorService {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            String result = "Called at " + LocalTime.now();
            Thread.sleep(3000);
            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable);

        // 결과 대기
        System.out.println("result : " + future.get());
    }
}
