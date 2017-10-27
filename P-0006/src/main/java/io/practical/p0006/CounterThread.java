package io.practical.p0006;

import java.util.concurrent.CountDownLatch;

public class CounterThread extends Thread {

	private Counter counter;
	private CountDownLatch countDownLatch;

	public CounterThread(Counter counter, CountDownLatch countDownLatch) {
		this.counter = counter;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		counter.increment();
		countDownLatch.countDown();
	}
}
