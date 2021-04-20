package test_06_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import test_06_concurrency.ConcurrentUtils;
/*
 * classes commonly useful in concurrent programming
 * 1. ConcurrentHashMap
 * 2. ConcurrentLinkedQueue<E>
 * 3. CopyOnWriteArrayList<E>
 * 4. CopyOnWriteArraySet<E>
 * 5. LinkedBlockingDeque<E>
 * ...
 * 
 * thread-safe programming on single variables
 * 1. AtomicBoolean
 * 2. AtomicInteger
 * 3. AtomicIntegerArray
 * 4. AtomicLong
 * 5. AtomicLongArray
 * 6. AtomicReference<V>
 * 7. AtomicReferenceArray<E>
 * ...
 */

public class Main {
	public static void main(String[] args) {
		AtomicInteger atomicInt = new AtomicInteger(0);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 1000)
		    .forEach(i -> executor.submit(atomicInt::incrementAndGet));

		ConcurrentUtils.stop(executor);

		System.out.println(atomicInt.get());    // => 1000
		
	}
}
