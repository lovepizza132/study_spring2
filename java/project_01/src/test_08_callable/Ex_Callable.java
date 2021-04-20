package test_08_callable;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Ex_Callable {
	static class MyCallable implements Callable<String>{
		@Override
		public String call() throws Exception{
			String result = "Called at " + LocalTime.now();
			Thread.sleep(2000);
			return result;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        // 결과 대기
        System.out.println("result : " + futureTask.get());
	}
}
