package io.pratical.p0002;

import java.util.concurrent.CountDownLatch;

public class Counter extends Thread {

	private static final int COUNT_SIZE = 10000;
	private int counter = 0;
	private CountDownLatch latch;

	public Counter(String name, CountDownLatch latch) {
		super(name);
		this.latch = latch;
	}

	public void increment() {
		counter++;
	}

	@Override
	public void run() {
		System.out.println("Thread " + getName() + " start");

		while (counter < COUNT_SIZE) {
			increment();
		}
		latch.countDown();

		System.out.println("Thread " + getName() + " - " + counter + " end");
	}

}
