package io.practical.p0004;

public class CounterSynchronizedMethod implements Counter {

	private int counter;

	public CounterSynchronizedMethod() {
	}

	@Override
	public synchronized void increment() {
		this.counter++;
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

}
