package io.practical.p0004;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import io.practical.p0004.CounterAtomic;
import io.practical.p0004.CounterOptimized;
import io.practical.p0004.CounterSynchronizedInside;
import io.practical.p0004.CounterSynchronizedMethod;

@State(Scope.Thread)
public class P0004 {

	private static final int COUNTER_SIZE = 1000000;

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void benchmarkCounterOptimized() throws InterruptedException {
		CounterSynchronizedInside counter = new CounterSynchronizedInside();
		benchmarkInside(counter);
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void benchmarkCounterSynchronizedInside() throws InterruptedException {
		CounterOptimized counter = new CounterOptimized();
		benchmarkInside(counter);
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void benchmarkCounterSynchronizedMethod() throws InterruptedException {
		CounterSynchronizedMethod counter = new CounterSynchronizedMethod();
		benchmarkInside(counter);
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void benchmarkCounterAtomic() throws InterruptedException {
		CounterAtomic counter = new CounterAtomic();
		benchmarkInside(counter);
	}

	private void benchmarkInside(Counter counter) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		CountDownLatch countDownLatch = new CountDownLatch(COUNTER_SIZE);
		CounterThread thread = new CounterThread("CounterAtomic", counter, countDownLatch);

		for (int i = 0; i < COUNTER_SIZE; i++) {
			executorService.execute(thread);
		}

		countDownLatch.await();

	}

}
