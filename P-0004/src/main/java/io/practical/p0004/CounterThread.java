package io.practical.p0004;

import java.util.concurrent.CountDownLatch;

public class CounterThread extends Thread {

	private Counter counter;
	private CountDownLatch countDownLatch;

	public CounterThread(String name, Counter counter, CountDownLatch countDownLatch) {
		super(name);
		this.countDownLatch = countDownLatch;
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.increment();
		countDownLatch.countDown();
//		System.out.println(getName() + " - " + counter.getCounter());
	}

}
