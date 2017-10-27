package io.practical.p0006;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class P0006 {

	private static final int COUNTER_SIZE = 100000;

	public P0006() {
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void benchmark() throws InterruptedException {
		Counter counter = new Counter();
		CountDownLatch countDownLatch = new CountDownLatch(COUNTER_SIZE);
		CounterThread thread = new CounterThread(counter, countDownLatch);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		// ExecutorService executorService = Executors.newFixedThreadPool(4);
		// ExecutorService executorService = Executors.newFixedThreadPool(8);
		// ExecutorService executorService = Executors.newFixedThreadPool(16);

		for (int i = 0; i < COUNTER_SIZE; i++) {
			executorService.execute(thread);
		}

		countDownLatch.await();
	}
}
