package io.practical.p0004;

public class CounterSynchronizedInside implements Counter {

	private int counter;

	public CounterSynchronizedInside() {
	}

	@Override
	public void increment() {
		synchronized (this) {
			counter++;
		}
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

}
